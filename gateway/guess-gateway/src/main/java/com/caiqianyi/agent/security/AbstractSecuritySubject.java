package com.caiqianyi.agent.security;

public abstract class AbstractSecuritySubject {
	
	public abstract Object getCurrentUser();
	
	public abstract void logout();
	
	public abstract Object login(String username);
	
}
