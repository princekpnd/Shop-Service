package com.shop.shopservice.controller;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.shop.shopservice.constants.ServiceConstants;
import com.shop.shopservice.entity.Product;
import com.shop.shopservice.entity.ProductList;
import com.shop.shopservice.service.IProductListService;

@RestController
@RequestMapping("/api/productlist")

public class ProductListController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IProductListService productListService;

	@GetMapping("getallproductlist")
	public ResponseEntity<List<ProductList>> getAllProductList() {
		List<ProductList> productList = productListService.getAllProductList();
		return new ResponseEntity<List<ProductList>>(productList, HttpStatus.OK);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<ProductList> getProductListById(@PathVariable("id") int id) {
		ProductList productList = productListService.getProductListById(id);
		return new ResponseEntity<ProductList>(productList, HttpStatus.OK);
	}

	@GetMapping("getproductlistbyuserid/{userId}")
	public ResponseEntity<List<ProductList>> getProductListByUserId(@PathVariable("userId") String userId) {
		List<ProductList> productList = productListService.getProductListByUserId(userId);
		return new ResponseEntity<List<ProductList>>(productList, HttpStatus.OK);
	}

	@GetMapping("getproductlistbyshopid/{shopId}/{cartId}")
	public ResponseEntity<List<ProductList>> getProductListByShopId(@PathVariable("shopId") String shopId, @PathVariable("cartId") String cartId) {
		List<ProductList> productList = productListService.getProductListByShopId(shopId);
		return new ResponseEntity<List<ProductList>>(productList, HttpStatus.OK);
	}
	
//	@GetMapping("getproductlistbycartid/{cartId}")
//	public ResponseEntity<List<ProductList>> getProductListByShopId(@PathVariable("cartId") String cartId) {
//		List<ProductList> productList = productListService.getProductListByShopId(shopId);
//		return new ResponseEntity<List<ProductList>>(productList, HttpStatus.OK);
//	}
	
	@GetMapping("getproductlistbyproductid/{productId}/{cartId}")
	public ResponseEntity<Boolean> getProductListByUserIdAndShopId(@PathVariable("productId") String productId,@PathVariable("cartId") int cartId){
		boolean productList = productListService.getProductByProductId(productId, cartId);
		return new ResponseEntity<Boolean>(productList, HttpStatus.OK);
		
	}
	
	@GetMapping("getproductlistbyproductidandcartid/{productId}/{cartId}")
	public ResponseEntity<ProductList> getProductByProductIdAndCartId(@PathVariable("productId") String productId,@PathVariable("cartId") int cartId){
		ProductList productList = productListService.getProductByProductIdAndCartId(productId, cartId);
		return new ResponseEntity<ProductList>(productList, HttpStatus.OK);
		
	}
	
	@GetMapping("getbycartid/{cartId}")
	public ResponseEntity<List<ProductList>> getProductByProductListCartId(@PathVariable("cartId") int cartId){
		List<ProductList> productList = productListService.getProductListCartId(cartId);
		return new ResponseEntity<List<ProductList>>(productList, HttpStatus.OK);
		
	}
	

	@PutMapping("/update")
	ResponseEntity<Map<String, String>> UpdateCart(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.NAME));
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.ID)
				&& null != productListService.getProductList(Integer.parseInt(json.get(ServiceConstants.ID)))) {
			ProductList productList = productListService
					.getProductList(Integer.parseInt(json.get(ServiceConstants.ID)));

			if (null != json.get(ServiceConstants.ID)) {
				int id = Integer.parseInt(json.get(ServiceConstants.ID).toString());
				productList.setId(id);
			}
			if (null != json.get(ServiceConstants.SHOP_ID)) {
				String shopId = json.get(ServiceConstants.SHOP_ID).toString();
				productList.setShopId(shopId);
			}
			if (null != json.get(ServiceConstants.USER_ID)) {
				String userId = json.get(ServiceConstants.USER_ID).toString();
				productList.setUserId(userId);
			}
			if (null != json.get(ServiceConstants.PRODUCT_ID)) {
				String productId = json.get(ServiceConstants.PRODUCT_ID).toString();
				productList.setProductId(productId);
			}
			if (null != json.get(ServiceConstants.PRODUCT_NAME)) {
				String productName = json.get(ServiceConstants.PRODUCT_NAME).toString();
				productList.setProductName(productName);
			}
			if (null != json.get(ServiceConstants.CART_ID)) {
				int cartId = Integer.parseInt(json.get(ServiceConstants.CART_ID).toString());
				productList.setCartId(cartId);
			}
			if (null != json.get(ServiceConstants.CREATED_ON)) {
				Date createdOn = new Date();
				productList.setCreatedOn(createdOn);
			}
			if (null != json.get(ServiceConstants.PRODUCT_QUANTITY)) {
				float productQuantity = Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY).toString());
				productList.setProductQuantity(productQuantity);
			}
			if (null != json.get(ServiceConstants.PRICE)) {
				Float price = Float.parseFloat(json.get(ServiceConstants.PRICE).toString());
				productList.setPrice(price);
			}
			
			if (null != json.get(ServiceConstants.STOCK)) {
				int stock = Integer.parseInt(json.get(ServiceConstants.STOCK).toString());
				productList.setStock(stock);
			}
			
			if (null != json.get(ServiceConstants.GST_PERCENT)) {
				int gstPercent = Integer.parseInt(json.get(ServiceConstants.GST_PERCENT).toString());
				productList.setGstPrecent(gstPercent);
			}
			if (null != json.get(ServiceConstants.IS_ACTIVE)) {
				boolean isActive = Boolean.parseBoolean(json.get(ServiceConstants.IS_ACTIVE).toString());
				productList.setActive(isActive);
			}
			if (null != json.get(ServiceConstants.IS_DELETED)) {
				boolean isDeleted = Boolean.parseBoolean(json.get(ServiceConstants.IS_DELETED).toString());
				productList.setDeleted(isDeleted);
			}
			if (null != json.get(ServiceConstants.OFFERS_AVAILABLE)) {
				boolean offersAvailable = Boolean.parseBoolean(json.get(ServiceConstants.OFFERS_AVAILABLE));
				productList.setOffersAvailable(offersAvailable);

			}
			if (null != json.get(ServiceConstants.OLD_PRICE)) {
				float oldPrice = Float.parseFloat(json.get(ServiceConstants.OLD_PRICE).toString());
				productList.setOldPrice(oldPrice);
			}
			if (null != json.get(ServiceConstants.DISCOUNT)) {
				float discount = Float.parseFloat(json.get(ServiceConstants.DISCOUNT).toString());
				productList.setDiscount(discount);
			}
			if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
				Float deliveryCharge = Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE).toString());
				productList.setDeliveryCharge(deliveryCharge);
			}
			if (null != json.get(ServiceConstants.OFFER)) {
				int offer = Integer.parseInt(json.get(ServiceConstants.OFFER).toString());
				productList.setOffer(offer);
			}
			if (null != json.get(ServiceConstants.OFFER_TO)) {
				Date offerTo = new Date(json.get(ServiceConstants.OFFER_TO).toString());
				productList.setOfferTo(offerTo);
			}
			if (null != json.get(ServiceConstants.OFFER_FROM)) {
				Date offerFrom = new Date(json.get(ServiceConstants.OFFER_FROM).toString());
				productList.setOfferFrom(offerFrom);
			}
			if (null != json.get(ServiceConstants.GST_AMOUNT)) {
				float gstAmount = Float.parseFloat(json.get(ServiceConstants.GST_AMOUNT).toString());
				productList.setGstAmount(gstAmount);
			}
			if (null != json.get(ServiceConstants.MEASUREMENT)) {
				String measurement = json.get(ServiceConstants.MEASUREMENT).toString();
				productList.setMeasurement(measurement);
			}

			productListService.updateProductList(productList);
			response.put("status", Boolean.TRUE.toString());
			response.put("Discreptino", "productList updated");
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("Discreption", "Product doesn't exist with given  id");
		}
		return ResponseEntity.ok().body(response);
	}

	@SuppressWarnings({})
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createProductList(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to create user: {}", json.get(ServiceConstants.SHOP_ID));
		Map<String, String> response = new HashMap<String, String>();

		ProductList productList = new ProductList(Integer.parseInt(json.get(ServiceConstants.CART_ID)),
				json.get(ServiceConstants.SHOP_ID));

		productList.setUserId(json.get(ServiceConstants.USER_ID));
		productList.setShopId(json.get(ServiceConstants.SHOP_ID));
		productList.setProductQuantity(Float.parseFloat(json.get(ServiceConstants.PRODUCT_QUANTITY)));
		productList.setProductName(json.get(ServiceConstants.PRODUCT_NAME));
		productList.setProductId(json.get(ServiceConstants.PRODUCT_ID));
		productList.setPrice(Float.parseFloat(json.get(ServiceConstants.PRICE)));
		productList.setOfferTo(new Date());
		productList.setOffersAvailable(Boolean.parseBoolean(json.get(ServiceConstants.OFFERS_AVAILABLE)));
		if (Boolean.parseBoolean(json.get(ServiceConstants.OFFERS_AVAILABLE))) {
			productList.setOldPrice(Float.parseFloat(json.get(ServiceConstants.OLD_PRICE)));
			productList.setOffer(Integer.parseInt(json.get(ServiceConstants.OFFER)));
			productList.setDiscount(Float.parseFloat(json.get(ServiceConstants.DISCOUNT)));
		}

		if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
			productList.setDeliveryCharge(Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE)));
		}

		productList.setOfferFrom(new Date());
		productList.setMeasurement(json.get(ServiceConstants.MEASUREMENT));
		productList.setGstAmount(Float.parseFloat(json.get(ServiceConstants.GST_AMOUNT)));
		productList.setDeleted(Boolean.FALSE);
		productList.setCreatedOn(new Date());
		productList.setCartId(Integer.parseInt(json.get(ServiceConstants.CART_ID)));
		productList.setActive(Boolean.TRUE);
		productList.setGstPrecent(Integer.parseInt(json.get(ServiceConstants.GST_PERCENT)));
		productList.setStock(Integer.parseInt(json.get(ServiceConstants.STOCK)));

		response.put("shopId", json.get(ServiceConstants.SHOP_ID));
		if (productListService.productListExists(json.get(ServiceConstants.PRODUCT_ID))) {
			response.put("FALSE", Boolean.FALSE.toString());
			response.put("discreption", "Product already exist with given Shop Id");

		} else {
			boolean result = productListService.createProductList(productList);
			response.put("status", Strings.EMPTY + result);
			response.put("description",
					"ProductList created successfully with given Shop Id , go through your inbox to activate");
		}
		return ResponseEntity.ok().body(response);
	}

}
