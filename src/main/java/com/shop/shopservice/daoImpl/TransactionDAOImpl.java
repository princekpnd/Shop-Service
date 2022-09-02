package com.shop.shopservice.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.ITransactionDAO;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.Product;
import com.shop.shopservice.entity.Transaction;

@Repository
@Transactional
public class TransactionDAOImpl implements ITransactionDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	
	@Override
	public List<Transaction> getAllTransaction() {
		List<Transaction> profileList= entityManager.createNamedQuery("Transaction.findAll",Transaction.class).getResultList();
		return profileList;
	}
	
	@Override
	public List<Transaction> getTransactionByUserId(int userId) {
		return this.entityManager.createNamedQuery("Transaction.findTransactionByUserId", Transaction.class).setParameter("userId", userId).getResultList();
	}
	
	@Override
	public List<Transaction> getTransactionForUserByShopId(String shopId) {
		List<Transaction> transactionList = entityManager.createNamedQuery("Transaction.findTransactionForUserByShopId",Transaction.class).setParameter("shopId", shopId).getResultList();
		return transactionList;
	}
	
	@Override
	public List<Transaction> getTransactionByShopId(String shopId) {
		return this.entityManager.createNamedQuery("Transaction.findTransactionByShopId", Transaction.class).setParameter("shopId", shopId).getResultList();
	}
	@Override
	public List<Transaction> getTransactionByTransactionId(int transactionId) {
		return this.entityManager.createNamedQuery("Transaction.findTransactionByTransactionId", Transaction.class).setParameter("transactionId", transactionId).getResultList();
	}
	
	@Override
	public boolean transactionExists(String shopId) {
		Transaction transaction = entityManager.createNamedQuery("Transaction.findByShopId",Transaction.class).setParameter("shopId", shopId).getResultList().stream().findFirst().orElse(null);;
		return null != transaction ?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public void addTransaction(Transaction transaction) {
		entityManager.persist(transaction);
	}
	
	@Override
	public Transaction getByTransactionId(int transactionId) {
		return this.entityManager.find(Transaction.class, transactionId);
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		entityManager.merge(transaction);
		
	}

	@Override
	public List<Transaction> getTransactionByCartId(int cartId) {
		List<Transaction> transactionList = entityManager.createNamedQuery("Transaction,findByCartId",Transaction.class).setParameter("cartId", cartId).getResultList();
		return transactionList;
	}

	@Override
	public List<Transaction> getAllActiveTransaction(int userId, int transactionType, boolean isActive) {
		List<Transaction> transactionList = entityManager.createNamedQuery("Transaction, findActiveTransaction",Transaction.class).setParameter("userId", userId).setParameter("transactionType", transactionType).setParameter("isActive", isActive).getResultList();
		return transactionList;
	}

	
	

	
}
