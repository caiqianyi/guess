package com.lebaoxun.webchat.service;

import java.util.Set;

public interface IChatClubService {
	
	public boolean create(String clubId);
	
	public boolean extis(String clubId);
	
	public Set<String> online(String clubId);
	
	public boolean isOnline(String clubId,String userId);
	
}
