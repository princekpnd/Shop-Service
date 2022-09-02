package com.shop.shopservice.Idao;

import java.util.List;

import com.shop.shopservice.entity.Notification;

public interface INotificationDAO {
List<Notification> getAll();
public boolean createNotification(int articleId, int notificationType);
}
