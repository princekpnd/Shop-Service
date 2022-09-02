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
@Table(name = "PRODUCT_LIST")
@NamedQueries({
	@NamedQuery(name = "ProductList.findAll",
	           query = "SELECT pdl FROM ProductList pdl"),
	@NamedQuery(name="ProductList.findProductListByUserId",
	            query="SELECT pdl FROM ProductList pdl WHERE pdl.userId =:userId"),
	@NamedQuery(name ="ProductList.findProductListByShopId",
	            query = "SELECT pdl FROM ProductList pdl WHERE pdl.shopId =:shopId"),
	@NamedQuery(name ="ProductList.findByShopId",
	            query = "SELECT pdl FROM ProductList pdl WHERE pdl.productId =:shopId"),
	@NamedQuery(name ="ProductList.findProductListByProductId",
                query = "SELECT pdl FROM ProductList pdl WHERE pdl.productId =:productId and pdl.cartId =:cartId"),
	@NamedQuery(name ="ProductList.findProductListByCartId",
                 query = "SELECT pdl FROM ProductList pdl WHERE pdl.cartId = :cartId")

})
public class ProductList  implements Serializable{
	private static final long serialVersionUID = 1385794955661915701L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "ID", nullable = false)
	private int id;
		
	@Column(name ="SHOP_ID" ,nullable =false)
	private String shopId;
	
	@Column(name ="USER_ID",nullable =false)
	private String userId;
	
	@Column(name = "PRODUCT_ID",nullable =false)
	private String productId;
	
	@Column(name ="PRODUCT_NAME",nullable =false)
	private String productName;
	
	@Column(name ="CART_ID",nullable =false)
	private int cartId;
	
	@Column(name ="CREATED_ON",nullable =false)
	private Date createdOn;
	
	@Column(name ="PRODUCT_QUANTITY",nullable =false)
	private float productQuantity;
	
	@Column(name ="PRICE",nullable =false)
	private float price;
	
	@Column(name ="IS_ACTIVE",nullable =false)
	private boolean isActive;
	
	@Column(name ="IS_DELETED",nullable =false)
	private boolean isDeleted;
	
	@Column(name ="OFFERS_AVAILABLE",nullable =false)
	private boolean offersAvailable ;
	
	@Column(name ="OLD_PRICE",nullable =false)
	private float oldPrice ;
	
	@Column(name ="DISCOUNT",nullable =false)
	private float discount ;
	
	@Column(name ="DELIVERY_CHARGE",nullable =false)
	private Float deliveryCharge ;

	
	@Column(name ="OFFER",nullable =false)
	private int offer;
	
	@Column(name ="OFFER_TO",nullable =false)
	private Date offerTo;
	
	@Column(name ="OFFER_FROM",nullable =false)
	private Date offerFrom;
	
	@Column(name ="GST_AMOUNT",nullable =false)
	private float gstAmount;
	
	@Column(name ="MEASUREMENT",nullable =false)
	private String measurement;
	
	@Column(name ="TOTAL_AMOUNT",nullable =false)
	private float totalAmount;
	
	@Column(name ="GST_PERCENT",nullable =false)
	private int gstPrecent;
	
	@Column(name ="STOCK",nullable =false)
	private int stock;




	public ProductList() {
		super();
	}
	
	public ProductList(int cartId,String shopId) {
		this.cartId = cartId;
		this.shopId = shopId;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * @return the productQuantity
	 */
	public float getProductQuantity() {
		return productQuantity;
	}

	/**
	 * @param productQuantity the productQuantity to set
	 */
	public void setProductQuantity(float productQuantity) {
		this.productQuantity = productQuantity;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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

	/**
	 * @return the offer
	 */
	public int getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(int offer) {
		this.offer = offer;
	}



	/**
	 * @return the offersAvailable
	 */
	public boolean getOffersAvailable() {
		return offersAvailable;
	}

	/**
	 * @param offersAvailable the offersAvailable to set
	 */
	public void setOffersAvailable(boolean offersAvailable) {
		this.offersAvailable = offersAvailable;
	}

	/**
	 * @return the oldPrice
	 */
	public float getOldPrice() {
		return oldPrice;
	}

	/**
	 * @param oldPrice the oldPrice to set
	 */
	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
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

	
	/**
	 * @return the deliveryCharge
	 */
	public Float getDeliveryCharge() {
		return deliveryCharge;
	}

	/**
	 * @param deliveryCharge the deliveryCharge to set
	 */
	public void setDeliveryCharge(Float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	/**
	 * @return the offerTo
	 */
	public Date getOfferTo() {
		return offerTo;
	}

	/**
	 * @param offerTo the offerTo to set
	 */
	public void setOfferTo(Date offerTo) {
		this.offerTo = offerTo;
	}

	/**
	 * @return the offerFrom
	 */
	public Date getOfferFrom() {
		return offerFrom;
	}

	/**
	 * @param offerFrom the offerFrom to set
	 */
	public void setOfferFrom(Date offerFrom) {
		this.offerFrom = offerFrom;
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
	 * @return the measurement
	 */
	public String getMeasurement() {
		return measurement;
	}

	/**
	 * @param measurement the measurement to set
	 */
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
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

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getGstPrecent() {
		return gstPrecent;
	}

	public void setGstPrecent(int gstPrecent) {
		this.gstPrecent = gstPrecent;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	
}
