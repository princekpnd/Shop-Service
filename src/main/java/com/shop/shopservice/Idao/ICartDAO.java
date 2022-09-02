package com.shop.shopservice.Idao;

import java.util.List;
import com.shop.shopservice.entity.Cart;
import com.shop.shopservice.entity.ProductList;

public interface ICartDAO {
	List<Cart> getAllCart();

	Cart getCartById(int cartId);

	ProductList getProductByProductId(String productId, int cartId);

	public void updateProductList(ProductList plId, float productQuantity);

	List<Cart> getCartForUserByShopId(String shopId);

	public List<Cart> getDeactiveCartByUserIdAndShopId(int userId, String shopId);

	public List<Cart> getDeactiveCartByUserIdAndShopId(String shopId, String userId, int orderStatus);

	public List<Cart> getCartForOrderByCartId(int cartId);
	
	public Cart getCartByShopIdAndCartId(String shopId, int cartId);
	
	List<Cart> getCartByShopId(String shopId);

	public Cart getOrderStatus(int cartId, String shopId);

	List<Cart> getCartByUserId(String userId);

	void updateCart(Cart cart);

//	 void addCart(Cart cart);
	boolean cartExists(String userId);

	boolean cartExists(String userId, String shopId, int orderStatus);

	void addCart(Cart cart);

	List<Cart> getCartByUserId(String userId, String shopId, boolean orderStatus);

	List<Cart> orderDetails(String shopId, String userId, int orderStatus);

}
