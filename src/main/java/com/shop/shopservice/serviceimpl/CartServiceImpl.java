package com.shop.shopservice.serviceimpl;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.shop.shopservice.Idao.ICartDAO;
import com.shop.shopservice.entity.Cart;
import com.shop.shopservice.entity.ProductList;
import com.shop.shopservice.entity.UserDeviceID;
import com.shop.shopservice.service.ICartService;

@Repository
@Transactional


public class CartServiceImpl implements ICartService{
	
	@Autowired
	ICartDAO cartDao;
	
	
	@Override
	public List<Cart> getAllCart() {
		return cartDao.getAllCart();
	}
	
	@Override
	public Cart getCart(int cartId) {
		return cartDao.getCartById(cartId);
	}
	
	@Override
	public ProductList getProductByProductId(String productId, int cartId) {
		return cartDao.getProductByProductId(productId, cartId);
	}
		@Override
	public List<Cart> getCartForOrderByCartId(int cartId) {
		return  cartDao.getCartForOrderByCartId(cartId);
	}
	@Override
	public void updateProductList(ProductList plId, float productQuantity) {
		cartDao.updateProductList(plId, productQuantity);		
	}
		@Override
	public List<Cart> getDeactiveCartByUserIdAndShopId(int userId, String shopId) {
		return  cartDao.getDeactiveCartByUserIdAndShopId(userId ,shopId);
	}
	@Override
	public List<Cart> getCartForUserByShopId(String shopId) {
		return cartDao.getCartForUserByShopId(shopId) ;
	}
		@Override
	public List<Cart> getCartByUserId(String userId) {
		return cartDao.getCartByUserId(userId);
	}
//	@Override
//	public List<Cart> getCartByUserId(String userId, String shopId, boolean orderStatus) {
//		return cartDao.getCartByUserId(userId, shopId, orderStatus);
//	}
		@Override
	public boolean cartExists(String userId) {			
		return cartDao.cartExists(userId);
	}
	
//	@Override
//	public List<Cart> getCartByUserId(String userId, String shopId, boolean orderStatus) {
//		return cartDao.getCartByUserId(userId, shopId, orderStatus);
//	}
	@Override
	public boolean updateCart(Cart cart) {
		cartDao.updateCart(cart);
		return true;
	}
	
		@Override
	public boolean cartExists(String userId, String shopId, int orderStatus) {
		return cartDao.cartExists(userId, shopId, orderStatus);
	}
	
//		public boolean createCart(Cart cart) {
//
//		if (cartExists(cart.getUserId())) {
//			return false;
//		} else {
//
//			cartDao.addCart(cart);
//			cart = null;
//			return true;
//		}
//	}
	


	
//	@Override
//	public boolean cartExists(String userId, String shopId, boolean orderStatus) {
//		return cartDao.cartExists(userId, shopId, orderStatus);
//	}
	
//	public boolean createCart(Cart cart) {
//
//		if (cartExists(cart.getUserId())) {
//			return false;
//		} else {
//			cartDao.addCart(cart);			
//			return true;
//		}
//	}

	@Override
	public List<Cart> getCartByUserId(String userId, String shopId, boolean orderStatus) {
		return cartDao.getCartByUserId(userId, shopId, orderStatus);
	}

	@Override
	public boolean createCart(Cart cart) {
		if (cartExists(cart.getUserId())) {
			return false;
		} else {
			cartDao.addCart(cart);			
			return true;
		}
	}

	@Override
	public  Cart getOrderStatus(int cartId, String shopId) {
		return cartDao.getOrderStatus(cartId, shopId);
	}

	@Override
	public List<Cart> orderDetails(String shopId, String userId, int orderStatus) {
		return cartDao.orderDetails(shopId, userId, orderStatus);
		
	}

	@Override
	public List<Cart> getDeactiveCartByUserIdAndShopId(String shopId, String userId, int orderStatus) {
		return cartDao.getDeactiveCartByUserIdAndShopId(shopId, userId, orderStatus);
	}

	@Override
	public Cart getCartByShopIdAndCartId(String shopId, int cartId) {
		return cartDao.getCartByShopIdAndCartId(shopId, cartId);
	}

	@Override
	public List<Cart> getCartByShopId(String shopId) {
	return cartDao.getCartByShopId(shopId);
	}

}
