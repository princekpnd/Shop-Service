package com.shop.shopservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.shop.shopservice.constants.ServiceConstants;
import com.shop.shopservice.entity.Notification;
import com.shop.shopservice.service.INotificationService;
import com.shop.shopservice.service.IProductService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	private final Logger log = LoggerFactory.getLogger(NotificationController.class);
	@Autowired
	private INotificationService notificationService;
	
	@GetMapping("getall")
	public ResponseEntity<List<Notification>> getAllNotification(){
		List<Notification> notificationList = notificationService.getAll();
		return new ResponseEntity<List<Notification>>(notificationList, HttpStatus.OK);
	}
	
	@SuppressWarnings({ })
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createBank(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to create user: {}", json.get(ServiceConstants.ACCOUNT_NUM));
		Map<String, String> response = new HashMap<String, String>();
		boolean result = notificationService.createNotification(10,12);
		if(result) {
			response.put("status", Boolean.TRUE.toString());
			response.put("descreption", "Notification created");
		}else {
			response.put("status", Boolean.FALSE.toString());
			response.put("descreption", "Notification not created");
		}
		return ResponseEntity.ok().body(response);
	}
	

}
