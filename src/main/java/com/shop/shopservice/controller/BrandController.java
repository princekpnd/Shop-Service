package com.shop.shopservice.controller;

import java.net.URISyntaxException;
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
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.Brand;
import com.shop.shopservice.service.IAdminService;
import com.shop.shopservice.service.IBrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
	private final Logger log = LoggerFactory.getLogger(BrandController.class);

	@Autowired
	private IBrandService brandService;

	@Autowired
	private IAdminService adminService;

	@GetMapping("getallbrand")
	public ResponseEntity<List<Brand>> getAllbrand() {
		List<Brand> brandList = brandService.getAllBrand();
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
	}

	@GetMapping("getallDeactivebrandbyshopid/{shopId}")
	public ResponseEntity<List<Brand>> getAllDeactiveBrandByShopId(@PathVariable("shopId") int shopId) {
		List<Brand> brandList = brandService.getAllDeactiveBrandByShopId(shopId);
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
	}

	@GetMapping("getbrandbyshopidandid/{shopId}/{id}")
	public ResponseEntity<List<Brand>> getBrandByShopIdAbdId(@PathVariable("shopId") String shopId,
			@PathVariable("id") int id) {
		List<Brand> brandList = brandService.getBrandByShopIdAndId(shopId, id);
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
	}

	@GetMapping("getbrandforuserbyshopid/{shopId}")
	public ResponseEntity<List<Brand>> getBrandForUserByShopId(@PathVariable("shopId") String shopId) {
		Admin admin = adminService.getAdminByShopId(shopId);
		List<Brand> brandList = null;
		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
			brandList = brandService.getBrandForUserByShopId(shopId);
		}
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
	}

	@GetMapping("getbrandbyshopid/{shopId}")
	public ResponseEntity<List<Brand>> getBrandByShopId(@PathVariable("shopId") String shopId) {
		Admin admin = adminService.getAdminByShopId(shopId);
		List<Brand> brandList = null;
		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
			brandList = brandService.getBrandByShopId(shopId);
		}
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
	}

	@SuppressWarnings({})
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createBrand(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to create user: {}", json.get(ServiceConstants.SHOP_ID));
		Map<String, String> response = new HashMap<String, String>();
		Brand brand = new Brand(json.get(ServiceConstants.SHOP_ID),
				(Integer.parseInt(json.get(ServiceConstants.CATEGORY))));
		if (null != json.get(ServiceConstants.AVATAR)) {
			brand.setAvatar(json.get(ServiceConstants.AVATAR));
		}

		brand.setTitle(json.get(ServiceConstants.TITLE));
		brand.setName(json.get(ServiceConstants.NAME));
		brand.setShopId(json.get(ServiceConstants.SHOP_ID));
		brand.setActive(Boolean.TRUE);
		brand.setDeleted(Boolean.FALSE);
		brand.setCategory(Integer.parseInt(json.get(ServiceConstants.CATEGORY)));

		response.put("CATEGORY", json.get(ServiceConstants.NAME));
		if (brandService.brandExists(brand.getName(), brand.getShopId())) {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Brand already exist with given shopId");
		} else {
			boolean result = brandService.createBrand(brand);
			response.put("status", Strings.EMPTY + result);
			response.put("description",
					"Brand created successfully with given shopId, go through your inbox to activate");
		}
		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/update")
	ResponseEntity<Map<String, String>> UpdateAccount(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.NAME));
		Map<String, String> response = new HashMap<String, String>();

		if (null != json.get(ServiceConstants.CATEGORY)
				&& null != brandService.getBrand(Integer.parseInt(json.get(ServiceConstants.CATEGORY)))) {
			Brand brand = brandService.getBrand(Integer.parseInt(json.get(ServiceConstants.CATEGORY)));

			if (null != json.get(ServiceConstants.ID)) {
				int id = Integer.parseInt(json.get(ServiceConstants.ID));
				System.out.println(id);
				brand.setId(id);

			}
			if (null != json.get(ServiceConstants.AVATAR)) {
				String avatar = json.get(ServiceConstants.AVATAR).toString();
				System.out.println(avatar);
				brand.setAvatar(avatar);

			}

			if (null != json.get(ServiceConstants.NAME)) {
				String name = json.get(ServiceConstants.NAME).toString();
				System.out.println(name);
				brand.setName(name);

			}

			if (null != json.get(ServiceConstants.TITLE)) {
				String title = json.get(ServiceConstants.TITLE);
				System.out.println(title);
				brand.setTitle(title);
			}

			if (null != json.get(ServiceConstants.SHOP_ID)) {
				String shopId = json.get(ServiceConstants.SHOP_ID).toString();
				brand.setShopId(shopId);

			}

			if (null != json.get(ServiceConstants.CATEGORY)) {
				int category = (Integer.parseInt(json.get(ServiceConstants.CATEGORY).toString()));
				brand.setCategory(category);

			}

			brandService.updateBrand(brand);
			response.put("status", Boolean.TRUE.toString());
			response.put("description", "Brand updated");
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Brand doesn't exist with given ShopId");
		}

		return ResponseEntity.ok().body(response);
	}
}
