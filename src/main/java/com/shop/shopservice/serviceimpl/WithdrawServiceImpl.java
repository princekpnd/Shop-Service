package com.shop.shopservice.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.shopservice.Idao.IWithdrawDAO;
import com.shop.shopservice.Idao.IWorkDAO;
import com.shop.shopservice.entity.Withdraw;
import com.shop.shopservice.service.IWithdrawService;
import com.shop.shopservice.service.IWorkService;

@Transactional
@Repository
public class WithdrawServiceImpl implements IWithdrawService{
	@Autowired
	IWithdrawDAO withdrawDao;

	@Override
	public List<Withdraw> getAll() {
		return withdrawDao.getAll();
	}

	@Override
	public Withdraw getById(int id) {
		return withdrawDao.getById(id);
	}

	@Override
	public boolean createWithdraw(Withdraw withdraw) {
	withdrawDao.createWithdraw(withdraw);
	return true;
	}
}
