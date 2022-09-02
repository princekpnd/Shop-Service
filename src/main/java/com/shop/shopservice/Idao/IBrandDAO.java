package com.shop.shopservice.Idao;

import java.util.List;
import com.shop.shopservice.entity.Brand;

public interface IBrandDAO {
	
	List<Brand> getAllBrand();
	List<Brand> getBrandForUser();
	List<Brand> getBrandByShopId(String shopId);
	List<Brand> getBrandForUserByShopId(String shopId);
	public List<Brand> getAllDeactiveBrandByShopId(int shopId);
	public List<Brand> getBrandByShopIdAndId(String shopId,int id);
	boolean brandExists(String name, String shopId);
	 void addBrand(Brand brand);
	 Brand getBrandByCategory(int category);
	 void updateBrand(Brand brand);

}
