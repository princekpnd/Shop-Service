package com.shop.shopservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopservice.Idao.IConsultationWorkflowDAO;
import com.shop.shopservice.Idao.INotificationOldDAO;
import com.shop.shopservice.constants.ServiceConstants;
import com.shop.shopservice.entity.ConsultationWorkflow;
import com.shop.shopservice.entity.NotificationOld;
import com.shop.shopservice.service.IUserService;

@RestController
@RequestMapping("/api/consultationworkflow")
public class ConsultationWorkflowController {

	@Autowired
	INotificationOldDAO NotificationDAO;

	@Autowired
	IUserService userService;
	
	@Autowired
	private IConsultationWorkflowDAO ConsultationWorkflowDAO;

	private final Logger log = LoggerFactory.getLogger(ConsultationWorkflowController.class);
	@GetMapping("getallnotification")
	public ResponseEntity<List<NotificationOld>> getAllNotification() {
		return null;
	}

	@GetMapping("getallworkflow/{consultationId}")
	public ResponseEntity<List<ConsultationWorkflow>> getallworkflow(
			@PathVariable("consultationId") String consultationId) {
		List<ConsultationWorkflow> listworkflow =ConsultationWorkflowDAO.getConsultationWorkflowByConsultantId(Integer.parseInt(consultationId));
		return new ResponseEntity<List<ConsultationWorkflow>>(listworkflow, HttpStatus.OK);
	}

	@GetMapping("getallworkflowbyuser/{userId}")
	public ResponseEntity<List<NotificationOld>> findUserNotification(@PathVariable("userId") String userId) {
		List<NotificationOld> notificationList = NotificationDAO.getNotificationByUserId(Integer.parseInt(userId));
		return new ResponseEntity<List<NotificationOld>>(notificationList, HttpStatus.OK);
	}

	@PostMapping("create")
	ResponseEntity<Map<String, String>> initiateworkflow(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) {
		log.info("Request to validate user: {}", json.get(ServiceConstants.CONSULTANTID));
		Map<String, String> response = new HashMap<String, String>();
//		response.put(ServiceConstants.EMAILID, json.get(ServiceConstants.EMAILID));
		ConsultationWorkflow cw = ConsultationWorkflowDAO.getConsultationWorkflowById(
				Integer.parseInt(json.get(ServiceConstants.USERID)),
				Integer.parseInt(json.get(ServiceConstants.CONSULTANTID)));
		if (null != cw) {
			log.info("workflow already exist for " + json.get(ServiceConstants.USERID) + " and "
					+ json.get(ServiceConstants.CONSULTANTID));
			response.put("status", "workflow already exist for " + json.get(ServiceConstants.USERID) + " and "
					+ json.get(ServiceConstants.CONSULTANTID));
			cw.setAccepted(Boolean.TRUE);
			ConsultationWorkflowDAO.updateConsultationWorkflowById(cw);
		} else {
			ConsultationWorkflow consultationWorkflow = new ConsultationWorkflow(
					Integer.parseInt(json.get(ServiceConstants.USERID)),
					Integer.parseInt(json.get(ServiceConstants.CONSULTANTID)),
					Integer.parseInt(json.get(ServiceConstants.PRICE)), Boolean.TRUE);
			ConsultationWorkflowDAO.initiateConsultationWorkflow(consultationWorkflow);
			response.put("status", "workflow created between " + json.get(ServiceConstants.USERID) + " and "
					+ json.get(ServiceConstants.CONSULTANTID));
		}
		return ResponseEntity.ok().body(response);
	}
}
