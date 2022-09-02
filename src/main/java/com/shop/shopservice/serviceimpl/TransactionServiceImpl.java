package com.shop.shopservice.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.ITransactionDAO;
import com.shop.shopservice.entity.Transaction;
import com.shop.shopservice.service.ITransactionService;

@Repository
@Transactional
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	ITransactionDAO transactionDao;
	
	
	@Override
	public List<Transaction> getAllTransaction() {
		return transactionDao.getAllTransaction();
	}
	

	@Override
	public List<Transaction> getTransactionByUserId(int userId) {
		return transactionDao.getTransactionByUserId(userId);
	}
	
	@Override
	public List<Transaction> getTransactionByShopId(String shopId) {
		return transactionDao.getTransactionByShopId(shopId);
	}
	@Override
	public List<Transaction> getTransactionByTransactionId(int transactionId) {
		return transactionDao.getTransactionByTransactionId(transactionId);
	}
	
	@Override
	public List<Transaction> getTransactionForUserByShopId(String shopId) {
		return transactionDao.getTransactionForUserByShopId(shopId);
	}
	
	@Override
	public boolean transactionExists(String shopId) {
		return transactionDao.transactionExists(shopId);
	}
	
	public boolean createTransaction(Transaction transaction) {

		if (transactionExists(transaction.getShopId())) {
			return false;
		} else {
			transactionDao.addTransaction(transaction);			
			return true;
		}
	}


	@Override
	public Transaction getTransaction(int transactionId) {
		return  transactionDao.getByTransactionId(transactionId);
	}


	@Override
	public boolean updateTransaction(Transaction transaction) {
		 transactionDao.updateTransaction(transaction);
		 return true;
	}


	@Override
	public List<Transaction> getTransactionByCartId(int cartId) {
		return transactionDao.getTransactionByCartId(cartId);
	}


	@Override
	public List<Transaction> getAllActiveTransaction(int userId, int transactionType, boolean isActive) {
	return transactionDao.getAllActiveTransaction(userId, transactionType, isActive);
	}
	

}
