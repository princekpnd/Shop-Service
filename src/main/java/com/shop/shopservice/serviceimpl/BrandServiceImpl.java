package com.shop.shopservice.serviceimpl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.shop.shopservice.Idao.IBrandDAO;
import com.shop.shopservice.entity.Brand;
import com.shop.shopservice.service.IBrandService;
@Repository
@Transactional
public class BrandServiceImpl implements IBrandService{
	
	@Autowired
	IBrandDAO brandDao;
	
	@Override
	public List<Brand> getAllBrand() {
		return brandDao.getAllBrand();
	}
	
	@Override
	public List<Brand> getBrandForUser() {
		return brandDao.getBrandForUser();
	}

	@Override
	public List<Brand> getBrandByShopId(String shopId) {
		return  brandDao.getBrandByShopId(shopId);
	}
	
	@Override
	public List<Brand> getBrandForUserByShopId(String shopId) {
		return  brandDao.getBrandForUserByShopId(shopId);
	}
	
	@Override
	public boolean brandExists(String name, String shopId) {
		return brandDao.brandExists(name, shopId);
	}
	
	public boolean createBrand(Brand brand) {

		if (brandExists(brand.getName(), brand.getShopId())) {
			return false;
		} else {

			brandDao.addBrand(brand);
			brand = null;
			return true;
		}
	}
	
	@Override
	public Brand getBrand(int category) {
		return brandDao.getBrandByCategory(category);
	}
	
	@Override
	public boolean updateBrand(Brand brand) {
		brandDao.updateBrand(brand);
		return true;
	}

	@Override
	public List<Brand> getAllDeactiveBrandByShopId(int shopId) {
		return brandDao.getAllDeactiveBrandByShopId(shopId);
	}

	@Override
	public List<Brand> getBrandByShopIdAndId(String shopId, int id) {
		return brandDao.getBrandByShopIdAndId(shopId,id) ;
	}

}
