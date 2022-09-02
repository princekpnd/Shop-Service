package com.shop.shopservice.daoImpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.IProductDAO;
import com.shop.shopservice.entity.Product;
import com.shop.shopservice.entity.TimeLine;

@Repository
@Transactional
public class ProductDAOImpl implements IProductDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Product> getAllProduct() {
		List<Product> productList= entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
		return productList;
	}
	
	@Override
	public List<Product> getAllProductForUser() {
		List<Product> productList= entityManager.createNamedQuery("Product.findAllForUser", Product.class).getResultList();
		return productList;
	}
	
	@Override
	public List<Product> getProductByCartId(int cartId) {
		List<Product> productList = entityManager.createNamedQuery("Product.findProductByCartId",Product.class).setParameter("cartId",cartId).getResultList();
		return productList;
	}

	public void indexProduct() {
		try {
		      FullTextEntityManager fullTextEntityManager =
		        Search.getFullTextEntityManager(entityManager);
		      fullTextEntityManager.createIndexer().startAndWait();
		    }
		    catch (InterruptedException e) {
		      System.out.println(
		        "An error occurred trying to build the serach index: " +
		         e.toString());
		    }
	}
	
	@Override
	public List<Product> searchProduct(String keyword) {
		// get the full text entity manager
	    FullTextEntityManager fullTextEntityManager =
	        org.hibernate.search.jpa.Search.
	        getFullTextEntityManager(entityManager);
	    
	    // create the query using Hibernate Search query DSL
	    QueryBuilder queryBuilder = 
	        fullTextEntityManager.getSearchFactory()
	        .buildQueryBuilder().forEntity(Product.class).get();
	    
	    // a very basic query by keywords
	    org.apache.lucene.search.Query query =
	        queryBuilder
	          .keyword()
	          .onFields("name", "name")
	          .matching(keyword)
	          .createQuery();

	    // wrap Lucene query in an Hibernate Query object
	    org.hibernate.search.jpa.FullTextQuery jpaQuery =
	        fullTextEntityManager.createFullTextQuery(query, Product.class);
	  
	    // execute search and return results (sorted by relevance as default)
	    @SuppressWarnings("unchecked")
	    List<Product> results = (List<Product>)jpaQuery.getResultList();
	    
	    return results;
	}
	
	@Override
	public Product getProductById(int productId) {
		return this.entityManager.find(Product.class, productId);
	}
	
	@Override
	public Product getProductForOfferByProductId(int productId) {
		Product product = entityManager.createNamedQuery("Product.findProductByProductId",Product.class).setParameter("productId", productId).getSingleResult();
		return product;
	}
	@Override
	public List<Product> getProductByBrand(int brand) {
		List<Product> productList = entityManager.createNamedQuery("Product.findProductByBrandName",Product.class).setParameter("brand", brand).getResultList();
		return productList;
	}

	
	@Override
	public List<Product> getProductByName(String name) {
		List<Product> productList= entityManager.createNamedQuery("Product.findByName", Product.class).setParameter("name", name).getResultList();
		return productList;
	}
	
	@Override
	public Product getProductByCategory(int category) {
		Product product= entityManager.createNamedQuery("Product.findByCategory",Product.class).setParameter("category", category).getSingleResult();
		return product;
	}
	
	@Override
	public List<Product> getProductByShopIdForCategory(String shopId, int category) {
		List<Product> productList =entityManager.createNamedQuery("Product.findProductByShopIdForCategory",Product.class).setParameter("shopId",shopId).setParameter("category", category).getResultList();
		return productList;
	}

	
	@Override
	public List<Product> getProductByShopId(String shopId) {
		List<Product> productList= entityManager.createNamedQuery("Product.findByShopId", Product.class).setParameter("shopId", shopId).getResultList();
		return productList;
	}
	@Override
	public List<Product> getProductForUserByShopId(String shopId) {
		List<Product> productList =entityManager.createNamedQuery("Product.findProductByShopId", Product.class).setParameter("shopId",shopId).getResultList();
		return productList;
	}
	
	@Override
	public List<Product> getProductByBrandName(int brand) {
		List<Product> product = entityManager.createNamedQuery("Product.findProductByBrand", Product.class).setParameter("brand", brand).getResultList();
		return product;
	}
	
	@Override
	public void updateProduct(Product product) {
	entityManager.merge(product);
	}
	
	@Override
	public boolean productExists(String shopId,String name, int category,int brand) {
		Product product = entityManager.createNamedQuery("Product.ProductExistByShopId",Product.class).setParameter("shopId", shopId).setParameter("name", name).setParameter("category", category).setParameter("brand", brand) .getResultList().stream().findFirst().orElse(null);;
		return null != product ?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);
	}

	@Override
	public List<Product> getProductOfferByShopId(String shopId) {
		List<Product> productList = entityManager.createNamedQuery("Product.findOfferProduct",Product.class).setParameter("shopId", shopId).getResultList();
		return productList;
	}

	@Override
	public List<Product> getProductByProductIdAndShopId(int productId, String shopId) {
		List<Product> productList = entityManager.createNamedQuery("Product.findProductIdAndShopId" ,Product.class).setParameter("productId", productId).setParameter("shopId", shopId).getResultList();
		return productList;
	}

	@Override
	public List<Product> getCurrentProduct(String shopId, String name, int category, int brand) {
		List<Product> productList = entityManager.createNamedQuery("Product.findCurrentProduct",Product.class).setParameter("shopId", shopId).setParameter("name", name)
				.setParameter("category", category).setParameter("brand", brand).getResultList();
		return productList;
	}

	@Override
	public List<Product> getWishList(int userId) {
		List<Product> productList = entityManager.createNamedQuery("Product.getWishList",Product.class).setParameter("userId", userId).getResultList();
		return productList;
	}

	@Override
	public List<Product> getProductByShopIdAndBrand(String shopId, int brand) {
		List<Product> productList = entityManager.createNamedQuery("Product.findBuShopIdAndBrand", Product.class).setParameter("shopId", shopId).setParameter("brand", brand).getResultList();
		return productList;
	}

	@Override
	public boolean deleteProduct(int productId) {
		Query query = entityManager.createQuery("delete Product where productId = " +productId);			
		query.executeUpdate();
		entityManager.flush();
		return true;
	}

	
	
}
