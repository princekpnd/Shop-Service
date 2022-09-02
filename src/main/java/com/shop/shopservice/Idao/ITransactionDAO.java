package com.shop.shopservice.Idao;

import java.util.List;
import com.shop.shopservice.entity.Transaction;

public interface ITransactionDAO {

	List<Transaction> getAllTransaction();

	List<Transaction> getTransactionByUserId(int userId);

	List<Transaction> getTransactionByShopId(String shopId);

	List<Transaction> getTransactionByTransactionId(int transactionId);

	List<Transaction> getTransactionForUserByShopId(String shopId);

	List<Transaction> getTransactionByCartId(int cartId);

	boolean transactionExists(String shopId);

	void addTransaction(Transaction transaction);

	Transaction getByTransactionId(int transactionId);

	void updateTransaction(Transaction transaction);
	
	public List<Transaction> getAllActiveTransaction(int userId,int transactionType, boolean isActive);

}
