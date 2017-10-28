package com.caiqianyi.agent.security;

import java.util.List;

public class Authority {
	
	private String role;
	
	private List<Navigation> navs;

	public List<Navigation> getNavs() {
		return navs;
	}

	public void setNavs(List<Navigation> navs) {
		this.navs = navs;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
