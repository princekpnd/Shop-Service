package com.shop.shopservice.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.shopservice.Idao.IOfflineDAO;
import com.shop.shopservice.service.IOfflineService;
import com.shop.shopservice.service.IProductListService;

@Transactional
@Repository
public class OfflineServiceImpl implements IOfflineService{
	@Autowired
	IOfflineDAO offlineDao;

}
