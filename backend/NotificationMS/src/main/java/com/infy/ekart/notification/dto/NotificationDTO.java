package com.infy.ekart.notification.dto;

public class NotificationDTO {
	private Long id;
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
