package com.crypto.dashboard.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Message {
	@JsonProperty("Message")
	private String msg;
	
	public Message(String msg){
		this.msg = msg;
	}
}
