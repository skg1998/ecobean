package com.infy.ekart.notification.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.infy.ekart.notification.config.MessageQueueConfig;
import com.infy.ekart.notification.dto.NotificationDTO;

@Component
public class Consumer {
	
	@RabbitListener(queues = MessageQueueConfig.QUEUE)
	public void consumeMessageFromQueue(NotificationDTO notificationDTO) {
		
	}
}
