package com.caiqianyi.soa.amqp.core.sender;



public interface IRabbitmqSender {
	
	public void sendContractDirect(String queue,Object message);
	
	public void sendContractTopic(String queue,Object message);
	
	public void sendContractFanout(String queue,Object message);
	
}