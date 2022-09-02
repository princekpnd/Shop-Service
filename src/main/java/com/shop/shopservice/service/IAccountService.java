package com.shop.shopservice.service;

import java.util.List;
import com.shop.shopservice.entity.Account;
public interface IAccountService {
	List<Account> getAllAccount();
	public Account getAccount(int id);
	List<Account> getAccountByUserId(int userId);
	public Account getAccountByBankName(String bankName);
	List<Account> getAccountByMobileNumber(String mobileNo);
	List<Account> getAccountByAccountNumber(int accountNum);
	public  List<Account> getAccountByShopId(String shopId);
	List<Account> getByUser(int userId);
	public boolean updateAccount(Account account);
	public boolean accountExists(int userId);
	public boolean createAccount(Account account);
	

}
