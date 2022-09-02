 package com.shop.shopservice.controller;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shop.shopservice.Idao.ILookUp;
import com.shop.shopservice.Idao.IManagedObject;
import com.shop.shopservice.Idao.IProductDAO;
import com.shop.shopservice.constants.ServiceConstants;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.Image;
import com.shop.shopservice.entity.Product;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.service.ArticleFileService;
import com.shop.shopservice.service.IAdminService;
import com.shop.shopservice.service.IImageService;
import com.shop.shopservice.service.IProductService;
import com.shop.shopservice.service.IUserService;

@RestController
@RequestMapping("/api/product")

public class ProductController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IProductService productService;
	
	@Autowired
	ArticleFileService fileService;
	
	@Autowired
	IManagedObject ManagedObjectService;
	
	@Autowired
	private IImageService imageService;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ILookUp lookup;
	
	@Autowired
	private IProductDAO productDao;

	@Autowired
	private IAdminService adminService;

	@GetMapping("getallproduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> productList = productService.getAllProduct();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("getallproductforuser")
	public ResponseEntity<List<Product>> getAllProductForUser() {
		List<Product> productList = productService.getAllProductForUser();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("get/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId) {
		Product product = productService.getProduct(productId.intValue());
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("getproductforofferbyproductid/{productId}")
	public ResponseEntity<Product> getProductForOfferByProductId(@PathVariable("productId") Integer productId) {
		// Product product = productService.getProductForOfferByProductId(productId
		// .intValue());
		Product product = productService.getProductForOfferByProductId(productId.intValue());
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@GetMapping("getproductbycartid/{cartId}")
	public ResponseEntity<List<Product>> getProductByCart(@PathVariable("cartId") int cartId) {
		List<Product> productList = productService.getProductByCartId(cartId);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("getproductbyname/{name}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
		List<Product> productList = productService.getProductByName(name);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("getproductbycategory/{category}")
	public ResponseEntity<Product> getProductByCategory(@PathVariable("category") Integer category) {
		Product product = productService.getProductByCategory(category.intValue());
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("getproductbyshopidandcategory/{shopId}/{category}")
	public ResponseEntity<List<Product>> getProductByShopIdForCategory(@PathVariable("shopId") String shopId,
			@PathVariable("category") Integer category) {
		List<Product> productList = productService.getProductByShopIdForCategory(shopId, category.intValue());
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("getproduct/shopid/brand/{shopId}/{brand}")
	public ResponseEntity<List<Product>> getProductByShopIdAndBrand(@PathVariable("shopId") String shopId, 
			@PathVariable("brand") int brand){
		List<Product> productList = productService.getProductByShopIdAndBrand(shopId, brand);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("getproductbybrand/{brand}")
	public ResponseEntity<List<Product>> getProductByBrand(@PathVariable("brand") int brand) {
		List<Product> productList = productService.getProductByBrand(brand);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("get/offerproduct/byshopid/{shopId}")
	public ResponseEntity<List<Product>> getProductOfferByShopId(@PathVariable("shopId") String shopId){
		List<Product> productList = productService.getProductOffreByShopId(shopId);
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}

	@GetMapping("getproductbyshopid/{shopId}")
	public ResponseEntity<List<Product>> getProductByShopId(@PathVariable("shopId") String shopId) {
		Admin admin = adminService.getAdminByShopId(shopId);
		List<Product> productList = null;
		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
			productList = productService.getProductByShopId(shopId);
		}
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("getproductforuserbyshopId/{shopId}")
	public ResponseEntity<List<Product>> getProductForUserByShopId(@PathVariable("shopId") String shopId) {
		Admin admin = adminService.getAdminByShopId(shopId);
		List<Product> productList = null;
		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
			productList = productService.getProductForUserByShopId(shopId);
		}
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("getproductbyproductidandshopid/{productId}/{shopId}")
	public ResponseEntity<List<Product>> getProductByProductIdAndShopId(@PathVariable("productId") int productId ,@PathVariable("shopId") String shopId){
		List<Product> productList = productService.getProductByProductIdAndShopId(productId, shopId);
		List<Image> imageList = imageService.getImageByShopIdAndProductId(shopId, productId);
		productList.get(0).setImage(imageList);
		return new ResponseEntity<List<Product>>(productList , HttpStatus.OK);
	}
	
	@GetMapping("getcurrentproduct/{shopId}/{name}/{category}/{brand}")
	public ResponseEntity<List<Product>> getCurrentProduct(@PathVariable("shopId") String shopId, @PathVariable("name") String name, @PathVariable("category") int category, @PathVariable("brand") int brand){
		List<Product> productList = productService.getCurrentProduct(shopId, name, category, brand);
		
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}


	@GetMapping("getproduct/wishlist/{shopId}/{userId}")
	public ResponseEntity<ArrayList<Product>> getProductForUserByShopId(@PathVariable("shopId") String shopId, @PathVariable("userId") int userId) {
		User user = userService.getUser(userId);
		
		String wishList = user.getWishList();
		String[] wishLists = wishList.split(",");
		
		Admin admin = adminService.getAdminByShopId(shopId);
		ArrayList<Product> productList = new ArrayList<>();
		Product product = null;
		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
			for(int i = 0; i < wishLists.length; i++) {
				int productId = Integer.parseInt(wishLists[i]);
				product = productService.getProduct(productId);
				productList.add(product);
			}		
		}
		return new ResponseEntity<ArrayList<Product>>(productList, HttpStatus.OK);
	}
	
//	@GetMapping("getwishlist/{userId}")
//	public ResponseEntity<List<Product>> getProductWishList(@PathVariable("userId") int userId){
//        User user = userService.getUser(userId);
//		
//		String wishList = user.getWishList();
//		String[] wishLists = wishList.split(",");
//		Admin admin = adminService.getAdminByShopId(shopId);
//		ArrayList<Product> productList = new ArrayList<>();
//		Product product = null;
//		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
//			for(int i = 0; i < wishLists.length; i++) {
//				int productId = Integer.parseInt(wishLists[i]);
//				product = productService.getProduct(productId);
//				productList.add(product);
//			}		
//		}
//	//	List<Product> productList = productService.getProductForWishList(userId);
//		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
//		
//		
//	}
	
	@GetMapping("getproductbybrandname/{shopId}/{brandName}")
	public ResponseEntity<List<Product>> getProductByBrand(@PathVariable("shopId") String shopId,
			@PathVariable("brandName") String brand) {
		int lookUpId = lookup.findLookUpIdByName(shopId, brand).getLookUpId();
		List<Product> productList = productService.getProductByBrandName(lookUpId);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("search/index")
	public ResponseEntity<String> indexAll() {
		productDao.indexProduct();
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@GetMapping("search/{keyword}")
	public ResponseEntity<List<Product>> searchAllProductBykeyword(@PathVariable("keyword") String keyword) {
		List<Product> result = new ArrayList<Product>();
		result = productDao.searchProduct(keyword);
		return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
	}

//	@PostMapping(value = "/upload/avatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	// If not @RestController, uncomment this
//	// @ResponseBody
//	public ResponseEntity<?> uploadavatarFile(@RequestParam("file") MultipartFile file,
//			@PathVariable("userId") String userId) {
//
//		// int moid =
//		// userService.getUser(Integer.parseInt(userId)).getManagementObject();
//		// ManagedObject mo = (ManagedObject)
//		// ManagedObjectService.getManagedObject(moid);
////		logger.debug("Single file upload with userId! : " + userId);
//		System.out.println("Single SYS file upload with userId! : " + userId);
//		if (file.isEmpty()) {
//			return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
//		}
//
//		try {
//
//			fileService.saveUploadedFiles(Arrays.asList(file), userId, "avatar");
//
//		} catch (IOException e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//
//		return new ResponseEntity<Object>("Successfully uploaded - " + file.getOriginalFilename(), new HttpHeaders(),
//				HttpStatus.OK);
//
//	}
	
	@PutMapping("/update")
	ResponseEntity<Map<String, String>> UpdateCart(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.NAME));
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.ID)
				&& null != productService.getProduct(Integer.parseInt(json.get(ServiceConstants.ID)))) {
			Product product = productService.getProduct(Integer.parseInt(json.get(ServiceConstants.ID)));

			if (null != json.get(ServiceConstants.ID)) {
				int productId = Integer.parseInt(json.get(ServiceConstants.ID).toString());
				product.setProductId(productId);
			}

			if (null != json.get(ServiceConstants.NAME)) {
				String name = json.get(ServiceConstants.NAME).toString();
				product.setName(name);
			}

			if (null != json.get(ServiceConstants.CATEGORY)) {
				int category = Integer.parseInt(json.get(ServiceConstants.CATEGORY).toString());
				product.setCategory(category);
			}

			if (null != json.get(ServiceConstants.BRAND)) {
				int brand = Integer.parseInt(json.get(ServiceConstants.BRAND).toString());
				product.setBrand(brand);
			}

			if (null != json.get(ServiceConstants.SHOP_ID)) {
				String shopId = json.get(ServiceConstants.SHOP_ID).toString();
				product.setShopId(shopId);
			}

			if (null != json.get(ServiceConstants.AVATAR)) {
				String avatar = json.get(ServiceConstants.AVATAR).toString();
				System.out.println(avatar);
				product.setAvatar(avatar);
			}

			if (null != json.get(ServiceConstants.PRICE)) {
				float price = Float.parseFloat(json.get(ServiceConstants.PRICE).toString());
				product.setPrice(price);
			}

			if (null != json.get(ServiceConstants.QUANTITY)) {
				int quantity = Integer.parseInt(json.get(ServiceConstants.QUANTITY).toString());
				product.setQuantity(quantity);
			}

			if (null != json.get(ServiceConstants.DESCRIPTION)) {
				String description = json.get(ServiceConstants.DESCRIPTION).toString();
				product.setDescription(description);
			}

			if (null != json.get(ServiceConstants.IS_ACTIVE)) {
				boolean isActive = Boolean.parseBoolean((json.get(ServiceConstants.IS_ACTIVE).toString()));
				product.setActive(isActive);
			}

			if (null != json.get(ServiceConstants.IS_DELETED)) {
				boolean isDeleted = Boolean.parseBoolean(json.get(ServiceConstants.IS_DELETED).toString());
				product.setDeleted(isDeleted);
			}

			if (null != json.get(ServiceConstants.BARCODE)) {
				String barcode = json.get(ServiceConstants.BARCODE).toString();
				product.setBarcode(barcode);

			}
			if (null != json.get(ServiceConstants.STOCK)) {
				int stock = Integer.parseInt(json.get(ServiceConstants.STOCK).toString());
				product.setStock(stock);

			}
			if (null != json.get(ServiceConstants.COST_PRICE)) {
				float costPrice = (Float.parseFloat(json.get(ServiceConstants.COST_PRICE).toString()));
				product.setCostPrice(costPrice);
			}
			if (null != json.get(ServiceConstants.SELLING_PRICE)) {
				float sellingPrice = Float.parseFloat(json.get(ServiceConstants.SELLING_PRICE).toString());
				product.setSellingPrice(sellingPrice);
			}
			if (null != json.get(ServiceConstants.OFFER_PERCENT)) {
				int offerPercent = Integer.parseInt(json.get(ServiceConstants.OFFER_PERCENT).toString());
				product.setOfferPercent(offerPercent);
			}
			if (null != json.get(ServiceConstants.OLD_PRICE)) {
				float oldPrice = Float.parseFloat(json.get(ServiceConstants.OLD_PRICE).toString());
				product.setOldPrice(oldPrice);
			}
			if (null != json.get(ServiceConstants.OFFER_FROM)) {
				Date offerFrom = new Date();
				product.setOfferFrom(offerFrom);
			}
			if (null != json.get(ServiceConstants.OFFER_TO)) {
				Date offerTo = new Date();
				System.out.println(offerTo);
				product.setOfferTo(offerTo);
			}
			if (null != json.get(ServiceConstants.OFFER_ACTIVE_IND)) {
				boolean offerActiveInd = Boolean.parseBoolean(json.get(ServiceConstants.OFFER_ACTIVE_IND).toString());
				product.setOfferActiveInd(offerActiveInd);
			}
			if (null != json.get(ServiceConstants.CREATED_ON)) {
				Date createdOn = new Date();
				System.out.println(createdOn);
				product.setCreatedOn(createdOn);

			}
			if (null != json.get(ServiceConstants.GST_AMOUNT)) {
				float gstAmount = Float.parseFloat(json.get(ServiceConstants.GST_AMOUNT).toString());
				System.out.println(gstAmount);
				product.setGstAmount(gstAmount);

			}
			if (null != json.get(ServiceConstants.GST_PERCENT)) {
				float gstPercent = Float.parseFloat(json.get(ServiceConstants.GST_PERCENT).toString());
				System.out.println(gstPercent);
				product.setGstPercent(gstPercent);
			}
			if (null != json.get(ServiceConstants.DELIVERY_CHARGE)) {
				float deliveryCharge = Float.parseFloat(json.get(ServiceConstants.DELIVERY_CHARGE).toString());
				System.out.println(deliveryCharge);
				product.setDeliveryCharge(deliveryCharge);
			}
			if (null != json.get(ServiceConstants.MEASUREMENT)) {
				String measurement = json.get(ServiceConstants.MEASUREMENT).toString();
				System.out.println(measurement);
				product.setMeasurement(measurement);
			}
			if(null != json.get(ServiceConstants.OUT_OF_STOCK)) {
				int outOfStock = Integer.parseInt(json.get(ServiceConstants.OUT_OF_STOCK));
				product.setOutOfStock(outOfStock);
			}
			if(null != json.get(ServiceConstants.HSN_CODE)) {
				String hsnCode = json.get(ServiceConstants.HSN_CODE);
				product.setHsnCode(hsnCode);
			}
			if(null != json.get(ServiceConstants.DATE_OF_EXPIRE)) {
				Date dateOfExpire = new Date();
				product.setDateOfExpire(dateOfExpire);
			}
			if(null != json.get(ServiceConstants.DATE_OF_MANUFACTURING)) {
				Date dateOfManufactring = new Date();
				product.setDateOfManufacturing(dateOfManufactring);
			}
			if(null != json.get(ServiceConstants.BATCH_NUMBER)) {
				String batchNumber = json.get(ServiceConstants.BATCH_NUMBER);
				product.setBatchNumber(batchNumber);
			}
			if(null != json.get(ServiceConstants.LOT_NUMBER)) {
				String lotNumber = json.get(ServiceConstants.LOT_NUMBER);
				product.setLotNumber(lotNumber);
			}
			

//			if(null != json.get(ServiceConstants.CART_ID)) {
//		        int cartId =Integer.parseInt( json.get(ServiceConstants.CART_ID).toString());
//				System.out.println(cartId);
//				product.setCartId(cartId);
//			}

			productService.updateProduct(product);
			response.put("status", Boolean.TRUE.toString());
			response.put("description", "Product updated");
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Product doesn't exist with given  userid");
		}
		return ResponseEntity.ok().body(response);
	}

	@SuppressWarnings({})
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createProduct(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to create user: {}", json.get(ServiceConstants.SHOP_ID));
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.BRAND) && null != json.get(ServiceConstants.SHOP_ID)) {
			Product product = new Product(Integer.parseInt(json.get(ServiceConstants.BRAND)),
					json.get(ServiceConstants.SHOP_ID));
			float sellingPrice = Float.parseFloat(json.get(ServiceConstants.SELLING_PRICE)),
					gstPercent = Float.parseFloat(json.get(ServiceConstants.GST_PERCENT)), gstAmount = 0;

			String shopId = json.get(ServiceConstants.SHOP_ID), name = json.get(ServiceConstants.NAME);

			int category = Integer.parseInt(json.get(ServiceConstants.CATEGORY)),
					brand = Integer.parseInt(json.get(ServiceConstants.BRAND));

			if (Boolean.parseBoolean(json.get(ServiceConstants.OFFER_ACTIVE_IND))) {
				float offerPercent = Float.parseFloat(json.get(ServiceConstants.OFFER_PERCENT)), price = 0,
						discount = 0, oldPrice = 0;

				discount = (sellingPrice * offerPercent) / 100;

				price = sellingPrice - discount;

				oldPrice = sellingPrice;

				price = sellingPrice - discount;

				gstAmount = (price * gstPercent) / 100;

				product.setPrice(price);
				product.setOldPrice(oldPrice);
				product.setDiscount(discount);
				product.setOfferPercent(Integer.parseInt(json.get(ServiceConstants.OFFER_PERCENT)));
				product.setOfferFrom(new Date());
				product.setOfferTo(new Date());

			} else {
				product.setPrice(sellingPrice);
				gstAmount = (sellingPrice * gstPercent) / 100;
			}

			if (null != json.get(ServiceConstants.DESCRIPTION)) {
				product.setDescription(json.get(ServiceConstants.DESCRIPTION));
			}
			
			if(null !=json.get(ServiceConstants.DATE_OF_EXPIRE)) {
			product.setDateOfExpire(new Date());
			}
//			
			if(null !=json.get(ServiceConstants.DATE_OF_MANUFACTURING)) {
				product.setDateOfManufacturing(new Date());
			}
			if(null != json.get(ServiceConstants.HSN_CODE)) {
				String hsnCode = json.get(ServiceConstants.HSN_CODE);
				product.setHsnCode(hsnCode);
			}
			if(null != json.get(ServiceConstants.BATCH_NUMBER)) {
				String batchNumber = json.get(ServiceConstants.BATCH_NUMBER);
				product.setBatchNumber(batchNumber);
			}
			if(null != json.get(ServiceConstants.LOT_NUMBER)) {
				String lotNumber = json.get(ServiceConstants.LOT_NUMBER);
				product.setLotNumber(lotNumber);
			}

			if (null != json.get(ServiceConstants.STOCK)) {
				product.setStock(Integer.parseInt(json.get(ServiceConstants.STOCK)));
			}

			if (null != json.get(ServiceConstants.BARCODE)) {
				product.setBarcode(json.get(ServiceConstants.BARCODE));
			}

			product.setGstAmount(gstAmount);
			product.setName(json.get(ServiceConstants.NAME));
			product.setCategory(Integer.parseInt(json.get(ServiceConstants.CATEGORY)));
			product.setBrand(Integer.parseInt(json.get(ServiceConstants.BRAND)));
			product.setShopId(json.get(ServiceConstants.SHOP_ID));
			product.setQuantity(Integer.parseInt((json.get(ServiceConstants.QUANTITY))));
			product.setSellingPrice(Float.parseFloat(json.get(ServiceConstants.SELLING_PRICE)));
			product.setCostPrice(Float.parseFloat(json.get(ServiceConstants.COST_PRICE)));
			product.setOfferActiveInd(Boolean.parseBoolean(json.get(ServiceConstants.OFFER_ACTIVE_IND)));
			product.setCreatedOn(new Date());
			product.setGstPercent(Float.parseFloat(json.get(ServiceConstants.GST_PERCENT)));
			product.setMeasurement(json.get(ServiceConstants.MEASUREMENT));
			product.setOutOfStock(Integer.parseInt(json.get(ServiceConstants.OUT_OF_STOCK)));
			product.setActive(Boolean.TRUE);
			product.setDeleted(Boolean.FALSE);
			
			
			response.put("shopId", json.get(ServiceConstants.SHOP_ID));
			if (productService.productExists(shopId, name, category, brand)) {
				response.put("status", Boolean.FALSE.toString());
				response.put("description", "Product already exist with given Shop Id");
			} else {
				boolean result = productService.createProduct(product);
				if(result && productService.productExists(shopId, name, category, brand)) {
					List<Product> productList = productService.getCurrentProduct(shopId, name, category, brand);
					 int productId= productList.get(0).getProductId();
					response.put("productId", Strings.EMPTY + productId);
					response.put("status", Strings.EMPTY + result);
					response.put("description",
							"Product created successfully with given Shop Id");
					
				}
				
			}
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.ok().body(response);
		}
	}
	
	@DeleteMapping("delete/{productId}")
	ResponseEntity<Map<String, String>> deleteProduct(@PathVariable("productId") int productId) {
		Map<String, String> response = new HashMap<String, String>();
		Product product = productService.getProduct(productId);
		if (null != product) {
			boolean result = productService.deleteProduct(productId);
			if (result) {
				response.put("status", Boolean.TRUE.toString());
				response.put("description", "Product deleted with given product Id :-" + productId);
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Product does not exist with given product Id");
		}
		return ResponseEntity.ok().body(response);
	}

}
