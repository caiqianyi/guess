package com.lebaoxun.agent.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	@Autowired
	public HttpServletRequest request;
	@Autowired
	public HttpServletResponse response;

}
