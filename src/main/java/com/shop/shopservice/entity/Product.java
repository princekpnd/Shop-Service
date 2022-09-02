package com.shop.shopservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "PRODUCT")
@NamedQueries({ 
	@NamedQuery(name = "Product.findAll",
				query = "SELECT pd FROM Product pd"),
	@NamedQuery(name = "Product.findAllForUser",
				query = "SELECT pd FROM Product pd WHERE pd.isActive is TRUE and pd.isDeleted is FALSE"),
	@NamedQuery(name="Product.findByName",
				query="SELECT pd FROM Product pd WHERE pd.name= :name"),
	@NamedQuery(name="Product.findByShopId",
				query="SELECT pd FROM Product pd WHERE pd.shopId= :shopId and isActive is TRUE and isDeleted is FALSE"),
	@NamedQuery(name="Product.findProductByBrand",
				query="SELECT pd FROM Product pd WHERE pd.brand= :brand"),
	@NamedQuery(name="Product.findByCategory",
	            query="SELECT pd FROM Product pd WHERE pd.category= :category"),  
	@NamedQuery(name = "Product.findProductByShopId",
	            query = "SELECT pd FROM Product pd WHERE pd.shopId = :shopId and isActive is TRUE and isDeleted is FALSE "),
	@NamedQuery(name ="Product.findProductByShopIdForCategory",
	            query = "SELECT pd FROM Product pd WHERE pd.shopId =:shopId and pd.category =:category"),
	@NamedQuery(name ="Product.findProductByBrandName",
	             query ="SELECT pd FROM Product pd WHERE pd.brand = :brand"),
	@NamedQuery(name ="Product.findProductByProductId",
	             query = "SELECT pd FROM Product pd WHERE pd.productId =  :productId and  offerActiveInd is TRUE"),
	@NamedQuery(name="Product.ProductExistByShopId",
	            query="SELECT pd FROM Product pd WHERE pd.shopId= :shopId and pd.name = :name and pd.category = :category and pd.brand = :brand"),
	@NamedQuery(name ="Product.findOfferProduct",
	            query= "SELECT pd FROM Product pd WHERE pd.shopId = :shopId and offerActiveInd is TRUE"),
	@NamedQuery(name ="Product.findProductIdAndShopId",
			query = "SELECT pd FROM Product pd WHERE pd.productId =  :productId and shopId = :shopId"),
	@NamedQuery(name ="Product.findCurrentProduct",
	            query="SELECT pd FROM Product pd WHERE pd.shopId = :shopId and pd.name = :name and pd.category = :category and pd.brand = :brand"),
	@NamedQuery(name= "Product.findBuShopIdAndBrand",
	            query ="SELECT pd FROM Product pd WHERE pd.shopId = :shopId and pd.brand = :brand")
	
	})


public class Product  implements Serializable{
	private static final long serialVersionUID = 1385794955661915701L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "ID", nullable = false)
	private int productId;
	
	@Field(store = Store.NO)
	@NotNull
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "CATEGORY", nullable = false)
	private int category;
	
	@Column(name = "BRAND", nullable = false)
	private int brand;
	
	@Column(name = "SHOP_ID", nullable = false)
	private String shopId;
	
	@Column(name = "AVATAR", nullable = false)
	private String avatar;
	
	@Column(name = "PRICE", nullable = false)
	private float price;
	
	@Column(name = "QUANTITY", nullable = false)
	private int quantity;
	
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;
	
	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "BARCODE", nullable = false)
	private String barcode;
	
	@Column(name ="STOCK" ,nullable = false)
	private int stock;
	
	@Column(name ="SELLING_PRICE",nullable= false)
	private float sellingPrice;
	
	@Column(name ="COST_PRICE", nullable = false)
	private float costPrice;
	
	@Column(name ="OLD_PRICE",nullable =false)
	private float oldPrice;
	
	@Column(name ="OFFER_PERCENT",nullable= false)
	private int offerPercent;
	
	@Column(name ="OFFER_FROM",nullable= false)
	private Date offerFrom;
	
	@Column(name ="OFFER_TO",nullable= false)
	private Date offerTo;
	
	@Column(name ="OFFER_ACTIVE_IND",nullable= false)
	private boolean offerActiveInd;
	
	@Column(name ="CREATED_ON",nullable= false)
	private Date createdOn;
	
	@Column(name ="GST_AMOUNT",nullable= false)
	private float gstAmount;
	
	@Column(name ="MEASUREMENT",nullable= false)
	private String measurement;
	
	@Column(name ="DELIVERY_CHARGE",nullable= false)
	private float deliveryCharge;
	
	@Column(name ="GST_PERCENT",nullable= false)
	private float gstPercent;
	
	@Column(name ="DISCOUNT",nullable= false)
	private float discount;
	
	@Column(name ="DATE_OF_EXPIRE",nullable= false)
	private Date dateOfExpire;
	
	@Column(name ="DATE_OF_MANUFACTURING",nullable= false)
	private Date dateOfManufacturing;
	
	@Column(name ="OUT_OF_STOCK", nullable = false)
	private int outOfStock;
	
	@Column(name ="HSN_CODE", nullable = false)
	private String hsnCode;
	
	@Column(name ="BATCH_NUMBER", nullable = false)
	private String batchNumber;
	
	@Column(name ="LOT_NUMBER", nullable = false)
	private String lotNumber;
	
	@Transient
	private List<Image> image;
	
	public Product() {
		super();
	}
	
	
	public Product( int  brand ,String shopId) {
		this.shopId = shopId;
		this.brand = brand;
	}
	


	/**
	 * @return the image
	 */
	public List<Image> getImage() {
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(List<Image> image) {
		this.image = image;
	}


	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}


	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}


	/**
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}


	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * @return the brand
	 */
	public int getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(int brand) {
		this.brand = brand;
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
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	

	/**
	 * @return the descreption
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param descreption the descreption to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}


	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}


	


	/**
	 * @return the sellingPrice
	 */
	public float getSellingPrice() {
		return sellingPrice;
	}


	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	/**
	 * @return the costPrice
	 */
	public float getCostPrice() {
		return costPrice;
	}


	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
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
	 * @return the offerPercent
	 */
	public int getOfferPercent() {
		return offerPercent;
	}


	/**
	 * @param offerPercent the offerPercent to set
	 */
	public void setOfferPercent(int offerPercent) {
		this.offerPercent = offerPercent;
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
	 * @return the offerActiveInd
	 */
	public boolean isOfferActiveInd() {
		return offerActiveInd;
	}


	/**
	 * @param offerActiveInd the offerActiveInd to set
	 */
	public void setOfferActiveInd(boolean offerActiveInd) {
		this.offerActiveInd = offerActiveInd;
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
	 * @return the deliveryCharge
	 */
	public float getDeliveryCharge() {
		return deliveryCharge;
	}


	/**
	 * @param deliveryCharge the deliveryCharge to set
	 */
	public void setDeliveryCharge(float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}


	/**
	 * @return the gstPercent
	 */
	public float getGstPercent() {
		return gstPercent;
	}


	/**
	 * @param gstPercent the gstPercent to set
	 */
	public void setGstPercent(float gstPercent) {
		this.gstPercent = gstPercent;
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


	public Date getDateOfExpire() {
		return dateOfExpire;
	}


	public Date getDateOfManufacturing() {
		return dateOfManufacturing;
	}


	public void setDateOfManufacturing(Date dateOfManufacturing) {
		this.dateOfManufacturing = dateOfManufacturing;
	}


	public int getOutOfStock() {
		return outOfStock;
	}


	public void setOutOfStock(int outOfStock) {
		this.outOfStock = outOfStock;
	}


	public void setDateOfExpire(Date dateOfExpire) {
		this.dateOfExpire = dateOfExpire;
	}


	public String getHsnCode() {
		return hsnCode;
	}


	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}


	public String getBatchNumber() {
		return batchNumber;
	}


	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}


	public String getLotNumber() {
		return lotNumber;
	}


	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}





	
}
