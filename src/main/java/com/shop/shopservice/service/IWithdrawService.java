package com.shop.shopservice.service;

import java.util.List;

import com.shop.shopservice.entity.Withdraw;

public interface IWithdrawService {
	
	List<Withdraw> getAll();
	
	public Withdraw getById(int id);
	
	public boolean createWithdraw(Withdraw withdraw);

}
