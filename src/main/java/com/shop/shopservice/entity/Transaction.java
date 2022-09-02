package com.shop.shopservice.entity;

import java.io.Serializable;
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
@Table(name = "TRANSACTION")
@NamedQueries({ 
	@NamedQuery(name = "Transaction.findAll",
				query = "SELECT tr FROM Transaction tr"),
	@NamedQuery(name = "Transaction.findTransactionByUserId",
				query = "SELECT tr FROM Transaction tr WHERE tr.userId = :userId"),
	@NamedQuery(name = "Transaction.findTransactionByShopId",
	            query = "SELECT tr FROM Transaction tr WHERE tr.shopId = :shopId"),
	@NamedQuery(name = "Transaction.findTransactionByTransactionId",
                query = "SELECT tr FROM Transaction tr WHERE tr.transactionId = :transactionId"),
	@NamedQuery(name="Transaction.findByShopId",
	            query="SELECT tr FROM Transaction tr WHERE tr.shopId= :shopId and isActive is TRUE and isDeleted is FALSE"),
	@NamedQuery(name ="Transaction.findTransactionForUserByShopId",
	            query ="SELECT tr FROM Transaction tr WHERE tr.shopId =:shopId and isActive is TRUE and isDeleted is FALSE "),
	@NamedQuery(name ="Transaction,findByCartId",
	            query = "SELECT tr FROM Transaction tr WHERE tr.cartId = :cartId"),
	@NamedQuery(name ="Transaction, findActiveTransaction",
	            query = "SELECT tr FROM Transaction tr WHERE tr.userId = :userId and tr.transactionType = :transactionType and tr.isActive = :isActive ")
	})


public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1385794955661915701L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "USER_ID", nullable = false)
	private int userId;
	
	@Column(name = "SHOP_ID", nullable = false)
	private String shopId;
	
	@Column(name = "PAYMENT_MODE", nullable = false)
	private int paymentMode;
	
	@Column(name = "AMOUNT", nullable = false)
	private int amount;
	
	@Column(name = "CREATED_ON", nullable = false)
	private Date createdOn;
	
	@Column(name = "TRANSACTION_ID", nullable = false)
	private String transactionId;
	
	@Column(name = "USER_TYPE", nullable = false)
	private int userType;
	
	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;
	
	@Column(name = "TOTAL_AMOUNT", nullable = false)
	private float totalAmount;
	
	@Column(name = "PAID", nullable = false)
	private float paid;
	
	@Column(name = "DUES", nullable = false)
	private float dues;
	
	@Column(name = "CART_ID", nullable = false)
	private int cartId;
	
	@Column(name = "TRANSACTION_TYPE", nullable = false)
	private int transactionType;
	
	@Column(name = "TRANSACTION_STATUS", nullable = false)
	private int transactionStatus;
	
	@Column(name = "ORDER_RCPTID_ID", nullable = false)
	private String orderRcptidId;
	
	@Column(name = "PURCHASE_ID", nullable = false)
	private int  purchaseId;
	
	@Column(name = "WITHDRAW_ID", nullable = false)
	private int  withdrawId;
	
	@Column(name = "ADMIN_ID", nullable = false)
	private int  adminId;
	
	@Column(name = "DISCREPTION", nullable = false)
	private String  discreption;
	
	public Transaction() {
		super();
	}
	
	
	public Transaction(String shopId, int userId) {
		this.shopId = shopId;
		this.userId = userId;
	}

	/**
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}


	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}


	/**
	 * @return the paid
	 */
	public float getPaid() {
		return paid;
	}


	/**
	 * @param paid the paid to set
	 */
	public void setPaid(float paid) {
		this.paid = paid;
	}


	/**
	 * @return the dues
	 */
	public float getDues() {
		return dues;
	}


	/**
	 * @param dues the dues to set
	 */
	public void setDues(float dues) {
		this.dues = dues;
	}


	/**
	 * @return the cartId
	 */
	public int getCartId() {
		return cartId;
	}


	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * @return the shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return the paymentMode
	 */
	public int getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(int paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the userType
	 */
	public int getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}


	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}


	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}


	public int getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}


	public int getTransactionStatus() {
		return transactionStatus;
	}


	public void setTransactionStatus(int transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


	public String getOrderRcptidId() {
		return orderRcptidId;
	}


	public void setOrderRcptidId(String orderRcptidId) {
		this.orderRcptidId = orderRcptidId;
	}


	public int getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}


	public int getWithdrawId() {
		return withdrawId;
	}


	public void setWithdrawId(int withdrawId) {
		this.withdrawId = withdrawId;
	}


	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getDiscreption() {
		return discreption;
	}


	public void setDiscreption(String discreption) {
		this.discreption = discreption;
	}

	
}
