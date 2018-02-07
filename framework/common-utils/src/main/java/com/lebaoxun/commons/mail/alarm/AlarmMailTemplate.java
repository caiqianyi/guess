package com.lebaoxun.commons.mail.alarm;

import java.util.Arrays;
import java.util.List;

public enum AlarmMailTemplate {
	
	//玩家转卡异常冻结 邮件预警
	AgentPlayerChargeFreezeAccount("6b561a12ce8b136d2b472f65204714c3","【异常转卡】已冻结账号","%s 账号在 %s 触发安全限制 %s，已经被冻结48小时，请及时处理","pingtaiyujing@ixianlai.com,luanjiayujing@ixianlai.com,zhangxueshuang@ixianlai.com,guxiuji@ixianlai.com,huhaiyan@ixianlai.com,wanjun@ixianlai.com"),
	AgentPlayerChargeFreezeAccountPending("3bb80cc1937e43f2884a74bbc64de44d","【异常转卡】待冻结账号通知 ","%s 账号在 %s 触发安全限制 %s，请及时处理","pingtaiyujing@ixianlai.com,luanjiayujing@ixianlai.com");
	
	private String id;
	private String subject;
	private String content;
	private String recipients;
	
	private AlarmMailTemplate(String id,String subject,String content,String recipients) {
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.recipients = recipients;
	}
	
	public String getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getContent(Object ...args) {
		return content.contains("%s")? String.format(content, args) : getContent();
	}
	
	public String getRecipients() {
		return recipients;
	}
	
	public String getSubject() {
		return subject;
	}

	public static AlarmMailTemplate valueOfTemplate(String id){
		List<AlarmMailTemplate> list = Arrays.asList(AlarmMailTemplate.values());
		for(AlarmMailTemplate template : list){
			if(template.getId().equals(id)){
				return template;
			}
		}
		return null;
	}
}
