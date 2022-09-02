package com.shop.shopservice.service;

import java.util.List;

import com.shop.shopservice.entity.Notification;
public interface INotificationService {
List<Notification> getAll();
public boolean createNotification(int articleId, int notificationType);
}
