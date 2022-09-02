package com.shop.shopservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shop.shopservice.Idao.INotificationOldDAO;
import com.shop.shopservice.entity.NotificationOld;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.service.IUserService;

@RestController
@RequestMapping("/api/notification")
public class NotificationOldController {

	@Autowired
	INotificationOldDAO NotificationDAO;

	@Autowired
	IUserService userService;

	@GetMapping("getallnotification")
	public ResponseEntity<List<NotificationOld>> getAllNotification() {
		List<NotificationOld> nlist = NotificationDAO.getAllNotification(500);
		return new ResponseEntity<List<NotificationOld>>(nlist, HttpStatus.OK);
	}

	@GetMapping("getallnotification/catagory/{categoryId}")
	public ResponseEntity<List<NotificationOld>> getAllNotificationOnCatagory(
			@PathVariable("categoryId") String categoryId) {
		List<NotificationOld> listNotification = NotificationDAO
				.getAllNotificationOnCatagory(Integer.parseInt(categoryId));
		return new ResponseEntity<List<NotificationOld>>(listNotification, HttpStatus.OK);
	}

	@GetMapping("getallnotification/user/{userId}")
	public ResponseEntity<List<NotificationOld>> findUserNotification(@PathVariable("userId") String userId) {
		List<NotificationOld> notificationList = NotificationDAO.getNotificationByUserId(Integer.parseInt(userId));
		return new ResponseEntity<List<NotificationOld>>(notificationList, HttpStatus.OK);
	}

	@GetMapping("getnotification/{notificationId}")
	public ResponseEntity<NotificationOld> getNotificationByid(@PathVariable("notificationId") Long notificationId) {
		NotificationOld n = NotificationDAO.getNotificationById(notificationId);
		User user = userService.getUser(n.getUserId());
		n.setUserName(user.getName());
		return new ResponseEntity<NotificationOld>(n, HttpStatus.OK);
	}
}
