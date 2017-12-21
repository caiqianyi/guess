package com.caiqianyi.guess.caipiao.jclq.match.service;

import java.util.Map;

import com.caiqianyi.guess.jclq.entity.JCLQMatch;

public interface IJCLQMatchSyncService {
	
	void pull(Map<String,JCLQMatch> nums);
	
	void pull(Map<String,JCLQMatch> nums,String start,String end);
}
