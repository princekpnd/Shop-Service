package com.shop.shopservice.service;

import java.util.List;

import com.shop.shopservice.entity.Transaction;

public interface ITransactionService {

	List<Transaction> getAllTransaction();

	List<Transaction> getTransactionByUserId(int userId);

	List<Transaction> getTransactionByCartId(int cartId);

	List<Transaction> getTransactionForUserByShopId(String shopId);

	List<Transaction> getTransactionByShopId(String shopId);

	List<Transaction> getTransactionByTransactionId(int transactionId);

	public boolean transactionExists(String shopId);

	public boolean createTransaction(Transaction transaction);

	public Transaction getTransaction(int transactionId);

	public boolean updateTransaction(Transaction transaction);
	
	List<Transaction> getAllActiveTransaction(int userId, int transactionType, boolean isActive);

}
