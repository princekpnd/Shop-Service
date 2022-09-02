package com.shop.shopservice.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shopservice.Idao.IWithdrawDAO;
import com.shop.shopservice.Idao.IWorkDAO;
import com.shop.shopservice.entity.Withdraw;
import com.shop.shopservice.entity.Work;

@Repository
@Transactional

public class WithdrawDAOImpl implements IWithdrawDAO{
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public List<Withdraw> getAll() {
		List<Withdraw> withdrawList = entityManager.createNamedQuery("Withdraw.findAll",Withdraw.class).getResultList();
		return withdrawList;
	}

	@Override
	public Withdraw getById(int id) {
		return this.entityManager.find(Withdraw.class, id);
		
	}

	@Override
	public void createWithdraw(Withdraw withdraw) {
		entityManager.persist(withdraw);
		
	}

//	@Override
//	public boolean createWithdraw(Withdraw withdraw) {
//		entityManager.persist(withdraw);
//		
//	}
}
