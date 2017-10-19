package com.caiqianyi.commons.mail.alarm;

import java.util.List;

public class AlarmMailMQMessage {
	
	private String templte_id;
	private List<String> datas;
	
	public String getTemplte_id() {
		return templte_id;
	}
	public void setTemplte_id(String templte_id) {
		this.templte_id = templte_id;
	}
	public List<String> getDatas() {
		return datas;
	}
	public void setDatas(List<String> datas) {
		this.datas = datas;
	}
	
}
