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
import com.shop.shopservice.entity.Account;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.service.IAccountService;
import com.shop.shopservice.service.IAdminService;

@RestController
@RequestMapping("/api/account")

public class AccountController {

	private final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAdminService adminService;

	@GetMapping("getallaccount")
	public ResponseEntity<List<Account>> getAllaccount() {
		List<Account> accountList = accountService.getAllAccount();
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}

	@GetMapping("get/{accountId}")
	public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Integer accountId) {
		Account account = accountService.getAccount(accountId.intValue());
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@GetMapping("getaccountbyuserid/{userId}")
	public ResponseEntity<List<Account>> getAccountByUserId(@PathVariable("userId") Integer userId) {
		List<Account> accountList = accountService.getAccountByUserId(userId);
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}

	@GetMapping("getaccountbybankname/{bankName}")
	public ResponseEntity<Account> getAccountByBankName(@PathVariable("bankName") String bankName) {
		Account account = accountService.getAccountByBankName(bankName);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@GetMapping("get/activeuser/{userId}")
	public ResponseEntity<List<Account>> getByUserId(@PathVariable("userId") int userId) {
		List<Account> accountList = accountService.getByUser(userId);
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}

	@GetMapping("getaccountbymobilenumber/{mobileNum}")
	public ResponseEntity<List<Account>> getAccountByMobileNumber(@PathVariable("mobileNo") String mobileNo) {
		List<Account> account = accountService.getAccountByMobileNumber(mobileNo);
		return new ResponseEntity<List<Account>>(account, HttpStatus.OK);
	}

	@GetMapping("getaccountbyaccountnumber/{accountNum}")
	public ResponseEntity<List<Account>> getAccountByAccountNumber(@PathVariable("accountNum") Integer accountNum) {
		List<Account> account = accountService.getAccountByAccountNumber(accountNum.intValue());
		return new ResponseEntity<List<Account>>(account, HttpStatus.OK);
	}

	@GetMapping("getaccountbyshopid/{shopId}")
	public ResponseEntity<List<Account>> getAccountByShopId(@PathVariable("shopId") String shopId) {
		Admin admin = adminService.getAdminByShopId(shopId);
		List<Account> accountList = null;
		if (admin != null && admin.isActive() == Boolean.TRUE && admin.isDeleted() == Boolean.FALSE) {
			accountList = accountService.getAccountByShopId(shopId);
		}
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}

	@PutMapping("/update")
	ResponseEntity<Map<String, String>> UpdateAccount(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to update user: {}", json.get(ServiceConstants.NAME));
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstants.ID)
				&& null != accountService.getAccount(Integer.parseInt(json.get(ServiceConstants.ID)))) {
			Account account = accountService.getAccount(Integer.parseInt(json.get(ServiceConstants.ID)));
//			if (null != json.get(ServiceConstants.USER_TYPE) && null != accountService.getAccount(Integer.parseInt(json.get(ServiceConstants.USER_TYPE)))) {
//				Account Account = accountService.getAccount(Integer.parseInt(json.get(ServiceConstants.USER_TYPE)));

			if (null != json.get(ServiceConstants.ID)) {
				int id = Integer.parseInt(json.get(ServiceConstants.ID));
//				System.out.println(id);
				account.setId(id);

			}
			if (null != json.get(ServiceConstants.USER_ID)) {
				int userId = Integer.parseInt(json.get(ServiceConstants.USER_ID).toString());
//				System.out.println(userId);
				account.setUserId(userId);

			}
			if (null != json.get(ServiceConstants.ACCOUNT_NUM)) {
				int accountNum = Integer.parseInt(json.get(ServiceConstants.ACCOUNT_NUM));
//				System.out.println(accountNum);
				account.setAccountNum(accountNum);
			}
			if (null != json.get(ServiceConstants.IFSC)) {
				String ifsc = json.get(ServiceConstants.IFSC).toString();
//				System.out.println(ifsc);
				account.setIfsc(ifsc);

			}
			if (null != json.get(ServiceConstants.SHOP_ID)) {
				String shopId = json.get(ServiceConstants.SHOP_ID);
//				System.out.println(shopId);
				account.setShopId(shopId);
			}
			if (null != json.get(ServiceConstants.BANK_NAME)) {
				String bankName = json.get(ServiceConstants.BANK_NAME).toString();
//				System.out.println(bankName);
				account.setBankName(bankName);

			}

			if (null != json.get(ServiceConstants.CARD_NUM)) {
				int cardNum = Integer.parseInt(json.get(ServiceConstants.CARD_NUM).toString());
				System.out.println(cardNum);
				account.setCardNum(cardNum);

			}
			if (null != json.get(ServiceConstants.CARD_TYPE)) {
				int cardType = Integer.parseInt(json.get(ServiceConstants.CARD_TYPE).toString());
				System.out.println(cardType);
				account.setCardType(cardType);

			}

			if (null != json.get(ServiceConstants.CURRENCY)) {
				int currency = Integer.parseInt(json.get(ServiceConstants.CURRENCY).toString());
				System.out.println(currency);
				account.setCurrency(currency);

			}

			if (null != json.get(ServiceConstants.MOBILE_NUMBER)) {
				String mobileNo = json.get(ServiceConstants.MOBILE_NUMBER).toString();
				System.out.println(mobileNo);
				account.setMobileNo(mobileNo);

			}

			if (null != json.get(ServiceConstants.ACCOUNT_HOLDER_NAME)) {
				String accountHolderName = json.get(ServiceConstants.ACCOUNT_HOLDER_NAME).toString();
				account.setAccountHolderName(accountHolderName);

			}

			accountService.updateAccount(account);
			response.put("status", Boolean.TRUE.toString());
			response.put("description", "Account updated");
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Account doesn't exist with given email or userid");
		}

		return ResponseEntity.ok().body(response);

	}

	@SuppressWarnings({})
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createAccount(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to create user: {}", json.get(ServiceConstants.USER_ID));
		Map<String, String> response = new HashMap<String, String>();
		Account account = new Account(Integer.parseInt(json.get(ServiceConstants.USER_ID)),
				json.get(ServiceConstants.ACCOUNT_HOLDER_NAME));

		account.setUserId(Integer.parseInt((json.get(ServiceConstants.USER_ID))));
		account.setUserType(Integer.parseInt((json.get(ServiceConstants.USER_TYPE))));
		account.setAccountNum(Integer.parseInt((json.get(ServiceConstants.ACCOUNT_NUM))));
		account.setIfsc(json.get(ServiceConstants.IFSC));
		account.setBankName(json.get(ServiceConstants.BANK_NAME));
		account.setCreatedOn(new Date());
		account.setActive(Boolean.TRUE);
		account.setDeleted(Boolean.FALSE);
//		account.setCardNum(Integer.parseInt((json.get(ServiceConstants.CARD_NUM))));
//		account.setCardType(Integer.parseInt((json.get(ServiceConstants.CARD_TYPE))));
		account.setShopId(json.get(ServiceConstants.SHOP_ID));
		account.setAccountHolderName(json.get(ServiceConstants.ACCOUNT_HOLDER_NAME));
//		String expDate = json.get(ServiceConstants.EXPIRY_DATE).toString();	
//		LocalDate localdate = LocalDate.parse(expDate);
//		ZoneId defaultZoneId = ZoneId.systemDefault();
//		Date date = Date.from(localdate.atStartOfDay(defaultZoneId).toInstant());
//		account.setExpiryDate(date);

//		account.setCurrency(Integer.parseInt((json.get(ServiceConstants.CURRENCY))));

		account.setMobileNo(json.get(ServiceConstants.MOBILE_NUMBER));

		response.put("userId", json.get(ServiceConstants.USER_ID));
		if (accountService.accountExists(account.getUserId())) {
			response.put("status", Boolean.FALSE.toString());
			response.put("description", "Account already exist with given userId");
		} else {
			boolean result = accountService.createAccount(account);
			response.put("status", Strings.EMPTY + result);
			response.put("description",
					"Account created successfully with given account, go through your inbox to activate");
		}
		return ResponseEntity.ok().body(response);
	}

}
