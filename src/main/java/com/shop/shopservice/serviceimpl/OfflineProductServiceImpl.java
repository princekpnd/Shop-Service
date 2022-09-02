package com.shop.shopservice.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.shopservice.Idao.IOfflineDAO;
import com.shop.shopservice.Idao.IOfflineProductDAO;
import com.shop.shopservice.service.IOfflineProductService;
import com.shop.shopservice.service.IOfflineService;

@Transactional
@Repository
public class OfflineProductServiceImpl implements IOfflineProductService{
	@Autowired
	IOfflineProductDAO offlineProductDao;
}
