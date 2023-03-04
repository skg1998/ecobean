package com.infy.ekart.notification.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.infy.ekart.notification.dto.MessageType;
import com.infy.ekart.notification.dto.NotificationType;

@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated
	private NotificationType type;
	private MessageType message;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public NotificationType getType() {
		return type;
	}
	public void setType(NotificationType type) {
		this.type = type;
	}
	public MessageType getMessage() {
		return message;
	}
	public void setMessage(MessageType message) {
		this.message = message;
	}
}
