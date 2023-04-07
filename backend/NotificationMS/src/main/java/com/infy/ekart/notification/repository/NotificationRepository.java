package com.infy.ekart.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infy.ekart.notification.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, Integer> {

}
