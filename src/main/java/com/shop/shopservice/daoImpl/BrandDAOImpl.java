package com.shop.shopservice.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.shop.shopservice.Idao.IBrandDAO;
import com.shop.shopservice.entity.Brand;
@Repository
@Transactional

public class BrandDAOImpl implements IBrandDAO{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Brand> getAllBrand() {
		List<Brand> brandList = entityManager.createNamedQuery("Brand.findAll",Brand.class).getResultList();
		return brandList;
	}
	
	@Override
	public List<Brand> getBrandForUser() {
		List<Brand> brandList = entityManager.createNamedQuery("Brand.findAllForUser", Brand.class).getResultList();
		return brandList;
	}

//	@Override
//	public List<Brand> getBrandByShopId(String shopId) {
//		List<Brand> brandList= entityManager.createNamedQuery("Brand.findByShopId",Brand.class).setParameter("shopId", shopId).getResultList();
//		return  brandList;
//	}
	
	@Override
	public List<Brand> getBrandByShopId(String shopId) {
		List<Brand> brandList= entityManager.createNamedQuery("Brand.findByShopId",Brand.class).setParameter("shopId", shopId).getResultList();
		return brandList;
	}
	@Override
	public List<Brand> getAllDeactiveBrandByShopId(int shopId) {
		List<Brand> brandList =entityManager.createNamedQuery("Brand.findDeactiveBrand",Brand.class).setParameter("shopId",shopId).getResultList();
		return brandList;
	}
	
	@Override
	public List<Brand> getBrandForUserByShopId(String shopId) {
		List<Brand> brandList= entityManager.createNamedQuery("Brand.findForUserByShopId",Brand.class).setParameter("shopId", shopId).getResultList();
		return brandList;
	}
	@Override
	public List<Brand> getBrandByShopIdAndId(String shopId, int id) {
		List<Brand> brandList = entityManager.createNamedQuery("Brand.findBrandByShopIdAndId",Brand.class).setParameter("shopId", shopId).setParameter("id", id).getResultList();
		return brandList;
	}
	
	@Override
	public boolean brandExists(String name, String shopId) {
		Brand brand = entityManager.createNamedQuery("Brand.findByNameShopId",Brand.class).setParameter("name", name).setParameter("shopId", shopId).getResultList().stream().findFirst().orElse(null);;
		return null != brand ? Boolean.TRUE : Boolean.FALSE;
	}
	
	@Override
	public void addBrand(Brand brand) {
		entityManager.persist(brand);

	}
	
	@Override
	public Brand getBrandByCategory(int category) {
		return this.entityManager.find(Brand.class, category);
	}

	@Override
	public void updateBrand(Brand brand) {
		entityManager.merge(brand);
		
	}

}
