package com.shop.shopservice.service;

import java.util.List;
import com.shop.shopservice.entity.Brand;

public interface IBrandService {
	
	List<Brand> getAllBrand();
	List<Brand> getBrandForUser();
	public List<Brand> getBrandByShopId(String shopId);
	public List<Brand> getBrandForUserByShopId(String shopId);
	public List<Brand> getAllDeactiveBrandByShopId(int shopId);
	public List<Brand> getBrandByShopIdAndId(String shopId,int id);
	public boolean brandExists(String name, String shopId);
	public boolean createBrand(Brand brand);
	public Brand getBrand(int category);
	public boolean updateBrand(Brand brand);
	

}
