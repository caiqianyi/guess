package com.caiqianyi.guess.jclq.match;

import java.util.Map;

import com.caiqianyi.guess.jclq.entity.JCLQMatch;

public interface IJCLQMatchService {
	
	void pull(Map<String,JCLQMatch> nums);
	
}
