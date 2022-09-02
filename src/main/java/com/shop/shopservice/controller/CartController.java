package com.shop.shopservice.controller;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopservice.Idao.ILookUp;
import com.shop.shopservice.Idao.ILookUpType;
import com.shop.shopservice.constants.ServiceConstants;
import com.shop.shopservice.entity.Address;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.AdminBillBook;
import com.shop.shopservice.entity.Cart;
import com.shop.shopservice.entity.LookUp;
import com.shop.shopservice.entity.Product;
import com.shop.shopservice.entity.ProductList;
import com.shop.shopservice.entity.Review;
import com.shop.shopservice.entity.Transaction;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.entity.UserBillBook;
import com.shop.shopservice.service.IAddressService;
import com.shop.shopservice.service.IAdminBillBookService;
import com.shop.shopservice.service.IAdminService;
import com.shop.shopservice.service.ICartService;
import com.shop.shopservice.service.IProductListService;
import com.shop.shopservice.service.IProductService;
import com.shop.shopservice.service.IReviewService;
import com.shop.shopservice.service.ITransactionService;
import com.shop.shopservice.service.IUserBillBookService;
import com.shop.shopservice.service.IUserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	private final Logger log = LoggerFactory.getLogger(CartController.class);
	@Autowired
	ICartService cartService;

	@Autowired
	private IUserService UserService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductListService productListService;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private ILookUpType lookUpType;

	@Autowired
	private IReviewService reviewService;

	@Autowired
	private IAdminBillBookService adminBillBookService;

	@Autowired
	private IUserBillBookService userBillBookService;

	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private ILookUp lookup;

	@GetMapping("getallcart")
	public ResponseEntity<List<Cart>> getAllCart() {
		List<Cart> cartList = cartService.getAllCart();
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("getdeactivecartbyuseridandshopid/{userId}/{shopId}")
	public ResponseEntity<List<Cart>> getDeactiveCartByUserIdAndShopId(@PathVariable("userId") int userId,
			@PathVariable("shopId") String shopId) {
		List<Cart> cartList = cartService.getDeactiveCartByUserIdAndShopId(userId, shopId);
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable("cartId") Integer cartId) {
		Cart cart = cartService.getCart(cartId.intValue());
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@GetMapping("getcartfororderbycartid/{cartId}")
	public ResponseEntity<List<Cart>> getCartForOrderByCartId(@PathVariable("cartId") int cartId) {
		List<Cart> cartList = cartService.getCartForOrderByCartId(cartId);
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}
	
	@GetMapping("getbyshopid/{shopId}")
	public ResponseEntity<List<Cart>> getByShopId(@PathVariable("shopId") String shopId){
		List<Cart> cartList  = cartService.getCartByShopId(shopId);
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("getcartbyuserid/{userId}/{shopId}")
	public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable("userId") String userId,
			@PathVariable("shopId") String shopId) {
		List<Cart> cart = cartService.getCartByUserId(userId, shopId, Boolean.TRUE);
		return new ResponseEntity<List<Cart>>(cart, HttpStatus.OK);
	}

	@GetMapping("getcartbyuserid/{userId}")
	public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable("userId") String userId) {
		List<Cart> cartList = cartService.getCartByUserId(userId);
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/accepted/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> acceptedOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "ACCEPTED");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/rejected/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> rejectedOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "REJECTED");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/packed/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> packedOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "PACKED");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/shipped/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> shippedOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "SHIPPED");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/delivered/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> deliveredOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "DELIVERED");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/successful/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> successfulOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "SUCCESSFUL");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/placed/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> placedOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "PLACED");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

//	@GetMapping("get/totalAmount/byshopId/{shopId}")
//	public ResponseEntity<Map<String, String>> getAllCartByShopId(@PathVariable("shopId") String shopId) {
//		Map<String, String> response = new HashMap<String, String>();
//		List<Cart> cartList = cartService.getCartByShopIdId(shopId);
//		LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "RECEIVED");
//		LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "DELIVERED");
//		LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "SUCCESSFUL");
//		int received = lookUp.getLookUpId();
//		int delivered = lookUp1.getLookUpId();
//		int successful = lookUp2.getLookUpId();
//		int totalAmount =0;
//		for(int i =0; i<cartList.size(); i++) {
//			int orderStatus = cartList.get(i).getOrderStatus();
//			if(orderStatus == received || orderStatus == delivered || orderStatus == successful) {
//				int payableAmount = cartList.get(i).getPayableAmount();
//				totalAmount = totalAmount + payableAmount ;
//				
//			}
//		}
//		response.put("status", Boolean.TRUE.toString());
//		response.put("totalPayableAmount", String.valueOf(totalAmount));
//		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
//	}

	@GetMapping("get/denied/order/{shopId}/{userId}")
	public ResponseEntity<List<Cart>> deniedOrder(@PathVariable("shopId") String shopId,
			@PathVariable("userId") String userId) {
		LookUp orderStatus = lookup.findLookUpIdByName("MILAAN", "DENIED");
		List<Cart> cartList = cartService.orderDetails(shopId, userId, orderStatus.getLookUpId());
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("getcartby/shopid/cartId/{shopId}/{cartId}")
	public ResponseEntity<Cart> getCartByShopIdAndCartId(@PathVariable("shopId") String shopId,
			@PathVariable("cartId") int cartId) {
		Cart cart = cartService.getCartByShopIdAndCartId(shopId, cartId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@GetMapping("orderstatus/{cartId}/{shopId}/{orderStatus}")
	public ResponseEntity<List<ProductList>> getOrderStatus(@PathVariable("cartId") int cartId,
			@PathVariable("shopId") String shopId, @PathVariable("orderStatus") int orderStatus) {
		Map<String, String> response = new HashMap<String, String>();
		Cart cart = cartService.getOrderStatus(cartId, shopId);
		LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "ACCEPTED");
		LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "PLACED");
		int accepted = lookUp.getLookUpId();
		int placed = lookUp1.getLookUpId();
		List<ProductList> productList = null;
		Product product = null;
		if (orderStatus == accepted) {
			cart.setOrderStatus(accepted);
			cart.setActive(Boolean.FALSE);
			productList = productListService.getProductListCartId(cartId);
			for (int i = 0; i < productList.size(); i++) {
				int productId = Integer.parseInt(productList.get(i).getProductId());
				int productQuantity = (int) productList.get(i).getProductQuantity();
				product = productService.getProduct(productId);
				product.setStock(product.getStock() - productQuantity);
				productService.updateProduct(product);
			}
			// int user = Integer.parseInt(json.get(ServiceConstants.USER_ID));
			int userId = Integer.parseInt(cart.getUserId());
			User user = UserService.getUser(userId);
			user.setDues(user.getDues() + cart.getTotalAmount());
			UserService.updateUser(user);
		} else if (orderStatus == placed) {
			cart.setOrderStatus(placed);
			cart.setActive(Boolean.FALSE);
		} else {
			cart.setOrderStatus(orderStatus);
		}

//		int productId = Integer.parseInt(json.get(ServiceConstants.PRODUCT_ID));
//		float productQuantity =Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY));
//		ProductList productList =productListService.getProductListCartId(cartId);
//		Product product = null;

//		product = productService.getProduct(productId);

//		float totalQuantity = product.getQuantity()-productList.getProductQuantity();

		// productList.getProductQuantity(product.getQuantity()-productList.getProductQuantity());
//		product.setQuantity(Float.parseFloat(totalQuantity));
//		boolean update1 = productService.updateProduct(product);
		boolean update = cartService.updateCart(cart);
		if (update) {
			response.put("status", Boolean.TRUE.toString());
			response.put("description", "Cart updated");
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Cart did not updated");
		}
		// return ResponseEntity.ok().body(response);
		return new ResponseEntity<List<ProductList>>(productList, HttpStatus.OK);
	}

	@GetMapping("getcartforuserbyshopid/{shopId}")
	public ResponseEntity<List<Cart>> getCartForUserByShopId(@PathVariable("shopId") String shopId) {
		Admin admin = adminService.getAdminByShopId(shopId);
		List<Cart> cartList = null;
		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
			cartList = cartService.getCartForUserByShopId(shopId);
		}
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}

	@GetMapping("get/deactive/cart/{userId}/{shopId}")
	public ResponseEntity<List<Cart>> getDeactiveCartByUserIdAndShopId(@PathVariable("userId") String userId,
			@PathVariable("shopId") String shopId) {
		LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "DEACTIVE");
		int orderStatus = lookUp.getLookUpId();
		List<Cart> cartList = cartService.getDeactiveCartByUserIdAndShopId(shopId, userId, orderStatus);
		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
	}
	// Complete cheak
	@PutMapping("cartincrease/{cartId}/{productId}")
	public ResponseEntity<Map<String, String>> CartIncreaseByCartIdAndProductId(@PathVariable("cartId") int cartId,
			@PathVariable("productId") String productId) {
		Map<String, String> response = new HashMap<String, String>();
		Cart cart = cartService.getCart(cartId);
		if(null != cart) {
		ProductList productList = productListService.getProductByProductIdAndCartId(productId, cartId);
		Product product = productService.getProduct(Integer.parseInt(productId));
//		product.setStock(product.getStock() - 1);
//		productService.updateProduct(product);
		float price = product.getPrice(), sellingPrice = product.getSellingPrice(), costPrice = product.getCostPrice(),
				oldPrice = product.getOldPrice(), gstAmount = product.getGstAmount(), discount = product.getDiscount(),
				totalAmount = price + gstAmount, payableAmount = cart.getTotalAmount() + totalAmount;
		if (product.getStock() > productList.getProductQuantity()) {
			cart.setDiscount(cart.getDiscount() + discount);
			cart.setGstAmount(cart.getGstAmount() + gstAmount);
			cart.setPaid(cart.getPaid() + price);
			cart.setTotalAmount(totalAmount);
			cart.setPayableAmount(cart.getPayableAmount() + totalAmount);
			cart.setDues(cart.getDues() + payableAmount);
			boolean cartUpdate = cartService.updateCart(cart);
			if (cartUpdate) {
				productList.setPrice(productList.getPrice() + price);
				productList.setDiscount(productList.getDiscount() + discount);
				productList.setGstAmount(productList.getGstAmount() + gstAmount);
				productList.setOldPrice(productList.getOldPrice() + oldPrice);
				productList.setTotalAmount(productList.getTotalAmount() + totalAmount);
				productList.setProductQuantity(productList.getProductQuantity() + 1);

				boolean productListUpdate = productListService.updateProductList(productList);
				if (productListUpdate) {
					response.put("status", Boolean.TRUE.toString());
					response.put("description", "Product updated");
				} else {
					response.put("status", Boolean.FALSE.toString());
					response.put("description", "Product does not updated");
				}
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("description", "Cart does not updated");
			}

		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Product is out of stock");
		}
		}
		return ResponseEntity.ok().body(response);
	}
	// Complete cheak
	@PutMapping("cartdecrease/{cartId}/{productId}")
	public ResponseEntity<Map<String, String>> CartDecreaseByCartIdAndProductId(@PathVariable("cartId") int cartId,
			@PathVariable("productId") String productId) {
		Map<String, String> response = new HashMap<String, String>();
		Cart cart = cartService.getCart(cartId);
		ProductList productList = productListService.getProductByProductIdAndCartId(productId, cartId);
		Product product = productService.getProduct(Integer.parseInt(productId));
		float price = product.getPrice(), sellingPrice = product.getSellingPrice(), costPrice = product.getCostPrice(),
				oldPrice = product.getOldPrice(), gstAmount = product.getGstAmount(), discount = product.getDiscount(),
				totalAmount = price + gstAmount, payableAmount = cart.getTotalAmount() - totalAmount;
		if (productList.getProductQuantity() > 1) {

			cart.setDiscount(cart.getDiscount() - discount);
			cart.setGstAmount(cart.getGstAmount() - gstAmount);
			cart.setPaid(cart.getPaid() - price);
			cart.setTotalAmount(totalAmount);
			cart.setPayableAmount(cart.getPayableAmount() - totalAmount);
			cart.setDues(cart.getDues() - payableAmount);
			boolean cartUpdate = cartService.updateCart(cart);
			if (cartUpdate) {
				productList.setPrice(productList.getPrice() - price);
				productList.setProductQuantity(productList.getProductQuantity() - 1);
				productList.setDiscount(productList.getDiscount() - discount);
				productList.setGstAmount(productList.getGstAmount() - gstAmount);
				productList.setOldPrice(productList.getOldPrice() - oldPrice);
				productList.setTotalAmount(productList.getTotalAmount() - totalAmount);
				boolean productListUpdate = productListService.updateProductList(productList);
				if (productListUpdate) {
					response.put("status", Boolean.TRUE.toString());
					response.put("description", "Product updated");
				} else {
					response.put("status", Boolean.FALSE.toString());
					response.put("description", "Product does not updated");
				}
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("description", "Product does not decrease");
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Product does not decrease");
		}

		return ResponseEntity.ok().body(response);
	}

	// Complete cheak

	@PutMapping("placeorder")
	ResponseEntity<Map<String, String>> placeOrder(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.CART_ID));
		Map<String, String> response = new HashMap<String, String>();

		if (null != json.get(ServiceConstants.CART_ID) && null != json.get(ServiceConstants.ORDER_TYPE)
				&& null != json.get(ServiceConstants.TRANSACTION_TYPE)
				&& null != json.get(ServiceConstants.ADDRESS_ID)) {
			boolean callData = false;

			Date slotDate = null;
			int cartId = Integer.parseInt(json.get(ServiceConstants.CART_ID)),
					transactionType = Integer.parseInt(json.get(ServiceConstants.TRANSACTION_TYPE)),
					addressId = Integer.parseInt(json.get(ServiceConstants.ADDRESS_ID));
//							orderType = Integer.parseInt(json.get(json.get(ServiceConstants.ORDER_TYPE)));
			Cart cart = cartService.getCart(cartId);
			String shopId = cart.getShopId();
			Address address = addressService.getAddress(addressId);
			String mobileNo = address.getMobileNo();
			String userId = address.getUserId();
			String userName = address.getUserName();
			Admin admin = adminService.getAdminByShopId(shopId);
			int adminId = admin.getAdminId();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:a");

			LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "PLACED");
			LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "CUSTOMER");
			LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "ONLINE_PAYMENT");
			LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "CASH");
			LookUp lookUp4 = lookup.findLookUpIdByName("MILAAN", "SELF_DELIVERY");
			LookUp lookUp5 = lookup.findLookUpIdByName("MILAAN", "SHIPPED");
			LookUp lookUp6 = lookup.findLookUpIdByName("MILAAN", "TRANS_PENDING");
			LookUp lookUp7 = lookup.findLookUpIdByName("MILAAN", "SHOPING");
			int place = lookUp.getLookUpId();
			int customer = lookUp1.getLookUpId();
			int online = lookUp2.getLookUpId();
			int cash = lookUp3.getLookUpId();
			int selfDelivery = lookUp4.getLookUpId();
			int shipping = lookUp5.getLookUpId();
			int shoping = lookUp7.getLookUpId();
			int transactionStatus = lookUp6.getLookUpId();
			Transaction transaction = new Transaction(shopId, Integer.parseInt(userId));
			List<ProductList> productList = productListService.getProductListCartId(cartId);
			for (int i = 0; i < productList.size(); i++) {
				try {
					Product product = productService.getProduct(Integer.parseInt(productList.get(i).getProductId()));
					if (product.getStock() >= productList.get(i).getProductQuantity()) {
						callData = true;
					}
				} catch (Exception e) {
					callData = false;
				}
			}
			if (callData) {
				if (transactionType == online) {

					cart.setOrderStatus(place);
					cart.setTransactionType(online);
					cart.setActive(Boolean.TRUE);
					cart.setCreatedOn(new Date());
					cart.setMobileNo(mobileNo);
					cart.setUserName(userName);

					cart.setAddressId(addressId);
					// cart.setOrderType(orderType);
//					cart.setOrderDate(new Date());
//					try {
//						cart.setSlotDate(
//								new SimpleDateFormat("yyyy-MM-dd").parse(json.get(ServiceConstants.SLOT_DATE)));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}

//					try {
//						slotDate = formater.parse(json.get(ServiceConstants.SLOT_DATE));
//						cart.setSlotDate(slotDate);
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
					transaction.setActive(Boolean.TRUE);
					transaction.setAmount((int) cart.getTotalAmount());
					transaction.setCartId(cartId);
					transaction.setCreatedOn(new Date());
					transaction.setDeleted(Boolean.FALSE);
					transaction.setDues(0);
					transaction.setPaid(cart.getPaid());
					transaction.setPaymentMode(online);
					transaction.setShopId(shopId);
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setAdminId(adminId);
					transaction.setTransactionType(shoping);
					transaction.setTransactionStatus(transactionStatus);
					transaction.setTotalAmount(cart.getTotalAmount());
					// transaction.setTransactionId(transactionId);
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setUserType(customer);

					transactionService.createTransaction(transaction);
					List<Transaction> transactionList = transactionService.getTransactionByCartId(cartId);
					Transaction transaction1 = transactionList.get(0);

					boolean result = cartService.updateCart(cart);
					if (result) {
						// notificationService.createNotification(cartId, orderNotification);
						response.put("status", String.valueOf(true));
						response.put("transactionId", String.valueOf(transaction1.getId()));
						response.put("description", "Please complete transaction to place order.");
					} else {
						response.put("status", String.valueOf(false));
						response.put("description", "Your placed order has been failed.");
					}

				} else if (transactionType == cash) {

					cart.setDeliveryType(selfDelivery);
					// cart.setShippingDate(new Date());
					cart.setDeliveryDate(new Date());
					cart.setOrderStatus(place);
					cart.setTransactionType(cash);
					cart.setActive(Boolean.TRUE);
					cart.setDues(cart.getDues());
					// cart.setOrderType(orderType);
					cart.setUserName(userName);
					cart.setMobileNo(mobileNo);

					transaction.setActive(Boolean.TRUE);
					transaction.setAmount((int) cart.getTotalAmount());
					transaction.setCartId(cartId);
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setCreatedOn(new Date());
					transaction.setDeleted(Boolean.FALSE);
					transaction.setDues(0);
					transaction.setPaid(cart.getPaid());
					transaction.setPaymentMode(cash);
					transaction.setAdminId(adminId);
					transaction.setShopId(shopId);
					transaction.setTotalAmount(cart.getTotalAmount());
					transaction.setTransactionType(shoping);
					transaction.setTransactionStatus(transactionStatus);
					// transaction.setTransactionId(transactionId);
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setUserType(customer);
					transactionService.createTransaction(transaction);

//					try {
//						slotDate = formater.parse(json.get(ServiceConstants.SLOT_DATE));
//						cart.setSlotDate(slotDate);
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
					cart.setTransactionId(String.valueOf(transaction.getId()));

				}
				boolean updateCart = cartService.updateCart(cart);
				if (updateCart) {
					List<ProductList> productList1 = productListService.getProductListCartId(cartId);
					if (productList1.size() > 0) {  
						for (int i = 0; i < productList1.size(); i++) {
							String productId = productList1.get(i).getProductId();
							float productQuantity = productList1.get(i).getProductQuantity();
							Product product = productService.getProduct(Integer.parseInt(productId));
							product.setStock(product.getStock() - ((int) productQuantity));
							productService.updateProduct(product);
						}
					}

				}
				if (updateCart) {
					response.put("status", String.valueOf(true));
					response.put("description", "Your order placed sucessfully.");
				} else {
					response.put("status", String.valueOf(true));
					response.put("description", "Your order does not placed.");
				}
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("description", "Some product is out of stock");
			}

		}
		return ResponseEntity.ok().body(response);
	}

//Complete cheak
	@GetMapping("cartpacked/{cartId}")
	public ResponseEntity<Map<String, String>> cartPacked(@PathVariable("cartId") int cartId) {
		Map<String, String> response = new HashMap<String, String>();
		Cart cart = cartService.getCart(cartId);
		// int orderStatus = cart.getOrderStatus();
		LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "PACKED");
		int orderStatus1 = lookUp.getLookUpId();
		cart.setOrderStatus(orderStatus1);
		boolean cartUpdate = cartService.updateCart(cart);
		if (cartUpdate) {
			response.put("status", Boolean.TRUE.toString());
			response.put("description", "Cart packed successfull");
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Cart does not packed");
		}
		return ResponseEntity.ok().body(response);
	}

	// Complete cheak
	@PostMapping("order/shipped")
	ResponseEntity<Map<String, String>> shippedOrder(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.CART_ID) && null != json.get(ServiceConstants.DELIVERY_TYPE)
				&& null != json.get(ServiceConstants.DELIVERY_DATE)) {
			int cartId = Integer.parseInt(json.get(ServiceConstants.CART_ID)),
					deliveryType = Integer.parseInt(json.get(ServiceConstants.DELIVERY_TYPE));
			Date deliveryDate = null;

			LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "SHIPPED");
			LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "BY_DELIVERY_BOY");
			LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "SELF_DELIVERY");
			LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "COURIER");
			int shipped = lookUp.getLookUpId();
			int dBoy = lookUp1.getLookUpId();
			int selfDelivery = lookUp2.getLookUpId();
			int courier = lookUp3.getLookUpId();
			Cart cart = cartService.getCart(cartId);
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:a");

			cart.setActive(Boolean.TRUE);
			cart.setOrderStatus(shipped);
			cart.setDeliveryType(deliveryType);
			cart.setShippingDate(new Date());

			try {
				deliveryDate = formater.parse(json.get(ServiceConstants.DELIVERY_DATE));
				cart.setDeliveryDate(deliveryDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (deliveryType == dBoy) {
				if (null != json.get(ServiceConstants.D_BOY_NAME) && null != json.get(ServiceConstants.D_BOY_NUMBER)) {
					String dBoyName = json.get(ServiceConstants.D_BOY_NAME);
					String dBoyNumber = json.get(ServiceConstants.D_BOY_NUMBER);
					cart.setdBoyName(dBoyName);
					cart.setdBoyNumber(dBoyNumber);
					cart.setDeliveryDate(deliveryDate);
					cart.setDeliveryType(dBoy);
					if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
						float deliveryCharge = Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE));
						cart.setDeliveryCharge(deliveryCharge);
					}
				}

			} else if (deliveryType == courier) {
				if (null != json.get(ServiceConstants.COURIER_NAME) && null != json.get(ServiceConstants.SHIPPING_ID)) {
					String courierName = json.get(ServiceConstants.COURIER_NAME),
							shippingId = json.get(ServiceConstants.SHIPPING_ID);
					cart.setCourierName(courierName);
					cart.setShippingId(shippingId);
					if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
						float deliveryCharge = Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE));
						cart.setDeliveryCharge(deliveryCharge);
					}
				}

			}

			boolean updateCart = cartService.updateCart(cart);
			if (updateCart) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Descreption", "Cart shipping sucessfully");
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("Descreption", "Cart shipping sucessfully");
			}

		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Your date in insafficiant");
		}

		return ResponseEntity.ok().body(response);
	}

	// Complete cheak

	@GetMapping("acceptcart/{shopId}/{cartId}")
	public ResponseEntity<Map<String, String>> acceptCart(@PathVariable("shopId") String shopId,
			@PathVariable("cartId") int cartId) {
		Map<String, String> response = new HashMap<String, String>();
		Cart cart = cartService.getCartByShopIdAndCartId(shopId, cartId);
		LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "ACCEPTED");
		int accept = lookUp.getLookUpId();
		if (null != cart) {

			cart.setOrderStatus(accept);
			String userId = cart.getUserId();
			float totalAmount = cart.getPayableAmount();
			User user = UserService.getUser(Integer.parseInt(userId));
			user.setDues(user.getDues() + totalAmount);
			UserService.updateUser(user);
			boolean updateCart = cartService.updateCart(cart);
			if (updateCart) {
				response.put("status", Boolean.TRUE.toString());
				response.put("descreption", "Cart is accepted successfully");
				response.put("user dues", String.valueOf(user.getDues()));
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("descreption", "Cart does not accept");
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("descreption", "Cart is empty");
		}

		return ResponseEntity.ok().body(response);
		// return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	// Complete cheak

	@PostMapping("order/reject")
	public ResponseEntity<Map<String, String>> cartRejectStatus(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.CART_ID));
		Map<String, String> response = new HashMap<String, String>();

		if (null != json.get(ServiceConstants.CART_ID) && null != json.get(ServiceConstants.SHOP_ID)
				&& null != json.get(ServiceConstants.DESCRIPTION)) {
			int cartId = Integer.parseInt(json.get(ServiceConstants.CART_ID));
			String shopId = json.get(ServiceConstants.SHOP_ID), description = json.get(ServiceConstants.DESCRIPTION);
			Cart cart = cartService.getCartByShopIdAndCartId(shopId, cartId);

			if (null != cart) {
				List<AdminBillBook> adminBillBookList = null;
				AdminBillBook adminBillBook = null;
				List<UserBillBook> userBillBookList = null;
				UserBillBook userBillBook = null;
				String userId = cart.getUserId();
				int transactionType = cart.getTransactionType();
				float totalAmount = cart.getTotalAmount();
				String adminId = cart.getAdminId();
			

				LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "REJECTED");
				LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "ONLINE_PAYMENT");
				LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "CUSTOMER");
				LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "COMPLETED");
				LookUp lookUp4 = lookup.findLookUpIdByName("MILAAN", "ADMIN_REJECTED");
				int adminReject = lookUp4.getLookUpId();
				int transactionStatus = lookUp3.getLookUpId();
				int reject = lookUp.getLookUpId();
				int online = lookUp1.getLookUpId();
				int customer = lookUp2.getLookUpId();

				Transaction transaction = new Transaction(shopId, Integer.parseInt(userId));
				String order_rcptid_11 = Strings.EMPTY;
				order_rcptid_11 = UUID.randomUUID().toString().substring(0, 10);
//				User user = UserService.getUser(Integer.parseInt(userId));
//				user.setDues(dues);
//				cart.setActive(Boolean.FALSE);
				cart.setOrderStatus(reject);
				cart.setDescription(description);

				List<ProductList> productList = productListService.getProductListCartId(cartId);
				if (productList.size() > 0) {
					for (int i = 0; i < productList.size(); i++) {
						String productId = productList.get(i).getProductId();
						float quantity = productList.get(i).getProductQuantity();

						Product product = productService.getProduct(Integer.parseInt(productId));

						product.setStock(product.getStock() + (int) quantity);
						productService.updateProduct(product);
					}
				}
				if (transactionType == online) {
					adminBillBookList = adminBillBookService.getBillBookByAdminId(adminId);
					adminBillBook = adminBillBookList.get(0);
					adminBillBook.setDebit(adminBillBook.getDebit() + (int) totalAmount);
					adminBillBook.setLastDebitedOn(new Date());
					adminBillBook.setUpdatedOn(new Date());
					adminBillBookService.updateAdminBillBook(adminBillBook);

					userBillBookList = userBillBookService.getByUserId(userId);
					userBillBook = userBillBookList.get(0);
					userBillBook.setCredit(userBillBook.getCredit() + (int) totalAmount);
					userBillBook.setLastCreditedOn(new Date());
					userBillBook.setUpDatedOn(new Date());
					userBillBookService.updateUserBillBook(userBillBook);

					User user = UserService.getUser(Integer.parseInt(userId));
					user.setDues(user.getDues() - totalAmount);
					user.setWallet(user.getWallet() + totalAmount);
					UserService.updateUser(user);

					Admin admin = adminService.getAdminByShopId(shopId);
					admin.setWallet(admin.getWallet() - totalAmount);
					adminService.updateAdmin(admin);

					transaction.setActive(Boolean.TRUE);
					transaction.setAmount((int) totalAmount);
					transaction.setCartId(cartId);
					transaction.setCreatedOn(new Date());
					transaction.setDeleted(Boolean.FALSE);
					transaction.setDues(0);
					transaction.setPaymentMode(online);
					transaction.setOrderRcptidId(order_rcptid_11);
					transaction.setShopId(shopId);
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setAdminId(Integer.parseInt(adminId));
					transaction.setTotalAmount(totalAmount);
					transaction.setTransactionId(cart.getTransactionId());
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setUserType(customer);
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setTransactionStatus(transactionStatus);
					transaction.setTransactionType(adminReject);
					transaction.setAdminId(Integer.parseInt(adminId));
					transaction.setDiscreption(description);
				}
				boolean create = transactionService.createTransaction(transaction);
				if (create) {
					boolean updateCart = cartService.updateCart(cart);
					if (updateCart) {
						response.put("Status", Boolean.TRUE.toString());
						response.put("Description", "Cart has been rejected");
					} else {
						response.put("Status", Boolean.FALSE.toString());
						response.put("Description", "Cart has not been rejected");
					}
				}

			} else {
				response.put("Status", Boolean.FALSE.toString());
				response.put("Description", "Cart is empty");
			}

		}

		return ResponseEntity.ok().body(response);
		// return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	// Complete Cheak
	@GetMapping("cart/delivered/{cartId}/{otp}")
	public ResponseEntity<Map<String, String>> deliveredCart(@PathVariable("cartId") int cartId,
			@PathVariable("otp") int otp) {
		Map<String, String> response = new HashMap<String, String>();
		Cart cart = cartService.getCart(cartId);
		String adminId = cart.getAdminId();
		int transactionType = cart.getTransactionType();
		Admin admin = adminService.getAdmin(Integer.parseInt(adminId));
		Transaction transaction = null;
		if (otp == cart.getOtp()) {

			LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "DELIVERED");
			LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "CASH");
			LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "ONLINE_PAYMENT");
			LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "COMPLETED");
			int delivered = lookUp.getLookUpId();
			int cash = lookUp1.getLookUpId();
			int online = lookUp2.getLookUpId();
			int completed = lookUp3.getLookUpId();
			if (null != cart) {
				if (transactionType == online) {
					cart.setOrderStatus(delivered);
					cart.setActive(Boolean.FALSE);
					cart.setTransactionType(online);
//				transaction.setTransactionType(online);
					float payableAmount = cart.getPayableAmount();

					admin.setAvailableAmount(admin.getAvailableAmount() + payableAmount);
					adminService.updateAdmin(admin);
				} else {
					cart.setOrderStatus(delivered);
					cart.setActive(Boolean.FALSE);
					cart.setTransactionType(cash);
					// float payableAmount = cart.getPayableAmount();
					cart.setDues(cart.getDues() - cart.getPayableAmount());
					List<Transaction> transactionList = transactionService.getTransactionByCartId(cartId);
					transaction = transactionList.get(0);
					transaction.setTransactionType(cash);

					float totalAmount = cart.getPayableAmount(), amount = cart.getTotalAmount();
					String receiptId = Strings.EMPTY;
					receiptId = UUID.randomUUID().toString();

					transaction.setTransactionStatus(completed);
					transaction.setDiscreption("payment done of " + totalAmount + " by user " + cart.getMobileNo()
							+ " with order id " + transaction.getCartId() + "and transaction ID is" + receiptId);
					boolean transactionUpdate = transactionService.updateTransaction(transaction);

				}
				boolean result = cartService.updateCart(cart);
				if (result) {
					response.put("status", Boolean.TRUE.toString());
					response.put("Descreption", "Your order delivered sucessfully.");
				} else {
					response.put("status", Boolean.FALSE.toString());
					response.put("Descreption", "Your delivered order has been failed.");
				}
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("Descreption", "Your cart is empty.");
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("Descreption", "Your otp is not valid.");
		}
		return ResponseEntity.ok().body(response);
	}

	// Complete Cheak
	@PostMapping("order/denied")
	ResponseEntity<Map<String, String>> deniedOrder(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.CART_ID) && null != json.get(ServiceConstants.COMMENT)) {
			int cartId = Integer.parseInt(json.get(ServiceConstants.CART_ID));
			String comments = json.get(ServiceConstants.COMMENT);
			Cart cart = cartService.getCart(cartId);

			String adminId = cart.getAdminId();
			String userId = cart.getUserId();
			String shopId = cart.getShopId();
			int orderStatus = cart.getOrderStatus();
			int transactionType = cart.getTransactionType();

			float payableAmount = cart.getPayableAmount();
			List<AdminBillBook> adminBillBookList = null;
			List<UserBillBook> userBillBookList = null;
			AdminBillBook adminBillBook = null;
			UserBillBook userBillBook = null;
			LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "DELIVERED");
			LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "ONLINE_PAYMENT");
			LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "COMPLETED");
			LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "USER_DENIED");
			LookUp lookUp4 = lookup.findLookUpIdByName("MILAAN", "ADMIN");
			LookUp lookUp5 = lookup.findLookUpIdByName("MILAAN", "WALLET_PAYMENT");
			int denied = lookUp.getLookUpId();
			int online = lookUp1.getLookUpId();
			int complete = lookUp2.getLookUpId();
			int userDenied = lookUp3.getLookUpId();
			int userType = lookUp4.getLookUpId();
			int walletPayment = lookUp5.getLookUpId();
			Transaction transaction = new Transaction(shopId, Integer.parseInt(userId));
			if (null != cart) {
				cart.setOrderStatus(denied);
				cart.setActive(Boolean.TRUE);

				Admin admin = adminService.getAdmin(Integer.parseInt(adminId));
				admin.setWallet(admin.getWallet() - payableAmount);
				adminService.updateAdmin(admin);

				adminBillBookList = adminBillBookService.getBillBookByAdminId(adminId);
				adminBillBook = adminBillBookList.get(0);
				adminBillBook.setCredit(adminBillBook.getCredit() + (int) payableAmount);
				adminBillBook.setLastCreditedOn(new Date());
				adminBillBook.setUpdatedOn(new Date());
				boolean update = adminBillBookService.updateAdminBillBook(adminBillBook);
				if (update) {
					User user = UserService.getUser(Integer.parseInt(userId));
					user.setWallet(user.getWallet() + payableAmount);
					user.setDues(user.getDues() - payableAmount);
					UserService.updateUser(user);

					userBillBookList = userBillBookService.getByUserId(userId);
					userBillBook = userBillBookList.get(0);
					userBillBook.setDebit(userBillBook.getDebit() + (int) payableAmount);
					userBillBook.setLastDebitedOn(new Date());
					userBillBook.setUpDatedOn(new Date());
				}
				boolean updateUserBillBook = userBillBookService.updateUserBillBook(userBillBook);
				if (updateUserBillBook) {

					transaction.setActive(Boolean.TRUE);
					transaction.setAdminId(Integer.parseInt(adminId));
					transaction.setAmount((int) cart.getPayableAmount());
					transaction.setCartId(cartId);
					transaction.setCreatedOn(new Date());
					transaction.setDeleted(Boolean.FALSE);
					transaction.setDiscreption(comments);
					transaction.setDues(0);
					transaction.setPaid(payableAmount);
					transaction.setPaymentMode(walletPayment);
					transaction.setShopId(shopId);
					transaction.setTotalAmount(payableAmount);
					transaction.setTransactionStatus(complete);
					transaction.setTransactionType(userDenied);
					transaction.setUserId(Integer.parseInt(userId));
					transaction.setUserType(userType);

				}
				boolean createTransaction = transactionService.updateTransaction(transaction);
				cart.setReview(comments);
				String name = cart.getUserName();
				if (createTransaction) {
					Review review = new Review(userId, shopId);
					review.setActive(Boolean.TRUE);
					review.setComment(comments);
					review.setCreatedOn(new Date());
					review.setDeleted(Boolean.FALSE);
					review.setName(name);
					review.setShopId(shopId);
					review.setUserId(userId);
					reviewService.createReview(review);

				}

			}
			boolean updateCart = cartService.updateCart(cart);
			if (updateCart) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Descreption", "Your order is denied");
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("Descreption", "Your denied order has been failed.");
			}

		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("status", "Please enter cart Id and comments");
		}
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("completpayment")
	ResponseEntity<Map<String, String>> completePayment(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.CART_ID) && null != json.get(ServiceConstants.TRANSACTION_ID)
				&& null != json.get(ServiceConstants.RZPAY_TRANSECTION_ID)) {
			int cartId = Integer.parseInt(json.get(ServiceConstants.CART_ID));
			String transactionId = json.get(ServiceConstants.TRANSACTION_ID),
					rzpayTransectionId = json.get(ServiceConstants.RZPAY_TRANSECTION_ID);
			Cart cart = cartService.getCart(cartId);
			String shopId = cart.getShopId();
			String adminId = cart.getAdminId();
			String userId = cart.getUserId();
			User user = UserService.getUser(Integer.parseInt(userId));
			if (null != user && user.getIsActive() && null != transactionId && !Strings.isEmpty(rzpayTransectionId)) {

				Admin admin = adminService.getAdminByShopId(shopId);
				List<AdminBillBook> adminBillBookList = null;
				List<UserBillBook> userBillBookList = null;
				AdminBillBook adminBillBook = null;
				UserBillBook userBillBook = null;
				float payableAmount = cart.getPayableAmount();
				LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "PLACED");
				LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "COMPLETED");
				LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "ADMIN");
				LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "CUSTOMER");
				int place = lookUp.getLookUpId();
				int complete = lookUp1.getLookUpId();
				int userType = lookUp2.getLookUpId();

				cart.setOrderStatus(place);
				cart.setPaid(payableAmount);
				cart.setDues(cart.getDues() - payableAmount);
				cart.setTransactionId(transactionId);
				cart.setTransactionType(complete);
				cart.setOrderDate(new Date());

				cartService.updateCart(cart);
				Transaction transaction = transactionService.getTransaction(Integer.parseInt(transactionId));
				transaction.setAmount((int) payableAmount);
				transaction.setDues(0);
				transaction.setTotalAmount(payableAmount);
				transaction.setTransactionStatus(complete);
				transaction.setOrderRcptidId(rzpayTransectionId);
				transaction.setDiscreption("payment done of " + payableAmount + " by user " + user.getMobileNo()
						+ " with order id " + transaction.getCartId() + "and transaction ID is" + rzpayTransectionId);
				boolean updateTransaction = transactionService.updateTransaction(transaction);
				if (updateTransaction) {
					admin.setWallet(admin.getWallet() + payableAmount);
					adminService.updateAdmin(admin);

					boolean exitsAdmin = adminBillBookService.adminBillBookExists(adminId);
					if (exitsAdmin) {
						adminBillBookList = adminBillBookService.getBillBookByAdminId(adminId);
						adminBillBook = adminBillBookList.get(0);
						adminBillBook.setCredit(adminBillBook.getCredit() + (int) payableAmount);
						adminBillBook.setLastCreditedOn(new Date());
						adminBillBook.setUpdatedOn(new Date());
						adminBillBookService.updateAdminBillBook(adminBillBook);

					} else {
						adminBillBook = new AdminBillBook(shopId, adminId);
						adminBillBook.setActive(Boolean.TRUE);
						adminBillBook.setAdminId(adminId);
						adminBillBook.setCreatedOn(new Date());
						adminBillBook.setCredit((int) payableAmount);
						adminBillBook.setDeleted(Boolean.FALSE);
						adminBillBook.setLastDebitedOn(new Date());
						adminBillBook.setShopId(shopId);
						adminBillBook.setUpdatedOn(new Date());
						adminBillBook.setUserType(userType);
					}
					boolean updateAdminBillBook = adminBillBookService.createAdminBillBook(adminBillBook);

					boolean userBillBookExists = userBillBookService.userBillBookExists(userId);
					if (userBillBookExists) {
						userBillBookList = userBillBookService.getByUserId(userId);
						userBillBook = userBillBookList.get(0);
						userBillBook.setDebit(userBillBook.getDebit() + (int) payableAmount);
						userBillBook.setLastDebitedOn(new Date());
						userBillBook.setUpDatedOn(new Date());
						userBillBookService.updateUserBillBook(userBillBook);
					} else {
						userBillBook = new UserBillBook(shopId, userId);
						userBillBook.setActive(Boolean.TRUE);
						userBillBook.setCreatedOn(new Date());
						userBillBook.setDebit((int) payableAmount);
						userBillBook.setDeleted(Boolean.FALSE);
						userBillBook.setLastDebitedOn(new Date());
						userBillBook.setShopId(shopId);
						userBillBook.setUpDatedOn(new Date());
						userBillBook.setUserId(userId);
						userBillBook.setUserType(userType);
						userBillBookService.createUserBillBook(userBillBook);
					}

					List<ProductList> productList = productListService.getProductListCartId(cartId);
					if (productList.size() > 0) {
						for (int i = 0; i > productList.size(); i++) {
							String productId = productList.get(i).getProductId();
							float quantity = productList.get(i).getProductQuantity();
							Product product = productService.getProduct(Integer.parseInt(productId));
							product.setStock(product.getStock() - (int) quantity);
							productService.updateProduct(product);
						}
					}

				} else {
					response.put("status", String.valueOf(false));
					response.put("description", "Your order does not been placed due to transaction failed.");
				}
			}
			response.put("status", Boolean.TRUE.toString());
			response.put("descreption", "Your order placed sucessfully");

		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Your order does not place");

		}
		return ResponseEntity.ok().body(response);
	}

//	@GetMapping("cartincrease/{cartId}/{productId}")
//	public ResponseEntity<Map<String, String>> getCartByCartIdAndProductId(@PathVariable("cartId") int cartId,
//			@PathVariable("productId") String productId) {
//		Map<String, String> response = new HashMap<String, String>();
//		Cart cart = cartService.getCart(cartId);
//		ProductList productList = productListService.getProductByProductIdAndCartId(productId, cartId);
//		Product product = productService.getProduct(Integer.parseInt(productId));
//		float totalAmount = product.getPrice(), gstAmount = product.getGstAmount(), discount = product.getDiscount(),
//				deliveryCharge = product.getDeliveryCharge();
//
//		cart.setTotalAmount(cart.getTotalAmount() + totalAmount);
//		cart.setGstAmount(cart.getGstAmount() + gstAmount);
//		cart.setDiscount(cart.getDiscount() + discount);
//
//		boolean updateCart = cartService.updateCart(cart);
//
//		if (updateCart) {
//			productList.setProductQuantity(productList.getProductQuantity() + 1);
//			productList.setPrice(productList.getPrice() + totalAmount);
//			productList.setGstAmount(productList.getGstAmount() + gstAmount);
//			productList.setDiscount(productList.getDiscount() + discount);
//			productList.setOldPrice(productList.getOldPrice() + product.getOldPrice());
//
//			boolean updateProductList = productListService.updateProductList(productList);
//
//			if (updateProductList) {
//				response.put("status", Boolean.TRUE.toString());
//				response.put("description", "Product updated");
//			} else {
//				response.put("status", Boolean.FALSE.toString());
//				response.put("description", "Product does not updated");
//			}
//
//		} else {
//			response.put("status", Boolean.FALSE.toString());
//			response.put("description", "Product does not updated");
//		}
//
//		return ResponseEntity.ok().body(response);
//	}

//	@GetMapping("cartdecrease/{cartId}/{productId}")
//	public ResponseEntity<Map<String, String>> cartDecrease(@PathVariable("cartId") int cartId,
//			@PathVariable("productId") String productId) {
//		Map<String, String> response = new HashMap<String, String>();
//		Cart cart = cartService.getCart(cartId);
//		ProductList productList = productListService.getProductByProductIdAndCartId(productId, cartId);
//		Product product = productService.getProduct(Integer.parseInt(productId));
//		float totalAmount = product.getPrice(), gstAmount = product.getGstAmount(), discount = product.getDiscount(),
//				deliveryCharge = product.getDeliveryCharge();
//
//		cart.setTotalAmount(cart.getTotalAmount() - totalAmount);
//		cart.setGstAmount(cart.getGstAmount() - gstAmount);
//		cart.setDiscount(cart.getDiscount() - discount);
//
//		boolean updateCart = cartService.updateCart(cart);
//
//		if (updateCart) {
//			productList.setProductQuantity(productList.getProductQuantity() - 1);
//			productList.setPrice(productList.getPrice() - totalAmount);
//			productList.setGstAmount(productList.getGstAmount() - gstAmount);
//			productList.setDiscount(productList.getDiscount() - discount);
//			productList.setOldPrice(productList.getOldPrice() - product.getOldPrice());
//
//			boolean updateProductList = productListService.updateProductList(productList);
//
//			if (updateProductList) {
//				response.put("status", Boolean.TRUE.toString());
//				response.put("description", "Product updated");
//			} else {
//				response.put("status", Boolean.FALSE.toString());
//				response.put("description", "Product does not updated");
//			}
//
//		} else {
//			response.put("status", Boolean.FALSE.toString());
//			response.put("description", "Product does not updated");
//		}
//
//		return ResponseEntity.ok().body(response);
//	}
	// Complete Cheak
	@SuppressWarnings({})
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createCart1(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to create user: {}", json.get(ServiceConstants.USER_ID));
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.USER_ID) && null != json.get(ServiceConstants.SHOP_ID)
				&& null != json.get(ServiceConstants.PRODUCT_ID)
				&& null != json.get(ServiceConstants.PRODUCT_QUANTITY)) {
			String shopId = json.get(ServiceConstants.SHOP_ID), userId = json.get(ServiceConstants.USER_ID),
					productId = json.get(ServiceConstants.PRODUCT_ID);
			int productQuantity = Integer.parseInt(json.get(ServiceConstants.PRODUCT_QUANTITY));

			LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "DEACTIVE");
			int orderStatus = lookUp.getLookUpId();
			List<Cart> cartList = null;
			Admin admin = adminService.getAdminByShopId(shopId);
			User user = UserService.getUser(Integer.parseInt(userId));
			Cart cart = null;

			ProductList productList = null;
			Date offerFrom = null, offerTo = null;
			int totalAmount = 0, gstAmount = 0, paid = 0, dues = 0, discount = 0, price = 0, deliveryCharge = 0;
			Product product = productService.getProduct(Integer.parseInt(productId));
			if (product.getStock() > productQuantity) {
				boolean result = cartService.cartExists(userId, shopId, orderStatus);
				if (result) {

					cartList = cartService.getDeactiveCartByUserIdAndShopId(shopId, userId, orderStatus);
					cart = cartList.get(0);

					gstAmount = (int) product.getGstAmount() * productQuantity;
					discount = (int) product.getDiscount() * productQuantity;
					price = (int) product.getPrice() * productQuantity;
					totalAmount = price + gstAmount;
					boolean result1 = productListService.getProductByProductId(productId, cart.getCartId());
					if (result1) {

					} else {
						productList = new ProductList(cart.getCartId(), shopId);
						productList.setActive(Boolean.TRUE);
						productList.setCartId(cart.getCartId());
						productList.setCreatedOn(new Date());
						productList.setDeleted(Boolean.FALSE);
						productList.setDeliveryCharge(cart.getDeliveryCharge());
						productList.setTotalAmount(totalAmount);
						productList.setGstAmount(gstAmount);
						productList.setMeasurement(product.getMeasurement());
						if (product.isOfferActiveInd()) {

							productList.setDiscount(discount);

							offerFrom = product.getOfferFrom();
							offerTo = product.getOfferTo();
							productList.setOffer(product.getOfferPercent());
							productList.setOfferFrom(offerFrom);
							productList.setOfferTo(offerTo);
						}
						productList.setPrice(price);
						productList.setOldPrice(product.getOldPrice() * productQuantity);
						productList.setProductId(String.valueOf(product.getProductId()));
						productList.setProductName(product.getName());
						productList.setProductQuantity(productQuantity);
						productList.setShopId(product.getShopId());
						productList.setUserId(userId);
						productList.setStock(product.getStock() - productQuantity);
						productList.setGstPrecent((int) product.getGstPercent() * productQuantity);

						cart.setGstAmount(cart.getGstAmount() + gstAmount);
						cart.setDiscount(cart.getDiscount() + discount);
						cart.setPaid(cart.getPaid() + paid);
						cart.setDeliveryCharge(deliveryCharge);
						cart.setPayableAmount(cart.getPayableAmount() + totalAmount);
						cart.setPrice(cart.getPrice() + price);
						cart.setTotalAmount(cart.getTotalAmount() + totalAmount);
						cartService.updateCart(cart);

						boolean productListCreate = productListService.createProductList(productList);
						// boolean productList = productListService.createProductList(productList);
						response.put("status", Strings.EMPTY + productListCreate);
						response.put("description",
								"ProductList created successfully with given Shop Id , go through your inbox to activate");

					}
				} else {
					cart = new Cart(userId, shopId);
					gstAmount = (int) product.getGstAmount() * productQuantity;
					discount = (int) product.getDiscount() * productQuantity;
					price = (int) product.getPrice() * productQuantity;
					totalAmount = price + gstAmount;

					cart.setActive(Boolean.TRUE);
					cart.setAdminId(String.valueOf(admin.getAdminId()));
					cart.setCreatedOn(new Date());
					cart.setDeleted(Boolean.FALSE);
					cart.setDeliveryCharge(product.getDeliveryCharge());
					cart.setDiscount(discount);
					cart.setDues(totalAmount);
					cart.setGstAmount(gstAmount);
					cart.setOrderStatus(orderStatus);
					cart.setOrderDate(new Date());
					cart.setPaid(0);
					cart.setDeliveryCharge(deliveryCharge);
					cart.setPayableAmount(totalAmount);
					cart.setPrice(price);
					cart.setShopId(shopId);
					cart.setShopName(admin.getShopName());
					cart.setTotalAmount(totalAmount);
					cart.setUserId(userId);
					cart.setUserName(user.getFirstName());
					boolean createCart = cartService.createCart(cart);
					if (createCart) {
						productList = new ProductList(cart.getCartId(), shopId);
						productList.setActive(Boolean.TRUE);
						productList.setCartId(cart.getCartId());
						productList.setCreatedOn(new Date());
						productList.setDeleted(Boolean.FALSE);
						productList.setDeliveryCharge(cart.getDeliveryCharge());
						productList.setProductName(product.getName());
						productList.setProductQuantity(productQuantity);
						productList.setShopId(product.getShopId());
						productList.setUserId(userId);

						if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
							float deliveryCharge1 = Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE));
							productList.setDeliveryCharge(deliveryCharge1);
						}
						if (product.isOfferActiveInd()) {
							offerFrom = product.getOfferFrom();
							offerTo = product.getOfferTo();
							productList.setOffer(product.getOfferPercent());
							productList.setOfferFrom(offerFrom);
							productList.setOfferTo(offerTo);
							productList.setDiscount(discount);
						}
						productList.setOldPrice(product.getOldPrice() * productQuantity);
						productList.setProductId(String.valueOf(product.getProductId()));

						productList.setGstAmount(product.getGstAmount() * productQuantity);
						productList.setProductQuantity(productQuantity);
						productList.setPrice(product.getPrice() * productQuantity);
						productList.setMeasurement(product.getMeasurement());
						productList.setGstPrecent((int) product.getGstPercent());
						productList.setStock(product.getStock() - productQuantity);
						productList.setTotalAmount(totalAmount);
					}

					boolean result1 = productListService.createProductList(productList);
					response.put("status", Strings.EMPTY + result1);
					response.put("description", "ProductList created successfully with given Shop ID.");
				}
				response.put("status", Boolean.TRUE.toString());
				response.put("descreption", "Cart created");

			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("description", "Product is out of stock");
			}

		}

		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/update")
	ResponseEntity<Map<String, String>> UpdateCart(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.NAME));
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.ID)
				&& null != cartService.getCart(Integer.parseInt(json.get(ServiceConstants.ID)))) {
			Cart cart = cartService.getCart(Integer.parseInt(json.get(ServiceConstants.ID)));

			if (null != json.get(ServiceConstants.ID)) {
				int id = Integer.parseInt(json.get(ServiceConstants.ID).toString());
				cart.setCartId(id);
			}

			if (null != json.get(ServiceConstants.SHOP_ID)) {
				String shopId = json.get(ServiceConstants.SHOP_ID).toString();
				cart.setShopId(shopId);
			}

			if (null != json.get(ServiceConstants.USER_ID)) {
				String userId = json.get(ServiceConstants.USER_ID).toString();
				cart.setUserId(userId);
			}

			if (null != json.get(ServiceConstants.CREATED_ON)) {
				Date createdOn = new Date();
				cart.setCreatedOn(createdOn);
			}

			if (null != json.get(ServiceConstants.GST_AMOUNT)) {
				Float gstAmount = Float.parseFloat((json.get(ServiceConstants.GST_AMOUNT).toString()));
				cart.setGstAmount(gstAmount);
			}

			if (null != json.get(ServiceConstants.TOTAL_AMOUNT)) {
				Float totalAmount = Float.parseFloat((json.get(ServiceConstants.TOTAL_AMOUNT).toString()));
				cart.setTotalAmount(totalAmount);
			}

			if (null != json.get(ServiceConstants.TRANSACTION_ID)) {
				String transactionId = json.get(ServiceConstants.TRANSACTION_ID).toString();
				cart.setTransactionId(transactionId);
			}

			if (null != json.get(ServiceConstants.DUES)) {
				float dues = Float.parseFloat((json.get(ServiceConstants.DUES).toString()));
				cart.setDues(dues);
			}
			if (null != json.get(ServiceConstants.PAID)) {
				float paid = Float.parseFloat((json.get(ServiceConstants.PAID).toString()));
				cart.setPaid(paid);
			}

			if (null != json.get(ServiceConstants.CREATED_ON)) {
				Date createdOn = new Date();
				System.out.println(createdOn);
				cart.setCreatedOn(createdOn);
			}

			if (null != json.get(ServiceConstants.GST_AMOUNT)) {
				Float gstAmount = Float.parseFloat((json.get(ServiceConstants.GST_AMOUNT).toString()));
				System.out.println(gstAmount);
				cart.setGstAmount(gstAmount);
			}

			if (null != json.get(ServiceConstants.TOTAL_AMOUNT)) {
				Float totalAmount = Float.parseFloat((json.get(ServiceConstants.TOTAL_AMOUNT).toString()));
				System.out.println(totalAmount);
				cart.setTotalAmount(totalAmount);
			}

			if (null != json.get(ServiceConstants.TRANSACTION_ID)) {
				String transactionId = json.get(ServiceConstants.TRANSACTION_ID).toString();
				System.out.println(transactionId);
				cart.setTransactionId(transactionId);
			}

			if (null != json.get(ServiceConstants.DUES)) {
				float dues = Float.parseFloat((json.get(ServiceConstants.DUES).toString()));
				System.out.println(dues);
				cart.setDues(dues);
			}
			if (null != json.get(ServiceConstants.PAID)) {
				float paid = Float.parseFloat((json.get(ServiceConstants.PAID).toString()));
				System.out.println(paid);
				cart.setPaid(paid);
			}

			if (null != json.get(ServiceConstants.IS_ACTIVE)) {
				boolean isActive = Boolean.parseBoolean((json.get(ServiceConstants.IS_ACTIVE).toString()));
				System.out.println(isActive);
				cart.setActive(isActive);
			}

			if (null != json.get(ServiceConstants.IS_DELETED)) {
				boolean isDeleted = Boolean.parseBoolean((json.get(ServiceConstants.IS_DELETED).toString()));
				System.out.println(isDeleted);
				cart.setDeleted(isDeleted);
			}

			if (null != json.get(ServiceConstants.ORDER_STATUS)) {
				int orderStatus = Integer.parseInt((json.get(ServiceConstants.ORDER_STATUS).toString()));
				System.out.println(orderStatus);
				cart.setOrderStatus(orderStatus);
			}

			if (null != json.get(ServiceConstants.TRANSACTION_TYPE)) {
				int transactionType = Integer.parseInt(json.get(ServiceConstants.TRANSACTION_TYPE).toString());
				System.out.println(transactionType);
				cart.setTransactionType(transactionType);
			}
			if (null != json.get(ServiceConstants.ORDER_DATE)) {
				Date orderDate = new Date();
				System.out.println(orderDate);
				cart.setOrderDate(orderDate);
			}

			if (null != json.get(ServiceConstants.IS_ACTIVE)) {
				boolean isActive = Boolean.parseBoolean((json.get(ServiceConstants.IS_ACTIVE).toString()));
				cart.setActive(isActive);
			}

			if (null != json.get(ServiceConstants.IS_DELETED)) {
				boolean isDeleted = Boolean.parseBoolean((json.get(ServiceConstants.IS_DELETED).toString()));
				cart.setDeleted(isDeleted);
			}

			cartService.updateCart(cart);
			response.put("status", Boolean.TRUE.toString());
			response.put("description", "Cart updated");
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Cart doesn't exist with given  userid");
		}
		return ResponseEntity.ok().body(response);
	}

//	@SuppressWarnings({})
//	@PostMapping("/create")
//	ResponseEntity<Map<String, String>> createCart(@Valid @RequestBody Map<String, String> json,
//			HttpServletRequest request) throws URISyntaxException {
//		log.info("Request to create user: {}", json.get(ServiceConstants.USER_ID));
//		Map<String, String> response = new HashMap<String, String>();
//		if (null != json.get(ServiceConstants.USER_ID) && null != json.get(ServiceConstants.SHOP_ID)
//				&& null != json.get(ServiceConstants.PRODUCT_ID)
//				&& null != json.get(ServiceConstants.PRODUCT_QUANTITY)) {
//			String shopId = json.get(ServiceConstants.SHOP_ID);
//			Cart cart = null;
//			List<Cart> cartList = null;
//			Product product = null;
//			ProductList productList = null;
//			LookUp lookup1 = lookup.findLookUpIdByName(shopId, "DEACTIVE");
//			int orderStatus = lookup1.getLookUpId();
//
//			String userId = json.get(ServiceConstants.USER_ID), productId = json.get(ServiceConstants.PRODUCT_ID);
//
//			product = productService.getProduct(Integer.parseInt(productId));
//			float productQuantity = Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY));
//
//			boolean result = cartService.cartExists(userId, shopId, Boolean.TRUE);
//			if (result) {
//				cartList = cartService.getCartByUserId(userId, shopId, Boolean.TRUE);
//				cart = cartList.get(0);
//				float totalAmount = product.getPrice() * productQuantity,
//						gstAmount = product.getGstAmount() * productQuantity,
//						discount = product.getDiscount() * productQuantity,
//						deliveryCharge = product.getDeliveryCharge();
//
//				boolean productList1 = productListService.getProductByProductId(productId, cart.getCartId());
//
//				if (productList1) {
//
//				} else {
//					productList = new ProductList(cart.getCartId(), shopId);
//
//					productList.setUserId(json.get(ServiceConstants.USER_ID));
//					productList.setShopId(json.get(ServiceConstants.SHOP_ID));
//					productList.setProductQuantity(Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY)));
//					productList.setProductName(product.getName());
//					productList.setProductId(json.get(ServiceConstants.PRODUCT_ID));
//					productList.setPrice(totalAmount);
//					productList.setOfferTo(new Date());
//					productList.setOffersAvailable(product.isOfferActiveInd());
//					if (product.isOfferActiveInd()) {
//						productList.setOldPrice(product.getOldPrice());
//						productList.setOffer(product.getOfferPercent());
//						productList.setDiscount(discount);
//					}
//					productList.setDeliveryCharge(deliveryCharge);
////					if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
////						productList.setDeliveryCharge(json.get(ServiceConstants.DELIVERY_CHARGE));
////					}
//
//					productList.setOfferFrom(new Date());
//					productList.setMeasurement(product.getMeasurement());
//					productList.setGstAmount(product.getGstAmount());
//					productList.setDeleted(Boolean.FALSE);
//					productList.setCreatedOn(new Date());
//					productList.setCartId(cart.getCartId());
//					productList.setActive(Boolean.TRUE);
//
//					cart.setTotalAmount(cart.getTotalAmount() + totalAmount);
//					cart.setGstAmount(cart.getGstAmount() + gstAmount);
//					cart.setDues(cart.getDues() + totalAmount);
//					cart.setDiscount(cart.getDiscount() + discount);
//					cart.setDeliveryCharge(deliveryCharge);
//					boolean finalResult = cartService.updateCart(cart);
//
//					boolean result1 = productListService.createProductList(productList);
//					response.put("status", Strings.EMPTY + result1);
//					response.put("description",
//							"ProductList created successfully with given Shop Id , go through your inbox to activate");
//				}
//			} else {
//				cart = new Cart(userId, shopId);
//				productList = new ProductList(cart.getCartId(), shopId);
//				float totalAmount = product.getPrice() * productQuantity,
//						gstAmount = product.getGstAmount() * productQuantity,
//						discount = product.getDiscount() * productQuantity,
//						deliveryCharge = product.getDeliveryCharge();
//
//				cart.setTotalAmount(totalAmount);
//				cart.setGstAmount(gstAmount);
//				cart.setCreatedOn(new Date());
//				cart.setOrderStatus(orderStatus);
//				cart.setDues(totalAmount);
//				cart.setPaid(0);
//				cart.setShopId(shopId);
//				cart.setUserId(userId);
//				cart.setActive(Boolean.TRUE);
//				cart.setDeleted(Boolean.FALSE);
//				cart.setDiscount(discount);
//				cart.setDeliveryCharge(deliveryCharge);
//				boolean finalResult = cartService.createCart(cart);
//
//				if (finalResult) {
//					result = cartService.cartExists(userId, shopId, Boolean.TRUE);
//					if (result) {
//						productList = new ProductList(cart.getCartId(), shopId);
//
//						productList.setUserId(json.get(ServiceConstants.USER_ID));
//						productList.setShopId(json.get(ServiceConstants.SHOP_ID));
//						productList.setProductQuantity(Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY)));
//						productList.setProductName(product.getName());
//						productList.setProductId(json.get(ServiceConstants.PRODUCT_ID));
//						productList.setPrice(totalAmount);
//						productList.setOfferTo(new Date());
//						productList.setOffersAvailable(product.isOfferActiveInd());
//						if (product.isOfferActiveInd()) {
//							productList.setOldPrice(product.getOldPrice());
//							productList.setOffer(product.getOfferPercent());
//							productList.setDiscount(discount);
//						}
//
//						if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
//							productList.setDeliveryCharge(Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE)));
//						}
//
//						productList.setOfferFrom(new Date());
//						productList.setMeasurement(product.getMeasurement());
//						productList.setGstAmount(product.getGstAmount());
//						productList.setDeleted(Boolean.FALSE);
//						productList.setCreatedOn(new Date());
//						productList.setCartId(cart.getCartId());
//						productList.setActive(Boolean.TRUE);
//
//						boolean result1 = productListService.createProductList(productList);
//						response.put("status", Strings.EMPTY + result1);
//						response.put("description", "ProductList created successfully with given Shop Id.");
//					}
//
//				}
//			}
//		}
//		return ResponseEntity.ok().body(response);
//	}

//	@SuppressWarnings({})
//	@PostMapping("/addproduct")
//	ResponseEntity<Map<String, String>> addProduct(@Valid @RequestBody Map<String, String> json,
//			HttpServletRequest request) throws URISyntaxException {
//		log.info("Request to create user: {}", json.get(ServiceConstants.USER_ID));
//		Map<String, String> response = new HashMap<String, String>();
//		if (null != json.get(ServiceConstants.USER_ID) && null != json.get(ServiceConstants.SHOP_ID)
//				&& null != json.get(ServiceConstants.PRODUCT_ID)
//				&& null != json.get(ServiceConstants.PRODUCT_QUANTITY)) {
//
//			List<Cart> cartList = null;
//			Cart cart = null;
//			List<ProductList> productList = null;
//			ProductList cartProduct = null;
//
//			String userId = json.get(ServiceConstants.USER_ID), shopId = json.get(ServiceConstants.SHOP_ID),
//					productId = json.get(ServiceConstants.PRODUCT_ID);
//			float productQuantity = Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY));
//
//			Product product = productService.getProduct(Integer.parseInt(productId));
//
//			boolean result = cartService.cartExists(userId, shopId, Boolean.TRUE);
//
//			if (result) {
//				cartList = cartService.getCartByUserId(userId, shopId, Boolean.TRUE);
//				cart = cartList.get(0);
//				float totalAmount = product.getPrice() * productQuantity,
//						gstAmount = product.getGstAmount() * productQuantity,
//						discount = product.getDiscount() * productQuantity;
//
//				cart.setTotalAmount(cart.getTotalAmount() + totalAmount);
//				cart.setGstAmount(cart.getGstAmount() + gstAmount);
////				productList = cart.getProductList();
//
//				 = productListService.getProductByProductIdAndCartId(productId, cart.getCartId());
//
//				if (null != cartProduct) {
//					cartProduct.setProductQuantity(productQuantity);
//					productListService.updateProductList(cartProduct);
//				} else {
//					cartProduct = new ProductList(cart.getCartId(), shopId);
//
//					cartProduct.setUserId(json.get(ServiceConstants.USER_ID));
//					cartProduct.setShopId(json.get(ServiceConstants.SHOP_ID));
//					cartProduct.setProductQuantity(Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY)));
//					cartProduct.setProductName(product.getName());
//					cartProduct.setProductId(json.get(ServiceConstants.PRODUCT_ID));
//					cartProduct.setPrice(totalAmount);
//					cartProduct.setOfferTo(new Date());
//					cartProduct.setOffersAvailable(product.isOfferActiveInd());
//					if (product.isOfferActiveInd()) {
//						cartProduct.setOldPrice(product.getOldPrice());
//						cartProduct.setOffer(product.getOfferPercent());
//						cartProduct.setDiscount(discount);
//					}
//
//					if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
//						cartProduct.setDeliveryCharge(Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE)));
//					}
//
//					cartProduct.setOfferFrom(new Date());
//					cartProduct.setMeasurement(product.getMeasurement());
//					cartProduct.setGstAmount(product.getGstAmount());
//					cartProduct.setDeleted(Boolean.FALSE);
//					cartProduct.setCreatedOn(new Date());
//					cartProduct.setCartId(cart.getCartId());
//					cartProduct.setActive(Boolean.TRUE);
//
//					boolean result1 = productListService.createProductList(cartProduct);
//					response.put("status", Strings.EMPTY + result1);
//					response.put("description",
//							"ProductList created successfully with given Shop Id , go through your inbox to activate");
//				}
//
//			} else {
//				cart = new Cart(userId, shopId);
//				cartProduct = new ProductList(cart.getCartId(), shopId);
//				float totalAmount = product.getPrice() * productQuantity,
//						gstAmount = product.getGstAmount() * productQuantity,
//						discount = product.getDiscount() * productQuantity;
//
//				cart.setTotalAmount(totalAmount);
//				cart.setGstAmount(gstAmount);
//				cart.setActive(Boolean.TRUE);
//				cart.setDeleted(Boolean.FALSE);
//				cart.setCreatedOn(new Date());
//				cart.setOrderStatus(Boolean.TRUE);
//				cart.setDues(totalAmount);
//				cart.setPaid(0);
//				cart.setShopId(shopId);
//				cart.setUserId(userId);
//				boolean finalResult = cartService.createCart(cart);
//
//				if (finalResult) {
//					if (cartService.cartExists(userId, shopId, Boolean.TRUE)) {
//						cartProduct = new ProductList(cart.getCartId(), shopId);
//
//						cartProduct.setUserId(json.get(ServiceConstants.USER_ID));
//						cartProduct.setShopId(json.get(ServiceConstants.SHOP_ID));
//						cartProduct.setProductQuantity(Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY)));
//						cartProduct.setProductName(product.getName());
//						cartProduct.setProductId(json.get(ServiceConstants.PRODUCT_ID));
//						cartProduct.setPrice(totalAmount);
//						cartProduct.setOfferTo(new Date());
//						cartProduct.setOffersAvailable(product.isOfferActiveInd());
//						if (product.isOfferActiveInd()) {
//							cartProduct.setOldPrice(product.getOldPrice());
//							cartProduct.setOffer(product.getOfferPercent());
//							cartProduct.setDiscount(discount);
//						}
//
//						if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
//							cartProduct.setDeliveryCharge(Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE)));
//						}
//
//						cartProduct.setOfferFrom(new Date());
//						cartProduct.setMeasurement(product.getMeasurement());
//						cartProduct.setGstAmount(product.getGstAmount());
//						cartProduct.setDeleted(Boolean.FALSE);
//						cartProduct.setCreatedOn(new Date());
//						cartProduct.setCartId(cart.getCartId());
//						cartProduct.setActive(Boolean.TRUE);
//
//						boolean result1 = productListService.createProductList(cartProduct);
//						response.put("status", Strings.EMPTY + result1);
//						response.put("description",
//								"ProductList created successfully with given Shop Id , go through your inbox to activate");
//					}
//				}
//			}
//		}
//		return ResponseEntity.ok().body(response);
//	}
}
