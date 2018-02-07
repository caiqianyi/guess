package com.lebaoxun.soa.amqp.core;

public enum AmqpExchange {
	
	FANOUT("sys.fanout"),TOPIC("sys.topic"),HEADERS("sys.headers"),DIRECT("sys.direct");
	
	private String value;
	
	private AmqpExchange(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}