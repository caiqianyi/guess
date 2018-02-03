package com.caiqianyi.webchat.server;

import java.io.IOException;
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
import com.caiqianyi.guess.entity.GuessClub;
import com.caiqianyi.guess.entity.GuessClubMember;
import com.caiqianyi.soa.amqp.core.sender.IRabbitmqSender;
import com.caiqianyi.soa.core.redis.IRedisCache;
import com.caiqianyi.soa.core.redis.IRedisHash;
import com.google.gson.Gson;

@ServerEndpoint(value = "/server/club/{clubId}/{memberId}")
@Component
public class ClubWebChatServer {
	
	private Logger logger = LoggerFactory.getLogger(ClubWebChatServer.class);
	
    private String clubId;
    private String memberId;      //用户名
    private GuessClubMember member;
    
    private IRabbitmqSender rabbitmaSender;
    
    private GuessClub getClub(Integer clubId){
    	IRedisCache redisCache = (IRedisCache) SpringConfigTool.getBean("redisCache");
    	Set<String> keys = redisCache.searchKey("guess:club:info:*:"+clubId);
    	if(keys.size() == 1){
    		return (GuessClub) redisCache.getSys(keys.iterator().next());
    	}
    	throw new I18nMessageException("-1","操作失败，聚乐部不存在");
    }
    
    private Map<String, Object> getCLubMembers(String clubId,String memberId){
    	IRedisHash redisHash = (IRedisHash) SpringConfigTool.getBean("redisHash");
    	String key = "guess:club:members:"+clubId;
    	if(redisHash.hExists(key, memberId)){
    		return redisHash.hGetAll(key);
    	}
    	throw new I18nMessageException("-1","操作失败，成员不存在");
    }
    
    private GuessClubMember getCLubMember(String clubId,String memberId){
    	IRedisHash redisHash = (IRedisHash) SpringConfigTool.getBean("redisHash");
    	String key = "guess:club:members:"+clubId;
    	if(redisHash.hExists(key, memberId)){
    		return (GuessClubMember) redisHash.hGet(key, memberId);
    	}
    	logger.debug("clubId={},memberId={}",clubId,memberId);
    	throw new I18nMessageException("-1","操作失败，成员不存在");
    }
    
    private void online(boolean online){
    	IRedisHash redisHash = (IRedisHash) SpringConfigTool.getBean("redisHash");
    	String key = "guess:club:members:"+clubId;
		if(redisHash.hExists(key, memberId+"")){
			GuessClubMember member = (GuessClubMember) redisHash.hGet(key, memberId+"");
			member.setOnline(online);
			redisHash.hSet(key, ""+memberId , member);
			return;
		}
		throw new I18nMessageException("-1","操作失败，成员不存在");
    }
    
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("clubId") Integer clubId,  
            @PathParam("memberId") Integer memberId,
            Session session, EndpointConfig config){
    	try{
    		logger.debug("clubId={},memberId={}",clubId,memberId);
    		GuessClub club = getClub(clubId);

    		this.clubId = clubId+"";
	        this.memberId=memberId+"";    //获取当前用户
	        
	        Map<String,Session> routetab =  ClubWebChatBoat.get(this.clubId);
	        if(routetab == null){
	        	routetab = new HashMap<String,Session>();
	        }
	        if(routetab.containsKey(this.memberId)){
	        	JSONObject member = new JSONObject();
	            member.put("message", "对不起，您的ID在其他地方登录。");
	            member.put("type", "logout");
	            String message = member.toJSONString();
	            sendMessage(this.clubId, this.memberId, message, "onOpen");
	            return;
	        	//routetab.remove(this.memberId);
	        }
	        routetab.put(this.memberId, session);   //将用户名和session绑定到路由表
	        
	        online(true);//修改登陆状态
	        
	        /**
	         * 获取用户列表，信息
	         */
    		Map<String, Object> members = getCLubMembers(this.clubId,this.memberId);
    		this.member = (GuessClubMember) members.get(this.memberId);
	    	
	    	IRedisHash redisHash = (IRedisHash) SpringConfigTool.getBean("redisHash");
	        
	        ClubWebChatBoat.put(this.clubId, routetab);
	        
	        JSONObject datas = new JSONObject();
	        datas.put("nickname", this.member.getNickname());
	        datas.put("userId", this.member.getUserId());
	        datas.put("id", this.member.getId());
	        datas.put("guessCount", this.member.getGuessCount());
	        datas.put("joinTime", this.member.getJoinTime());
	        
	        JSONObject message = new JSONObject();
	        message.put("type", "notice");
	        message.put("action", "join");
	        message.put("time", System.currentTimeMillis());
	        message.put("members", members);
	        message.put("datas", datas);
	        String field = "records_"+DateFormatUtils.format(new Date(), "yyyyMMdd");
	        if(redisHash.hExists("webchat:club:"+this.clubId, field)){//历史聊天纪录
	        	List<String> records = (List<String>)redisHash.hGet("webchat:club:"+this.clubId, field);
	        	logger.debug("records={}",new Gson().toJson(records));
	        	message.put("records", records);
	        }
	        String msg = message.toJSONString();
	        sendMessage(this.clubId, null, msg, "onOpen");//广播给俱乐部中在线成员
    	}catch(I18nMessageException e){
    		e.printStackTrace();
    		this.clubId = null;
    		this.member = null;
    		this.memberId = null;
    		JSONObject member = new JSONObject();
	        member.put("type", "error");
	        member.put("action", "join");
	        member.put("message", e.getMessage());
    		try {
    			session.getBasicRemote().sendText(member.toJSONString());
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        Map<String,Session> routetab =  ClubWebChatBoat.get(clubId);
        if(routetab != null){
        	routetab.remove(memberId);   //将用户名和session绑定到路由表
        	ClubWebChatBoat.put(clubId, routetab);
        }
        
        online(false);//修改下线状态
        
        Map<String, Object> members = getCLubMembers(this.clubId,memberId);
        
        JSONObject json = new JSONObject();
        JSONObject datas = new JSONObject();
        datas.put("nickname", this.member.getNickname());
        datas.put("userId", this.member.getUserId());
        datas.put("id", this.member.getId());
        datas.put("guessCount", this.member.getGuessCount());
        datas.put("joinTime", this.member.getJoinTime());
        
        json.put("type", "notice");
        json.put("action", "quit");
        json.put("members", members);
        json.put("datas", datas);
        json.put("time", System.currentTimeMillis());
        String message = json.toJSONString();
        
        sendMessage(clubId, null, message, "onClose");//广播给所有人更新在线列表

        this.clubId = null;
        this.memberId = null;
        this.member = null;
    }

    /**
     * 接收客户端的message,判断是否有接收人而选择进行广播还是指定发送
     * @param _message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String _message) {
    	try{
    		JSONObject msg = JSON.parseObject(_message);
            JSONObject chat = new JSONObject(),
            		message = new JSONObject();
            
            GuessClubMember member = getCLubMember(this.clubId, this.memberId);
            
            
            JSONObject fromJson = new JSONObject();
            fromJson.put("id", member.getId());
            fromJson.put("nickname", member.getNickname());
            fromJson.put("headimgurl", member.getHeadimgurl());
            
            message.put("content", msg.get("content"));
            message.put("from", fromJson);
            
            chat.put("datas", message);
            chat.put("type", "message");
            chat.put("time", System.currentTimeMillis());
            
            if(msg.get("to") == null || msg.get("to").equals("")){      //如果to为空,则广播;如果不为空,则对指定的用户发送消息
            	chat.put("action", "broadcast");
                sendMessage(clubId, null, chat.toJSONString(), "onMessage");
                return;
            }
            GuessClubMember toMember = getCLubMember(this.clubId, msg.getString("to"));
            
            JSONObject toJson = new JSONObject();
            toJson.put("id", toMember.getId());
            toJson.put("nickname", toMember.getNickname());
            toJson.put("headimgurl", toMember.getHeadimgurl());
            
            message.put("to", toJson);
            chat.put("action", "oto");
            String [] userlist = message.get("to").toString().split(",");
            sendMessage(clubId, this.memberId , chat.toJSONString(), "onMessage");//发送给自己
            for(String user : userlist){
            	if(!user.equals(this.memberId)){
            		sendMessage(clubId, user, chat.toJSONString(), "onMessage");
            	}
            }
    	}catch(I18nMessageException e){
    		e.printStackTrace();
    		JSONObject member = new JSONObject();
	        member.put("type", "error");
	        member.put("message", e.getMessage());
	        sendMessage(clubId, this.memberId , member.toJSONString(), "onMessage");//发送给自己
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
        logger.debug("send|message={}",message);
        rabbitmaSender.sendContractFanout(WebChatQueueConfig.CHAT_ROOM_BROADCAST,message.toString());
    }
}
