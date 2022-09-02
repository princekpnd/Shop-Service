package com.shop.shopservice.Idao;

import java.util.List;

import com.shop.shopservice.entity.NotificationOld;

public interface INotificationOldDAO {

	public List<NotificationOld> getAllNotification(int count);
	public List<NotificationOld> getAllNotificationOnCatagory(int catagory);
	public List<NotificationOld> getAllNotificationOnSubCatagory(List<Integer> subCatagoryList);
	public void createNotification(NotificationOld notification);
	public void updateNotification(NotificationOld notification);
	public NotificationOld getNotificationById(long notificationId);
	public List<NotificationOld> getNotificationByUserId(int userId);
	
}
