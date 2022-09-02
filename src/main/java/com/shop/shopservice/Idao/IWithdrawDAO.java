package com.shop.shopservice.Idao;

import java.util.List;

import com.shop.shopservice.entity.Withdraw;

public interface IWithdrawDAO {
	
	List<Withdraw> getAll();
	
	public Withdraw getById(int id);
	void createWithdraw(Withdraw withdraw);
}
