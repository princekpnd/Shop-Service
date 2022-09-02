package com.shop.shopservice.Idao;

import java.util.List;
import com.shop.shopservice.entity.Product;
import com.shop.shopservice.entity.TimeLine;

public interface IProductDAO {

	List<Product> getAllProduct();

	List<Product> getAllProductForUser();

	Product getProductById(int productId);

	List<Product> getProductByName(String name);

	List<Product> getProductOfferByShopId(String shopId);

	List<Product> getProductByProductIdAndShopId(int productId, String shopId);

	List<Product> getProductByShopIdAndBrand(String shopId, int brand);

	List<Product> getCurrentProduct(String shopId, String name, int category, int brand);

	Product getProductByCategory(int category);

	List<Product> getProductByShopId(String shopId);

	List<Product> getWishList(int userId);

	List<Product> getProductForUserByShopId(String shopId);

	public Product getProductForOfferByProductId(int productId);

	public List<Product> getProductByCartId(int cartId);

	List<Product> getProductByShopIdForCategory(String shopId, int category);

	List<Product> getProductByBrandName(int brand);

	public List<Product> getProductByBrand(int brand);

	void updateProduct(Product product);

	boolean productExists(String shopId, String name, int category, int brand);

	void addProduct(Product product);

	public void indexProduct();

	public List<Product> searchProduct(String keyword);

	boolean deleteProduct(int productId);

}
