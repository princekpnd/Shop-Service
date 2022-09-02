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
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.AdminDeviceID;
import com.shop.shopservice.entity.ShopImage;
import com.shop.shopservice.entity.UserAddress;
import com.shop.shopservice.service.IAdminService;
import com.shop.shopservice.service.IShopImageService;
import com.shop.shopservice.service.IUserAddressService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IShopImageService shopImageService;
	
	@Autowired
	IUserAddressService  userAddressService;

	@GetMapping("getalladmin")
	public ResponseEntity<List<Admin>> getAlladmin() {
		List<Admin> adminList = adminService.getAllAdmin();
		return new ResponseEntity<List<Admin>>(adminList, HttpStatus.OK);
	}

	@GetMapping("get/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") int adminId) {
		Admin admin = adminService.getAdmin(adminId);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@GetMapping("getbyemail/{emailId}")
	public ResponseEntity<Admin> getAdminByEmailId(@PathVariable("emailId") String emailId) {
		Admin admin = adminService.getAdminByEmailId(emailId);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@GetMapping("getadminbyshopid/{shopId}")
	public ResponseEntity<Admin> getAdminByShopId(@PathVariable("shopId") String shopId) {
		Admin admin = adminService.getAdminByShopId(shopId);
		List<UserAddress> userAddressList = userAddressService.getAddressByShopId(shopId);
		admin.setUserAddress(userAddressList);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}
	
	@GetMapping("getbymobilenumber/{mobileNo}")
	public ResponseEntity<Admin> getByMobileNumber(@PathVariable("mobileNo") String mobileNo){
		Admin admin = adminService.getByMobileNumber(mobileNo);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@GetMapping("getadminbyname/{firstName}")
	public ResponseEntity<List<Admin>> getAdminByFirstName(@PathVariable("firstName") String firstName) {
		List<Admin> adminList = adminService.getAdminByFirstName(firstName);
		return new ResponseEntity<List<Admin>>(adminList, HttpStatus.OK);
	}
	@GetMapping("getadminbyadminidandshopid/{adminId}/{shopId}")
	public ResponseEntity<List<Admin>> getAdminByAdminIdAndShopId(@PathVariable("adminId") int adminId,
			@PathVariable("shopId") String shopId){
		List<Admin> adminList = adminService.getAdminByAdminIdAndShopId(adminId, shopId);
		List<ShopImage> shopImageList = shopImageService.getShopImageByShopIdAndProductId(shopId, adminId);
		adminList.get(0).setShopImage(shopImageList);
		return new ResponseEntity<List<Admin>>(adminList, HttpStatus.OK);
	}
	
	@GetMapping("getadminbyadharnumber/{adharNumber}")
	public ResponseEntity<List<Admin>> getAdminByAdharNumber(@PathVariable("adharNumber") Integer adharNumber) {
		List<Admin> admin = adminService.getAdminByAdharNumber(adharNumber.intValue());
		return new ResponseEntity<List<Admin>>(admin, HttpStatus.OK);
	}
	@PutMapping("/update")
	ResponseEntity<Map<String, String>> UpdateAdmin(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.NAME));
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.ID) && null != adminService.getAdmin(Integer.parseInt(json.get(ServiceConstants.ID)))) {
			Admin admin = adminService.getAdmin(Integer.parseInt(json.get(ServiceConstants.ID)));
	//		if (null != json.get(ServiceConstants.SHOP_ID) && null != adminService.getAdmin(Integer.parseInt(json.get(ServiceConstants.SHOP_ID)))) {
	//			Admin Admin = adminService.getAdmin(Integer.parseInt(json.get(ServiceConstants.SHOP_ID)));
				
				if(null != json.get(ServiceConstants.ID)) {
					int id =Integer.parseInt( json.get(ServiceConstants.ID).toString());
					admin.setAdminId(id);				
					}
				if(null != json.get(ServiceConstants.SHOP_ID)) {
					String shopId = json.get(ServiceConstants.SHOP_ID).toString();
					admin.setShopId(shopId);				
					}
				if(null != json.get(ServiceConstants.F_NAME)) {
					String firstName = json.get(ServiceConstants.F_NAME).toString();
					admin.setFirstName(firstName);				
					}
				
				
				if(null != json.get(ServiceConstants.L_NAME)) {
					String lastName = json.get(ServiceConstants.L_NAME).toString();
					admin.setLastName(lastName);				
					}
				
				if(null != json.get(ServiceConstants.AVATAR)) {
					String avatar = json.get(ServiceConstants.AVATAR).toString();
					admin.setAvatar(avatar);				
					}
				
				if(null != json.get(ServiceConstants.ADHAR_NUM)) {
					int adharNumber =Integer.parseInt( json.get(ServiceConstants.ADHAR_NUM).toString());
					admin.setAdharNumber(adharNumber);				
					}
				
				
				if(null != json.get(ServiceConstants.PAN_NUM)) {
					String panNumber = json.get(ServiceConstants.PAN_NUM).toString();
					admin.setPanNumber(panNumber);				
					}
				
				
				
				if(null != json.get(ServiceConstants.MOBILE_NUMBER)) {
					String mobileNo = json.get(ServiceConstants.MOBILE_NUMBER).toString();
					admin.setMobileNo(mobileNo);				
					}
				
				if(null != json.get(ServiceConstants.CREATED_ON)) {
					Date createdOn =new Date();
					admin.setCreatedOn(createdOn);				
					}
				
				
				
				if(null != json.get(ServiceConstants.PWD)) {
					String pwd = json.get(ServiceConstants.PWD).toString();
					admin.setPwd(pwd);				
					}
		
				
				if(null != json.get(ServiceConstants.PAYMENT_STATUS)) {
					boolean paymentStatus =Boolean.parseBoolean( json.get(ServiceConstants.PAYMENT_STATUS).toString());
					admin.setPaymentStatus(paymentStatus);				
					}
				
				if(null != json.get(ServiceConstants.SHOP_NAME)) {
					String shopName = json.get(ServiceConstants.SHOP_NAME).toString();
					admin.setShopName(shopName);				
					}
				
				if(null != json.get(ServiceConstants.SHOP_TYPE)) {
					int shopType =Integer.parseInt( json.get(ServiceConstants.SHOP_TYPE).toString());
					admin.setShopType(shopType);				
					}
				
				if(null != json.get(ServiceConstants.EMAIL_ID)) {
					String emailId = json.get(ServiceConstants.EMAIL_ID).toString();
					admin.setEmailId(emailId);				
					}
				
				
				if(null != json.get(ServiceConstants.IS_ACTIVE)) {
					boolean isActive =Boolean.parseBoolean( json.get(ServiceConstants.IS_ACTIVE).toString());
					admin.setActive(isActive);				
					}
				
				if(null != json.get(ServiceConstants.IS_DELETED)) {
				   boolean isDeleted =Boolean.parseBoolean( json.get(ServiceConstants.IS_DELETED).toString());
					admin.setDeleted(isDeleted);				
					}
				
				if(null != json.get(ServiceConstants.OTP)) {
					String otp = json.get(ServiceConstants.OTP).toString();
					admin.setOtp(otp);				
					}
				
				if(null != json.get(ServiceConstants.GST_NUMBER)) {
					String gstNumber = json.get(ServiceConstants.GST_NUMBER).toString();
					admin.setGstNumber(gstNumber);				
					}
				if(null !=json.get(ServiceConstants.VALIDITY)) {
					int validity = Integer.parseInt(json.get(ServiceConstants.VALIDITY).toString());
					admin.setValidity(validity);
				}
				
				if(null != json.get(ServiceConstants.GENDER)) {
					int gender = Integer.parseInt(json.get(ServiceConstants.GENDER).toString());
					admin.setGender(gender);
				}
			
				adminService.updateAdmin(admin);
				response.put("status", Boolean.TRUE.toString());
				response.put("description", "Admin updated");
			}
			else {
				response.put("status", Boolean.FALSE.toString());
				response.put("description",
						"Admin doesn't exist with given  userid");
			}
		
			return ResponseEntity.ok().body(response);				
}
	
	@PostMapping("/login")
	ResponseEntity<?> validateAdmin(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) {
		Admin result = null;
		if(null != json.get(ServiceConstants.EMAIL_ID) && null != json.get(ServiceConstants.PWD)) {
			log.info("Request to validate admin: {}", json.get(ServiceConstants.EMAIL_ID));
			result = adminService.loginAdmin(json.get(ServiceConstants.EMAIL_ID), json.get(ServiceConstants.PWD));
			if (null != json.get(ServiceConstants.DEVICE) && json.get(ServiceConstants.DEVICE).length() > 10) {
				AdminDeviceID dvice = result.getAdminDeviceIDList().stream()
						.filter(ad -> json.get(ServiceConstants.DEVICE).equals(ad.getDeviceId())).findAny()
						.orElse(null);
				if (null == dvice) {
					dvice = new AdminDeviceID();
					dvice.setAdmin(result);
					dvice.setDeviceId(json.get(ServiceConstants.DEVICE));
					result.getAdminDeviceIDList().add(dvice);
				}
			}
		}
		else if(result == null && null != json.get(ServiceConstants.DEVICE) && json.get(ServiceConstants.DEVICE).length() > 10) {
			result = adminService.validateAdminByDeviceId(json.get(ServiceConstants.DEVICE));	
		}
		if (null != result) {
			result.setLastLoginDate(new Date());
			adminService.updateAdmin(result);
			result.setPwd(Strings.EMPTY);
			result.setAdminDeviceIDList(null);
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.noContent().build();
		}

	}
	
		@PostMapping("/logout")
		ResponseEntity<?> logoutAdmin(@Valid @RequestBody Map<String, String> json,
				HttpServletRequest request) {
		Admin result = null;
		AdminDeviceID dvice = null;
		if (null != json.get(ServiceConstants.EMAIL_ID)) {
			log.info("Request to logout user: {}", json.get(ServiceConstants.EMAIL_ID));
			result = adminService.getAdminByEmailId(json.get(ServiceConstants.EMAIL_ID));
			if (null != json.get(ServiceConstants.DEVICE) && json.get(ServiceConstants.DEVICE).length() > 10) {
				dvice = result.getAdminDeviceIDList().stream()
						.filter(ad -> json.get(ServiceConstants.DEVICE).equals(ad.getDeviceId())).findAny()
						.orElse(null);
				if (null != dvice) {
					adminService.deleteAdminDevice(dvice);
				}
			}
		}
		if (null != result && null != dvice) {
			return ResponseEntity.ok().body("Logout successfully");
		} else {
			return ResponseEntity.noContent().build();
		}

	}
		

		@SuppressWarnings({ })
		@PostMapping("/create")
		ResponseEntity<Map<String, String>> createAdmin(@Valid @RequestBody Map<String, String> json,
				HttpServletRequest request) throws URISyntaxException {
			log.info("Request to create user: {}", json.get(ServiceConstants.SHOP_ID));
			Map<String, String> response = new HashMap<String, String>();
			Admin admin = new Admin(json.get(ServiceConstants.MOBILE_NUMBER), json.get(ServiceConstants.PWD));
			
		
//			admin.setShopId(json.get(ServiceConstants.SHOP_ID));
			admin.setFirstName(json.get(ServiceConstants.F_NAME));
			admin.setLastName(json.get(ServiceConstants.L_NAME));
//			admin.setAvatar(json.get(ServiceConstants.AVATAR));
			if(null != json.get(ServiceConstants.ADHAR_NUM)) {
			admin.setAdharNumber(Integer.parseInt((json.get(ServiceConstants.ADHAR_NUM))));
			}
			if(null != json.get(ServiceConstants.PAN_NUM)) {
			admin.setPanNumber(json.get(ServiceConstants.PAN_NUM));
			}
			admin.setMobileNo(json.get(ServiceConstants.MOBILE_NUMBER));
			
//			String createron = json.get(ServiceConstants.CREATED_ON).toString();	
//			LocalDate localdate = LocalDate.parse(createron);
//			ZoneId defaultZoneId = ZoneId.systemDefault();
//			Date date = Date.from(localdate.atStartOfDay(defaultZoneId).toInstant());
//			admin.setCreatedOn(date);
//			
			
			admin. setPwd(json.get(ServiceConstants.PWD));
//			admin. setPaymentStatus(Boolean.parseBoolean((json.get(ServiceConstants. CREDIT))));
			admin.setShopName(json.get(ServiceConstants.SHOP_NAME));
			admin.setShopType(Integer.parseInt(json.get(ServiceConstants.SHOP_TYPE)));
//			admin.setEmailId(json.get(ServiceConstants.EMAIL_ID));
			admin.setActive(Boolean.TRUE);
			admin.setDeleted(Boolean.FALSE);
//			admin.setOtp(json.get(ServiceConstants.OTP));
			admin.setCreatedOn(new Date());
			if(null != json.get(ServiceConstants.GST_NUMBER)) {
			admin.setGstNumber(json.get(ServiceConstants.GST_NUMBER));
			}
			admin.setUserType(Integer.parseInt(json.get(ServiceConstants.USER_TYPE)));
//			admin.setValidity(Integer.parseInt(json.get(ServiceConstants.VALIDITY)));
			admin.setGender(Integer.parseInt(json.get(ServiceConstants.GENDER)));
			
			
			response.put("mobileNo", json.get(ServiceConstants.MOBILE_NUMBER));
			if (adminService.adminExists(admin.getMobileNo())) {
				response.put("status", Boolean.FALSE.toString());
				response.put("description", "Admin already exist with given mobile number");
			} else {
				boolean result = adminService.createAdmin(admin);
				
				if(result && adminService.adminExists(admin.getMobileNo())) {
					String mobileNo = json.get(ServiceConstants.MOBILE_NUMBER);
					Admin admin1 = adminService.getByMobileNumber(mobileNo);
					int adminId = admin1.getAdminId();
					
					response.put("adminId", Strings.EMPTY + adminId);
					response.put("description",
							"Admin created successfully with given account, go through your inbox to activate");
					
				}
				response.put("status", Strings.EMPTY + result);
				response.put("description",
						"Admin created successfully with given account, go through your inbox to activate");
			}
			return ResponseEntity.ok().body(response);
		}
			
			
	}
