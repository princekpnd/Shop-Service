package com.shop.shopservice.controller;

import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.shopservice.Idao.ILookUp;
import com.shop.shopservice.constants.ServiceConstants;
import com.shop.shopservice.entity.Account;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.AdminBillBook;
import com.shop.shopservice.entity.LookUp;
import com.shop.shopservice.entity.Transaction;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.entity.Withdraw;
import com.shop.shopservice.service.IAccountService;
import com.shop.shopservice.service.IAdminBillBookService;
import com.shop.shopservice.service.IAdminService;
import com.shop.shopservice.service.INotificationService;
import com.shop.shopservice.service.ITransactionService;
import com.shop.shopservice.service.IUserService;
import com.shop.shopservice.service.IWithdrawService;

@RestController
@RequestMapping("/api/withdraw")
public class WithdrawController {
	@Autowired
	private IWithdrawService withdrawService;
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IAdminBillBookService adminBillBookService;
	
	@Autowired
	private ILookUp lookup;
	
	@Autowired
	private INotificationService notificationService;

	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IUserService UserService;
	
	@Autowired
	private ITransactionService transactionService;
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
//	@GetMapping("getall")
//	public ResponseEntity<List<Withdraw>> getAll(){
//		List<Withdraw> withdrawList = withdrawService.getAll();
//		return new ResponseEntity<List<Withdraw>>(withdrawList, HttpStatus.OK);
//	}
//	
	@GetMapping("get/{id}")
	public ResponseEntity<Withdraw> getById(@PathVariable("id") int id){
		Withdraw withdraw = withdrawService.getById(id);
		return new ResponseEntity<Withdraw>(withdraw, HttpStatus.OK);
	}
	
	
	@GetMapping("getall")
	public ResponseEntity<List<Withdraw>> getAllWithdraw() {
		List<Withdraw> withdrawList = withdrawService.getAll();
		return new ResponseEntity<List<Withdraw>>(withdrawList, HttpStatus.OK);
	}
	
	@SuppressWarnings({})
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createWithdraw(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		log.info("Request to create user: {}", json.get(ServiceConstants.ACCOUNT_NUM));
		Map<String, String> response = new HashMap<String, String>();
		if(null != json.get(ServiceConstants.SHOP_ID) 
				&& null != json.get(ServiceConstants.WITHDRAW_BALANCE) && null != json.get(ServiceConstants.ACCOUNT_ID)) {
			String shopId = json.get(ServiceConstants.SHOP_ID);
			Admin admin = adminService.getAdminByShopId(shopId);
			List<User> userList = UserService.getUserByShopId(shopId);
			List<Account> accountList = accountService.getAccountByShopId(shopId);
			int userId = userList.get(0).getUserId();
			int adminId = admin.getAdminId();
			float availableAmount = admin.getAvailableAmount();
			String accountHolderName = accountList.get(0).getAccountHolderName();
			int accountNo = accountList.get(0).getAccountNum();
			String bankName = accountList.get(0).getBankName();
			String mobileNo = accountList.get(0).getMobileNo();
			
	//		int accountId = accountList.get(0).getId();
			AdminBillBook adminBillBook = null;
			int withdrawBalance = Integer.parseInt(json.get(ServiceConstants.WITHDRAW_BALANCE)), 
					accountId = Integer.parseInt(json.get(ServiceConstants.ACCOUNT_ID));
			int minimumBalance = 500, requestBalance = 0;
			
			requestBalance = withdrawBalance -minimumBalance;
			if(availableAmount <= requestBalance) {
				LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "WALLET_PAYMENT");
				LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "COMPLETED");
				LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "WITHDRAW_REQUEST");
				LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "ADMIN");
				LookUp lookUp4 = lookup.findLookUpIdByName("MILAAN", "WITHDRAW_PENDING");
				LookUp lookUp5 = lookup.findLookUpIdByName("MILAAN", "WITHDRAW_NOTIFICATION");
				int withdrawNotification = lookUp5.getLookUpId();
//				User user = UserService.
				int paymentMode = lookUp.getLookUpId();
				int transactionStatus = lookUp1.getLookUpId();
				int walletRequest = lookUp2.getLookUpId();
				int userType = lookUp3.getLookUpId();		
				int withdrawPending = lookUp4.getLookUpId();
				
				String order_rcptid_11 = Strings.EMPTY;
				order_rcptid_11 = UUID.randomUUID().toString().substring(0, 10);
				Withdraw withdraw = new Withdraw();
				
				admin.setWallet(admin.getWallet() - withdrawBalance);
				adminService.updateAdmin(admin);
				List<AdminBillBook> adminBillBookList = adminBillBookService.getBillBookByAdminId(String.valueOf(adminId));
				adminBillBook = adminBillBookList.get(0);
		//		int userId = adminBillBook.getU
				adminBillBook.setDebit(adminBillBook.getDebit() + requestBalance);
				adminBillBook.setLastDebitedOn(new Date());
				adminBillBook.setUpdatedOn(new Date());
				adminBillBookService.updateAdminBillBook(adminBillBook);
				Transaction transaction = new Transaction();
				transaction.setActive(Boolean.TRUE);
				transaction.setAdminId(adminId);
				transaction.setAmount(withdrawBalance);
				transaction.setCreatedOn(new Date());
				transaction.setDeleted(Boolean.FALSE);
				transaction.setOrderRcptidId(order_rcptid_11);
				transaction.setPaid(withdrawBalance);
				transaction.setPaymentMode(paymentMode);
				transaction.setShopId(shopId);
				transaction.setTotalAmount(withdrawBalance);
		//		transaction.setTransactionId(transactionId);
				transaction.setTransactionStatus(transactionStatus);
				transaction.setTransactionType(walletRequest);
				transaction.setUserType(userType);
					List<Transaction> transactionList = transactionService.getAllActiveTransaction(userId, walletRequest, Boolean.TRUE);
					for(int i = 0; i > transactionList.size(); i++) {
						transactionList.get(i).setActive(Boolean.FALSE);
						transactionService.updateTransaction(transactionList.get(i));
					}
				
				
				boolean transactionDone = transactionService.createTransaction(transaction);
				if(transactionDone) {
					withdraw.setAccountHolderName(accountHolderName);
					withdraw.setAccountId(accountId);
					withdraw.setAccountNum(String.valueOf(accountNo));
					withdraw.setActive(Boolean.TRUE);
					withdraw.setAdminId(adminId);
					withdraw.setBankName(bankName);
					withdraw.setCreatedOn(new Date());
					withdraw.setDeleted(Boolean.FALSE);
					withdraw.setMobileNo(mobileNo);
					withdraw.setShopId(shopId);
					withdraw.setUserId(userId);
					withdraw.setWithdrawBalance(withdrawBalance);
					withdraw.setWithdrawStatus(withdrawPending);
					withdraw.setRequestTransactionId(accountId);
			
				boolean creareWithdraw = withdrawService.createWithdraw(withdraw);
			if(creareWithdraw) {
				transaction.setWithdrawId(withdraw.getId());
				transactionService.updateTransaction(transaction);
				notificationService.createNotification(withdraw.getId(), withdrawNotification);
				
				response.put("status", Strings.EMPTY + creareWithdraw);
				response.put("description",
						"Withdraw created successfully with given account number");
			}
			response.put("accountNum", String.valueOf(accountList.get(0).getAccountNum()));
				}else {
					admin= adminService.getAdmin(adminId);
					admin.setWallet(admin.getWallet() + withdrawBalance);
					admin.setAvailableAmount(admin.getAvailableAmount() + withdrawBalance);
					adminService.updateAdmin(admin);
					response.put("status",Boolean.FALSE.toString());
					response.put("descreption", "Your payment is faild");
				}
			}else {
				response.put("status", Boolean.FALSE.toString());
				response.put("descreption", "Your wallet balance is not sufficient");
			}
			
			
		}
		return ResponseEntity.ok().body(response);
	}
}
