package com.shop.shopservice.daoImpl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.IOfflineProductDAO;

@Repository
@Transactional
public class OfflineProductDAOImpl implements IOfflineProductDAO{
	@PersistenceContext	
	private EntityManager entityManager;
}
