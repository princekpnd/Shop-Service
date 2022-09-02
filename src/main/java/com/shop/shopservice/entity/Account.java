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
@Table(name = "ACCOUNT")
@NamedQueries({ @NamedQuery(name = "Account.findAll",
                query = "SELECT ac FROM Account ac"),
	@NamedQuery(name = "Account.findAccountByUserId",
	            query = "SELECT ac FROM Account ac WHERE ac.userId = :userId"),
	 @NamedQuery(name="Account.findByBankName",
		        query="SELECT ac FROM Account ac WHERE ac.bankName = :bankName"),
	 @NamedQuery(name = "Account.findAccountByMobileNumber",
		        query = "SELECT ac FROM Account ac WHERE ac.mobileNo = :mobileNo"),
	 @NamedQuery(name = "Account.findAccountByAccountNumber",
		        query = "SELECT ac FROM Account ac WHERE ac.accountNum = :accountNum"),
	 @NamedQuery(name="Account.findByUserId",
		         query="SELECT ac FROM Account ac WHERE ac.userId = :userId"),
	 @NamedQuery(name = "Account.findAccountByShopId",
	             query = "SELECT ac FROM Account ac WHERE ac.shopId = :shopId "),
	 @NamedQuery(name = "Account.findByUser",
	             query = "SELECT ac FROM Account ac WHERE ac.userId = :userId and isActive is TRUE"),
	
})



public class Account implements Serializable{
	
	private static final long serialVersionUID = 1385794955661915701L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "USER_ID", nullable = false)
	private int userId;
	
	@Column(name = "USER_TYPE", nullable = false)
	private int userType;
	
	@Column(name = "ACCOUNT_NUM", nullable = false)
	private int accountNum;
	
	
	@Column(name = "IFSC", nullable = false)
	private String ifsc;
	
	@Column(name = "BANK_NAME", nullable = false)
	private String bankName;
	
	@Column(name = "CARD_NUM", nullable = false)
	private int cardNum;

	@Column(name = "CARD_TYPE", nullable = false)
	private int cardType;
	
	@Column(name = "EXPIRY_DATE", nullable = false)
	private Date expiryDate;
	
	@Column(name = "CURRENCY", nullable = false)
	private int currency;
	
	
	@Column(name = "MOBILE_NUMBER", nullable = false)
	private String mobileNo;
	
	@Column(name = "SHOP_ID", nullable = false)
	private String shopId;
	
	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;
	
	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "CREATED_ON", nullable = false)
	private Date createdOn;
	
	@Column(name = "ACCOUNT_HOLDER_NAME", nullable = false)
	private String accountHolderName;
	
	public Account() {
		super();
	}
	
	public Account(int userId, String accountHolderName) {
		this.userId = userId;
		this.accountHolderName = accountHolderName;
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
	 * @return the accountNum
	 */
	public int getAccountNum() {
		return accountNum;
	}

	/**
	 * @param accountNum the accountNum to set
	 */
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	/**
	 * @return the ifsc
	 */
	public String getIfsc() {
		return ifsc;
	}

	/**
	 * @param ifsc the ifsc to set
	 */
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	
	/**
	 * @return the cardNum
	 */
	public int getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * @return the cardType
	 */
	public int getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the currency
	 */
	public int getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(int currency) {
		this.currency = currency;
	}


	

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
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

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	

//	@PrePersist
//    void preInsert() {
//       PrePersistUtil.pre(this);
//    }
}
