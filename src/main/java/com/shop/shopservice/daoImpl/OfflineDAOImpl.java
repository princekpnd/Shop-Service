package com.shop.shopservice.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shopservice.Idao.IOfflineDAO;
import com.shop.shopservice.Idao.IProductDAO;

@Repository
@Transactional
public class OfflineDAOImpl implements IOfflineDAO{
	@PersistenceContext	
	private EntityManager entityManager;
}
