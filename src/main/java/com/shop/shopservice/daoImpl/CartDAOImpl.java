package com.shop.shopservice.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.ICartDAO;
import com.shop.shopservice.entity.Cart;
import com.shop.shopservice.entity.ProductList;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.entity.UserDeviceID;

@Repository
@Transactional


public class CartDAOImpl implements ICartDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public List<Cart> getAllCart() {
		List<Cart> cartList = entityManager.createNamedQuery("Cart.findAll", Cart.class).getResultList();
		return cartList;
	}

	@Override
	public Cart getCartById(int cartId) {
		return this.entityManager.find(Cart.class, cartId);
	}
	
	@Override
	public ProductList getProductByProductId(String productId, int cartId) {
		ProductList pl = null;
		try {
			pl = (ProductList) entityManager.createNamedQuery("ProductList.fetchProductById", ProductList.class)
					.setParameter("productId", productId).setParameter("cartId", cartId).getSingleResult();
		} catch (NoResultException nre) {
		}
		return null != pl ? pl : null;
	}
	
	@Override
	public List<Cart> getCartForOrderByCartId(int cartId) {
		List<Cart> cartList = entityManager.createNamedQuery("Cart.findCartForOrder",Cart.class).setParameter("cartId", cartId).getResultList();
		return cartList;
	}

	@Override
	public List<Cart> getDeactiveCartByUserIdAndShopId(int userId, String shopId) {
		List<Cart> cartList = entityManager.createNamedQuery("Cart.findDeactiveCart",Cart.class).setParameter("userId", userId).setParameter("shopId", shopId).getResultList();
		return cartList;
	}
	
	@Override
	public void updateProductList(ProductList plId, float productQuantity) {
		Query query = entityManager.createQuery("update ProductList set productQuantity =" + productQuantity + "where id = " + plId.getId());
		query.executeUpdate();
	}

	@Override
	public List<Cart> getCartByUserId(String userId, String shopId, boolean orderStatus) {
		return this.entityManager.createNamedQuery("Cart.findByOrderActiveUserId", Cart.class).setParameter("userId", userId)
				.setParameter("shopId", shopId).setParameter("orderStatus", orderStatus)
				.getResultList();
	}
	
//	@Override
//	public List<Cart> getCartByUserId(String userId, String shopId, boolean orderStatus) {
//		return this.entityManager.createNamedQuery("Cart.findByOrderActiveUserId", Cart.class).setParameter("userId", userId)
//				.setParameter("shopId", shopId).setParameter("orderStatus", orderStatus)
//				.getResultList();
//	}

	@Override
	public void updateCart(Cart cart) {
		entityManager.merge(cart);
	}

	@Override
	public boolean cartExists(String userId, String shopId, int orderStatus) {
		Cart cart = entityManager.createNamedQuery("Cart.findByOrderActiveUserId", Cart.class).setParameter("userId", userId)
				.setParameter("shopId", shopId).setParameter("orderStatus", orderStatus).getResultList().stream().findFirst()
				.orElse(null);
		;
		return null != cart ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public void addCart(Cart cart) {
		entityManager.persist(cart);
	}

	@Override
	public List<Cart> getCartForUserByShopId(String shopId) {
		List<Cart> cartList = entityManager.createNamedQuery("Cart.findCartForUserByShopId", Cart.class)
				.setParameter("shopId", shopId).getResultList();
		return cartList;
	}



	@Override
	public List<Cart> getCartByUserId(String userId) {
		List<Cart> cartList = entityManager.createNamedQuery("Cart.findCartForUserByUserId", Cart.class)
				.setParameter("userId", userId).getResultList();
		return cartList;
	}

	@Override
	public boolean cartExists(String userId) {
		Cart cart = entityManager.createNamedQuery("Cart.findCartForUserByUserId", Cart.class).setParameter("userId", userId)
				.getResultList().stream().findFirst()
				.orElse(null);
		;
		return null != cart ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public  Cart getOrderStatus(int cartId, String shopId) {
		 Cart cart = entityManager.createNamedQuery("Cart.findOrderStatus", Cart.class).setParameter("cartId", cartId).setParameter("shopId", shopId).getSingleResult();
		return cart;
	}

	@Override
	public List<Cart> orderDetails(String shopId, String userId, int orderStatus) {
		List<Cart> cartList = entityManager.createNamedQuery("Cart.orderDetails", Cart.class).setParameter("shopId", shopId).setParameter("userId", userId).setParameter("orderStatus", orderStatus).getResultList();
		return cartList;
	}

	@Override
	public List<Cart> getDeactiveCartByUserIdAndShopId(String shopId, String userId, int orderStatus) {
	List<Cart> cartList = entityManager.createNamedQuery("Cart.findDeactiveCartByUserId", Cart.class).setParameter("shopId", shopId).setParameter("userId", userId).setParameter("orderStatus", orderStatus).getResultList();
		return cartList;
	}

	@Override
	public Cart getCartByShopIdAndCartId(String shopId, int cartId) {
		Cart cart = entityManager.createNamedQuery("Cart.findCartByShopIdAndCartId", Cart.class).setParameter("shopId", shopId).setParameter("cartId", cartId).getSingleResult();
		return cart;
	}

	@Override
	public List<Cart> getCartByShopId(String shopId) {
		List<Cart> cartList = entityManager.createNamedQuery("Cart.findByShopId",Cart.class).setParameter("shopId", shopId).getResultList();
		return cartList;
	}

}