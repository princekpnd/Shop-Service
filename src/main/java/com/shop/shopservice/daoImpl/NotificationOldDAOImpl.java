package com.shop.shopservice.daoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shopservice.Idao.INotificationOldDAO;
import com.shop.shopservice.entity.NotificationOld;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.service.IUserService;


@Repository
@Transactional
public class NotificationOldDAOImpl implements INotificationOldDAO {

	@PersistenceContext	
	private EntityManager entityManager;

	
	@Override
	public List<NotificationOld> getAllNotification(int count) {
		return entityManager.createNamedQuery("Notification.findAll", NotificationOld.class).setMaxResults(count).getResultList();
	}

	@Override
	public NotificationOld getNotificationById(long notificationId) {
		NotificationOld notification =  this.entityManager.find(NotificationOld.class, notificationId);
		return notification;
	}

	@Override
	public void createNotification(NotificationOld notification) {
		notification.setCreatedOn(new Date());
		entityManager.persist(notification);

	}

	@Override
	public void updateNotification(NotificationOld notification) {
	entityManager.merge(notification);
	}

	
	@Override
	public List<NotificationOld> getNotificationByUserId(int userId) {
			return (List<NotificationOld>)entityManager.createNamedQuery("Notification.findByUserId",NotificationOld.class).setParameter("userId", userId).getResultList();
	}

	@Override
	public List<NotificationOld> getAllNotificationOnCatagory(int catagory) {
		return (List<NotificationOld>)entityManager.createNamedQuery("Notification.findByCatagory",NotificationOld.class).setParameter("catagoryId", catagory).getResultList();
	}

	@Override
	public List<NotificationOld> getAllNotificationOnSubCatagory(List<Integer> subCatagoryList) {
		// TODO Auto-generated method stub
		return null;
	}


}
