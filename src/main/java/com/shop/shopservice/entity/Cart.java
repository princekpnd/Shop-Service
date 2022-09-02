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
@Table(name = "CART")
@NamedQueries({ 
	@NamedQuery(name = "Cart.findAll",
				query = "SELECT cr FROM Cart cr"),
	 @NamedQuery(name = "Cart.findCartByUserId",
		        query = "SELECT cr FROM Cart cr WHERE cr.userId = :userId"),
	 @NamedQuery(name="Cart.findByUserId",
                query="SELECT cr FROM Cart cr WHERE cr.userId = :userId"),
	 @NamedQuery(name = "Cart.findCartForUserByShopId",
	            query = "SELECT cr FROM Cart cr WHERE cr.shopId= :shopId"),
	 @NamedQuery(name = "Cart.findCartForUserByUserId",
                 query = "SELECT cr FROM Cart cr WHERE cr.userId= :userId"),
	@NamedQuery(name ="Cart.findByOrderActiveUserId",
	            query ="SELECT cr FROM Cart  cr WHERE cr.userId = :userId  and cr.shopId = :shopId and  cr.orderStatus = :orderStatus"),
	@NamedQuery(name ="Cart.findDeactiveCart",
                query ="SELECT cr FROM Cart  cr WHERE cr.userId = :userId  and cr.shopId = :shopId and  isActive is FALSE"),
	@NamedQuery(name ="Cart.findCartForOrder",
	            query ="SELECT cr FROM Cart cr WHERE cr.cartId =:cartId and cr.isActive is TRUE"),
	@NamedQuery(name ="Cart.findOrderStatus",
               query ="SELECT cr FROM Cart cr WHERE cr.cartId =:cartId and cr.shopId = :shopId "),
	@NamedQuery(name ="Cart.orderDetails",
                query ="SELECT cr FROM Cart cr WHERE cr.shopId =:shopId and cr.userId = :userId and cr.orderStatus = :orderStatus "),
	@NamedQuery(name ="Cart.findDeactiveCartByUserId",
			     query ="SELECT cr FROM Cart cr WHERE cr.shopId =:shopId and cr.userId = :userId and cr.orderStatus = :orderStatus"),
	@NamedQuery(name ="Cart.findCartByShopIdAndCartId",
	             query = "SELECT cr FROM Cart cr WHERE cr.shopId = :shopId and cr.cartId = :cartId"),
	@NamedQuery(name ="Cart.findByShopId",
			 query = "SELECT cr FROM Cart cr WHERE cr.shopId = :shopId"),

	 
})

public class Cart implements Serializable {

	private static final long serialVersionUID = 1385794955661915701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int cartId;
	
	@Column(name = "USER_ID", nullable = false)
	private String userId;	

	@Column(name = "SHOP_ID", nullable = false)
	private String shopId;	

	@Column(name = "CREATED_ON", nullable = false)
	private Date createdOn;

	@Column(name = "GST_AMOUNT", nullable = false)
	private float gstAmount;

	@Column(name = "TOTAL_AMOUNT", nullable = false)
	private float totalAmount;
	
	@Column(name = "TRANSACTION_ID", nullable = false)
	private String transactionId;
	
	@Column(name = "PAID", nullable = false)
	private float paid;

	@Column(name = "DUES", nullable = false)
	private float dues;

	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;

	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;

	@Column(name = "ORDER_STATUS", nullable = false)
	private int orderStatus;
	
	@Column(name = "TRANSACTION_TYPE", nullable = false)
	private int transactionType;
	
	@Column(name = "ORDER_DATE", nullable = false)
	private Date orderDate;
	
	@Column(name = "DELIVERY_CHARGE", nullable = false)
	private float deliveryCharge;
	
	@Column(name = "DISCOUNT" , nullable = false)
	private float discount;
	
	
	@Column(name = "PRICE" , nullable = false)
	private float price;
	
	@Column(name = "ORDER_TYPE" , nullable = false)
	private int orderType;
	
	@Column(name = "USER_NAME" , nullable = false)
	private String userName;
	
	@Column(name = "MOBILE_NUMBER" , nullable = false)
	private String mobileNo;
	
	@Column(name = "ADDRESS_ID" , nullable = false)
	private int addressId;
	
	@Column(name ="SLOT_DATE" , nullable = false)
	private Date slotDate;
	
	@Column(name ="DESCRIPTION" , nullable = false)
	private String description;
	
	@Column(name ="SHIPPING_ID" , nullable = false)
	private String shippingId;
	
	@Column(name ="DELIVERY_TYPE" , nullable = false)
	private int deliveryType;
	
	@Column(name = "SHIPPING_DATE" , nullable = false)
	private Date shippingDate;
	
	@Column(name = "DELIVERY_DATE" , nullable = false)
	private Date deliveryDate;
	
	@Column(name = "SHIPPING_NAME" , nullable = false)
	private String shippingName;
	
	@Column(name = "D_BOY_NAME" , nullable = false)
	private String dBoyName;
	
	@Column(name = "D_BOY_NUMBER" , nullable = false)
	private String dBoyNumber;
	
	@Column(name = "DELIVERY_TIME" , nullable = false)
	private Date  deliveryTime;
	
	@Column(name = "COURIER_NAME" , nullable = false)
	private String  courierName;
	
	@Column(name = "REVIEW" , nullable = false)
	private String review;
	
	@Column(name = "SHOP_NAME" , nullable = false)
	private String  shopName;
	
	@Column(name = "ADMIN_ID" , nullable = false)
	private String  adminId;
	
	@Column(name = "PAYABLE_AMOUNT" , nullable = false)
	private float payableAmount;
	
	@Column(name = "OTP" , nullable = false)
	private int otp;
	
//	@Column(name = "MOBILE_NUMBER" , nullable = false)
//	private String mobileNo;
	
	
	
	


	public Cart() {
		super();
	}
	
	public Cart(String userId, String shopId) {
		this.userId = userId;
		this.shopId = shopId;
	}
	

	/**
	 * @return the transactionType
	 */
	public int getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the gstAmount
	 */
	public float getGstAmount() {
		return gstAmount;
	}

	/**
	 * @param gstAmount the gstAmount to set
	 */
	public void setGstAmount(float gstAmount) {
		this.gstAmount = gstAmount;
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



	
	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @param deliveryCharge the deliveryCharge to set
	 */
	public void setDeliveryCharge(float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	/**
	 * @return the discount
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	public String getMobileName() {
//		return mobileName;
//	}
//
//	public void setMobileName(String mobileName) {
//		this.mobileName = mobileName;
//	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public Date getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShippingId() {
		return shippingId;
	}

	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}

	public int getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getdBoyName() {
		return dBoyName;
	}

	public void setdBoyName(String dBoyName) {
		this.dBoyName = dBoyName;
	}

	public String getdBoyNumber() {
		return dBoyNumber;
	}

	public void setdBoyNumber(String dBoyNumber) {
		this.dBoyNumber = dBoyNumber;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public float getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(float payableAmount) {
		this.payableAmount = payableAmount;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public float getDeliveryCharge() {
		return deliveryCharge;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	

}
