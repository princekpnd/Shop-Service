package com.shop.shopservice.daoImpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.shop.shopservice.Idao.ICategoryDAO;
import com.shop.shopservice.entity.Category;

@Repository
@Transactional
public class CategoryDAOImpl implements ICategoryDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Category> getAllCategory() {
		List<Category> categoryList= entityManager.createNamedQuery("Category.findAll",Category.class).getResultList();
		return categoryList;
	}
	
	@Override
	public List<Category> getCategoryForUser(){
		List<Category> categoryList= entityManager.createNamedQuery("Category.findCategoryForUser",Category.class).getResultList();
		return categoryList;
	}

	@Override
	public List<Category> getCategoryForUserByShopId(String shopId) {
		List<Category> categoryList= entityManager.createNamedQuery("Category.findCategoryForUserByShopId",Category.class).setParameter("shopId",shopId) .getResultList();
		return categoryList;
	}
	@Override
	public List<Category> getAllDeactiveCategoryByShopId(String shopId) {
		List<Category> categoryList =entityManager.createNamedQuery("Category.findDeactiveCategory",Category.class).setParameter("shopId", shopId).getResultList();
		return categoryList;
	}
	@Override
	public List<Category> getCategoryByShopIdAndId(String shopId, int id) {
		List<Category> categoryList = entityManager.createNamedQuery("Category.findCategoryByShopIdAndId",Category.class).setParameter("shopId",shopId).setParameter("id",id).getResultList();
		return categoryList;
	}

	
	@Override
	public List<Category> getCategoryByShopId(String shopId) {
		List<Category> categoryList = entityManager.createNamedQuery("Category.findCategoryByShopId",Category.class).setParameter("shopId", shopId).getResultList();
		return categoryList;
	}
	
	@Override
	public boolean categoryExists(String name, String shopId) {
		Category category = entityManager.createNamedQuery("Category.findCategoryByName",Category.class).setParameter("name", name).setParameter("shopId", shopId).getResultList().stream().findFirst().orElse(null);;
		return null != category ?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public void addCategory(Category category) {
		entityManager.persist(category);

	}

	@Override
	public Category getCategoryById(int id) {
		return this.entityManager.find(Category.class, id);
	
	}

	@Override
	public void updateCategory(Category category) {
		entityManager.merge(category);
		
	}

	

}
