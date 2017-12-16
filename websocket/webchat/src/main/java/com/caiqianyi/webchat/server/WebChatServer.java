package com.caiqianyi.webchat.server;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caiqianyi.commons.beans.SpringConfigTool;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.soa.amqp.core.sender.IRabbitmqSender;
import com.caiqianyi.soa.core.redis.IRedisHash;
import com.google.gson.Gson;

@ServerEndpoint(value = "/chatServer/{clubId}/{userId}")
@Component
public class WebChatServer {
	
	private Logger logger = LoggerFactory.getLogger(WebChatServer.class);
	
    private String clubId;
    private String userId;      //用户名
    
    private IRabbitmqSender rabbitmaSender;
    
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("clubId") String clubId,  
            @PathParam("userId") String userId,
            Session session, EndpointConfig config){
    	
    	IRedisHash redisHash = (IRedisHash) SpringConfigTool.getBean("redisHash");
        Map<String,Object> club = redisHash.hGetAll("webchat:club:"+clubId);
        if(club == null){
        	throw new I18nMessageException("500","房间不存在");
        }
    	logger.debug("1");
        Map<String,Session> routetab =  ClubWebChatBoat.get(clubId);
        if(routetab == null){
        	routetab = new HashMap<String,Session>();
        }
        
        if(routetab.containsKey(userId)){
        	JSONObject member = new JSONObject();
            member.put("message", "对不起，您的ID在其他地方登录。");
            member.put("type", "offline");
            String message = member.toJSONString();
            sendMessage(clubId, userId, message, "onOpen");
        	routetab.remove(userId);
        }
        routetab.put(userId, session);   //将用户名和session绑定到路由表
        
        Set<String> online = (Set<String>)club.get("online");
        
        online.add(userId);
        
        ClubWebChatBoat.put(clubId, routetab);
        this.clubId = clubId;
        this.userId=userId+"";    //获取当前用户
        logger.debug("2");
        redisHash.hSet("webchat:club:"+clubId, "online", online);
        
        JSONObject member = new JSONObject();
        member.put("message", "[" + userId + "]加入["+clubId+"]聊天室,当前在线人数为"+online.size()+"位");
        member.put("type", "notice");
        member.put("list", online);
        
        String field = "records_"+DateFormatUtils.format(new Date(), "yyyyMMdd");
        if(redisHash.hExists("webchat:club:"+clubId, field)){
        	List<String> records = (List<String>)redisHash.hGet("webchat:club:"+clubId, field);
        	logger.debug("records={}",new Gson().toJson(records));
        	member.put("records", records);
        }
        String message = member.toJSONString();
        sendMessage(clubId, null, message, "onOpen");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        Map<String,Session> routetab =  ClubWebChatBoat.get(clubId);
        if(routetab != null){
        	routetab.remove(userId);   //将用户名和session绑定到路由表
        	ClubWebChatBoat.put(clubId, routetab);
        }
        
        IRedisHash redisHash = (IRedisHash) SpringConfigTool.getBean("redisHash");
        if(!redisHash.exists("webchat:club:"+clubId)){
        	throw new I18nMessageException("500","房间不存在");
        }
        Set<String> online = (Set<String>)redisHash.hGet("webchat:club:"+clubId, "online");
        online.remove(userId);        //从在线列表移除这个用户
        
        redisHash.hSet("webchat:club:"+clubId, "online", online);
        
        JSONObject member = new JSONObject();
        member.put("message", "[" + userId +"]离开了聊天室,当前在线人数为"+online.size()+"位");
        member.put("type", "notice");
        member.put("list", online);
        String message = member.toJSONString();
        
        sendMessage(clubId, null, message, "onClose");
    }

    /**
     * 接收客户端的message,判断是否有接收人而选择进行广播还是指定发送
     * @param _message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String _message) {
        JSONObject chat = JSON.parseObject(_message);
        JSONObject message = JSON.parseObject(chat.get("message").toString());
        if(message.get("to") == null || message.get("to").equals("")){      //如果to为空,则广播;如果不为空,则对指定的用户发送消息
            sendMessage(clubId, null, _message, "onMessage");
            return;
        }
        String [] userlist = message.get("to").toString().split(",");
        sendMessage(clubId, message.getString("from"), _message, "onMessage");//发送给自己
        for(String user : userlist){
        	if(!user.equals(message.get("from"))){
        		sendMessage(clubId, user, _message, "onMessage");
        	}
        }
    }

    /**
     * 发生错误时调用
     * @param error
     */
    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }
    
    private void sendMessage(String clubId,String to,String msg,String handle){
    	if(rabbitmaSender == null){
    		rabbitmaSender = (IRabbitmqSender) SpringConfigTool.getBean("baseAmqpSender");
    	}
    	JSONObject message = new JSONObject();
        message.put("clubId", clubId);
        message.put("to", to);
        message.put("message", msg);
        message.put("handle", handle);
        
        rabbitmaSender.sendContractFanout(WebChatQueueConfig.CHAT_ROOM_BROADCAST,message.toString());
    }
}
