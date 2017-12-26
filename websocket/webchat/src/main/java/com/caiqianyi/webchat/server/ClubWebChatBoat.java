package com.caiqianyi.webchat.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caiqianyi.commons.beans.SpringConfigTool;
import com.caiqianyi.soa.core.redis.IRedisHash;

public class ClubWebChatBoat {

	private static Logger logger = LoggerFactory
			.getLogger(ClubWebChatBoat.class);

	private static Map<String, Map<String, Session>> clubRoutetab = new HashMap<String, Map<String, Session>>();

	public static Map<String, Session> get(String clubId) {
		return clubRoutetab.get(clubId);
	}

	public static void put(String clubId, Map<String, Session> routetab) {
		clubRoutetab.put(clubId, routetab);
	}

	/**
	 * 广播消息
	 * 
	 * @param message
	 */
	public static synchronized void broadcast(String clubId, String to, String message, String handle) {
		Map<String, Session> routetab = clubRoutetab.get(clubId);
		if(routetab== null){
			return;
		}
		if (StringUtils.isNotBlank(to)) {
			if(routetab.containsKey(to)){
				send(message, routetab.get(to));
			}
			return;
		}

		logger.debug("111={}", handle);
		IRedisHash redisHash = (IRedisHash) SpringConfigTool
				.getBean("redisHash");
		List<String> records = null;
		String field = "records_"+DateFormatUtils.format(new Date(), "yyyyMMdd");
		if (redisHash.hExists("webchat:club:" + clubId, field)) {
			records = (List<String>) redisHash.hGet("webchat:club:" + clubId,
					field);
		}
		if (records == null) {
			records = new ArrayList<String>();
		}
		JSONObject chat = JSON.parseObject(message);
		if (chat.containsKey("records")) {
			chat.remove("records");
		}
		if (chat.containsKey("members")) {
			chat.remove("members");
		}
		records.add(chat.toJSONString());
		redisHash.hSet("webchat:club:" + clubId, field, records);
		for (String userId : routetab.keySet()) {
			Session session = routetab.get(userId);
			send(message, session);
		}
	}

	private static void send(String message, Session session) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
