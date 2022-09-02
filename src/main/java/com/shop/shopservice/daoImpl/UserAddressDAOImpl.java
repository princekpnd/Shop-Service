package com.shop.shopservice.daoImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.IUserAddressDAO;
import com.shop.shopservice.entity.UserAddress;
@Repository
@Transactional
public class UserAddressDAOImpl implements IUserAddressDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserAddress> getAllUserAddress() {
		List<UserAddress> userAddressList= entityManager.createNamedQuery("UserAddress,findAllUserAddress",UserAddress.class).getResultList();
		return userAddressList;
	}

	@Override
	public List<UserAddress> getAddressByUserId(String userId) {
		List<UserAddress> userAddressList=entityManager.createNamedQuery("UserAddress,findByUserId",UserAddress.class).setParameter("userId", userId).getResultList();
		return userAddressList;
	}

	@Override
	public List<UserAddress> getAddressByShopId(String shopId) {
		List<UserAddress> userAddressList =entityManager.createNamedQuery("UserAddress,findByShopId",UserAddress.class).setParameter("shopId", shopId).getResultList();
		return userAddressList;
	}

	@Override
	public boolean userAddressExists(String userId) {
		UserAddress userAddress = entityManager.createNamedQuery("UserAdderss.findAddressByUserId",UserAddress.class).setParameter("userId", userId).getResultList().stream().findFirst().orElse(null);;
		return null != userAddress ?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public void addUserAddress(UserAddress userAddress) {
			entityManager.persist(userAddress);
			
		}

	@Override
	public void updateUserAddress(UserAddress userAddress) {
			entityManager.merge(userAddress);
			
		}

	@Override
	public UserAddress getUserAddress(String userId) {
		UserAddress userAddress = entityManager.createNamedQuery("UserAddress.createAddressByUserId",UserAddress.class).setParameter("userId", userId).getSingleResult();
		return userAddress;
	}
	}
		


