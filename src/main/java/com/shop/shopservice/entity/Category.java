package com.shop.shopservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@NamedQueries({ 
	@NamedQuery(name = "Category.findAll",
				query = "SELECT cat FROM Category cat"),
	@NamedQuery(name="Category.findCategoryForUserByShopId",
			    query="SELECT cat FROM Category cat WHERE cat.shopId = :shopId and cat.isActive is TRUE and cat.isDeleted is FALSE"),
	@NamedQuery(name="Category.findCategoryByName",
                query="SELECT cat FROM Category cat WHERE cat.name= :name and cat.shopId = :shopId"),
	@NamedQuery(name="Category.findCategoryForUser",
	             query="SELECT cat FROM Category cat WHERE cat.isActive is TRUE and cat.isDeleted is FALSE"),
	@NamedQuery(name = "Category.findCategoryByShopId",
	             query = "SELECT cat FROM Category cat WHERE cat.shopId= :shopId"),
	@NamedQuery(name ="Category.findDeactiveCategory",
	              query="SELECT cat FROM Category cat WHERE cat.shopId = :shopId and cat.isActive is FALSE"),
	@NamedQuery(name ="Category.findCategoryByShopIdAndId",
	              query= "SELECT cat FROM Category cat WHERE cat.shopId =:shopId and cat.id = :id")
	
})
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1385794955661915701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "AVATAR", nullable = false)
	private String avatar;
	
	@Column(name="TITLE", nullable = false)
	private String title;
	
	@Column(name = "SHOP_ID", nullable = false)
	private String shopId;
	
	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;
	
	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;
	
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

	public Category() {
		super();
	}
	
	public Category(String shopId, String name) {
		this.shopId= shopId;
		this.name= name;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	
	

}
