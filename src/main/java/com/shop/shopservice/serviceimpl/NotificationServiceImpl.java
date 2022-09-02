package com.shop.shopservice.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.shopservice.Idao.INotificationDAO;
import com.shop.shopservice.Idao.IOfflineDAO;
import com.shop.shopservice.entity.Notification;
import com.shop.shopservice.service.INotificationService;
import com.shop.shopservice.service.IOfflineService;
@Transactional
@Repository
public class NotificationServiceImpl implements INotificationService{
	@Autowired
	INotificationDAO  notificationDao;

	@Override
	public List<Notification> getAll() {
		return notificationDao.getAll();
	}

	@Override
	public boolean createNotification(int articleId, int notificationType) {
		return notificationDao.createNotification(articleId,notificationType);
	}
}
