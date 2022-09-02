package com.shop.shopservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION_OLD")
//@NamedQueries({ @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
//		@NamedQuery(name = "Notification.findByCatagory", query = "SELECT n FROM Notification n WHERE n.catagory = :catagoryId"),
//		@NamedQuery(name = "Notification.findBySubCatagory", query = "SELECT n FROM Notification n WHERE n.subCatagoryList = :subCatagoryList"),
//		@NamedQuery(name = "Notification.findByUserId", query = "SELECT n FROM Notification n WHERE n.userId = :userId"), })
public class NotificationOld {

	@Id
	@Column(name = "NOTIFICATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public NotificationOld() {
		super();
	}

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "ARTICLE_ID")
	private int articleId;
	
	@Column(name = "TIMELINE_ID")
	private int timelineId;

	@Column(name = "SUMMERY_DETAILS")
	private String summeryDetails;

	@Column(name = "CATEGORY")
	private int catagory;

	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "SUB_CATAGORY")
	private String subCatagoryList;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "CREATED_ON")
	private Date createdOn;
	
	transient String userName;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the summeryDetails
	 */
	public String getSummeryDetails() {
		return summeryDetails;
	}

	/**
	 * @param summeryDetails the summeryDetails to set
	 */
	public void setSummeryDetails(String summeryDetails) {
		this.summeryDetails = summeryDetails;
	}

	/**
	 * @return the catagory
	 */
	public int getCatagory() {
		return catagory;
	}

	/**
	 * @param catagory the catagory to set
	 */
	public void setCatagory(int catagory) {
		this.catagory = catagory;
	}

	/**
	 * @return the subCatagoryList
	 */
	public String getSubCatagoryList() {
		return subCatagoryList;
	}

	/**
	 * @param subCatagoryList the subCatagoryList to set
	 */
	public void setSubCatagoryList(String subCatagoryList) {
		this.subCatagoryList = subCatagoryList;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(int timelineId) {
		this.timelineId = timelineId;
	}

}