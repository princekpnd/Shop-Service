package com.shop.shopservice.daoImpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.IAddressDAO;
import com.shop.shopservice.entity.Address;

@Repository
@Transactional
public class AddressDAOImpl implements IAddressDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Address> getAllAddress() {
		List<Address> addressList = entityManager.createNamedQuery("Address.findAllAddress", Address.class).getResultList();
		return addressList;
	}

	@Override
	public List<Address> getAddressByShopId(String shopId) {
		List<Address> addressList =entityManager.createNamedQuery("Address.findAddressByShopId",Address.class).setParameter("shopId", shopId).getResultList();
		return addressList;
	}

	@Override
	public boolean addressExists(String userId) {
		Address address = entityManager.createNamedQuery("Adderss.findByUserId",Address.class).setParameter("userId", userId).getResultList().stream().findFirst().orElse(null);;
		return null != address ?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public void addAddress(Address address) {
		entityManager.persist(address);
		
	}

	@Override
	public Address getAddressById(int id) {
		return this.entityManager.find(Address.class, id);
	}

	@Override
	public void updateAddress(Address address) {
		entityManager.merge(address);
		
	}

	@Override
	public List<Address> getAllAddressByUserId(String userId) {
		List<Address> addressList = entityManager.createNamedQuery("Address.findAddressByUserId",Address.class).setParameter("userId", userId).getResultList();
		return addressList;
	}

	@Override
	public List<Address> getAllAddressByShopId(String shopId) {
		List<Address> addressList = entityManager.createNamedQuery("Address.findByShopId",Address.class).setParameter("shopId", shopId).getResultList();
		return addressList;
	}

	@Override
	public List<Address> getAddressByUserIdAndShopId(String userId, String shopId) {
		List<Address> addressList = entityManager.createNamedQuery("Address.findByUserIdAndShopId",Address.class).setParameter("userId", userId).setParameter("shopId", shopId).getResultList();
		return addressList;
	}

	@Override
	public Address getDefaultAddress(String userId) {
		 Address address = entityManager.createNamedQuery("Address.findDefaultAddress",Address.class).setParameter("userId", userId).getSingleResult();
		return  address;
	}


}
