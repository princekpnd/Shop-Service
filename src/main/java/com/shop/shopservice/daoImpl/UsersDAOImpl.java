package com.shop.shopservice.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shopservice.Idao.IUsersDAO;
import com.shop.shopservice.entity.TimeLine;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.entity.UserDeviceID;

@Repository
@Transactional
public class UsersDAOImpl implements IUsersDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getAllUsers(int count) {
		List<User> profileList = entityManager.createNamedQuery("User.findAll", User.class).setMaxResults(count)
				.getResultList();
		return profileList;
	}

	@Override
	public User getUsersById(int userId) {
		return this.entityManager.find(User.class, userId);
	}

	@Override
	public User getUserByOtp(String mobileNo, String otp) {
		return this.entityManager.createNamedQuery("User.findByOtp", User.class).setParameter("mobileNo", mobileNo)
				.setParameter("otp", otp).getSingleResult();
	}

	@Override
	public void addUser(User user) {
		entityManager.persist(user);

	}

	@Override
	public void updateUsers(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUsers(int userId) {
		entityManager.find(User.class, userId).setIsActive(Boolean.FALSE);
		entityManager.flush();

	}

	@Override
	public boolean usersExists(String mobileNo) {
		User user = entityManager.createNamedQuery("User.findByMobile", User.class).setParameter("mobileNo", mobileNo)
				.getResultList().stream().findFirst().orElse(null);
		;
		return null != user ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public User sendOtp(String mobileNo) {
		return (User) entityManager.createNamedQuery("User.findOtpByMobileNumber", User.class)
				.setParameter("mobileNo", mobileNo).getSingleResult();

	}

	@Override
	public User validateUserByPwd(String email, String pwd) {
		User user = null;
		try {
			user = (User) entityManager.createNamedQuery("User.validatePwd", User.class).setParameter("email", email)
					.setParameter("pwd", pwd).getSingleResult();

		} catch (NoResultException nre) {
			nre.printStackTrace();
		}
		return user;
	}

	@Override
	public User validateUserByDeviceId(String deviceId) {
		User ud = null;
		try {
			ud = (User) entityManager.createNamedQuery("UserDeviceID.fetchUserId", User.class)
					.setParameter("deviceId", deviceId).getSingleResult();
		} catch (NoResultException nre) {
		}
		return null != ud ? ud : null;
	}

	@Override
	public User getUsersByEmailId(String emailId) {
		return (User) entityManager.createNamedQuery("User.findByEmail", User.class).setParameter("emailId", emailId)
				.getSingleResult();
	}

//	@Override
//	public int viewWalletBalance(int userId) {
//		return entityManager.createNamedQuery("User.viewWalletBalance", Integer.class).setParameter("userId", userId).getSingleResult();
//	}

//	public boolean updateWalletBalance(int userId, int amount) {
//		User user = getUsersById(userId);
//		int amt = user.getWalletBalance();
//		if(amount > 0) {
//			amt = amt + amount;		
//		} else if (amount < 0){
//			if (amt > amount)
//			amt = amt + amount;
//			else {
//				return false;
//			}
//		}
//		user.setWalletBalance(amt);
//		updateUsers(user);
//		return true;
//	}

//	@Override
//	public User validateAdminUserByPwd(String email, String pwd) {
//		User user = null;
//		try {
//			user = (User) entityManager.createNamedQuery("User.validateAdminPwd", User.class)
//					.setParameter("email", email).setParameter("pwd", pwd).getSingleResult();
//		} catch (NoResultException nre) {
//			nre.printStackTrace();
//		}
//		return user;
//	}

	/*
	 * public void followConsultantByUser(User consultant, Integer userId) { if
	 * (null != consultant && null != userId) { String followedBy =
	 * consultant.getFollowedBy(); if (!Strings.isBlank(followedBy) &&
	 * !followedBy.contains(Integer.toString(userId))) { followedBy = followedBy +
	 * "," + Integer.toString(userId);
	 * consultant.setFollowCount(consultant.getFollowCount() + 1); } else if
	 * (!Strings.isBlank(followedBy) &&
	 * followedBy.contains(Integer.toString(userId))) {
	 * 
	 * followedBy = followedBy.startsWith(Integer.toString(userId)) &&
	 * followedBy.contains(",") ? followedBy.replace(Integer.toString(userId) + ",",
	 * Strings.EMPTY) : followedBy.replace("," + Integer.toString(userId),
	 * Strings.EMPTY); if (followedBy.startsWith(Integer.toString(userId)) &&
	 * !followedBy.contains(",") ) followedBy=
	 * followedBy.replace(Integer.toString(userId), Strings.EMPTY);
	 * 
	 * consultant.setFollowCount(consultant.getFollowCount() - 1); } else if
	 * (Strings.isBlank(followedBy)) { followedBy = Integer.toString(userId);
	 * consultant.setFollowCount(consultant.getFollowCount() == 0 ? 1 :
	 * consultant.getFollowCount() + 1); } consultant.setFollowedBy(followedBy);
	 * entityManager.persist(consultant); consultant = null; } }
	 */

	@Override
	public void deleteUserDevice(UserDeviceID ud) {
		Query query = entityManager.createQuery("delete UserDeviceID where id = " + ud.getId());
		query.executeUpdate();
		// entityManager.createNamedQuery("UserDeviceID.updateDeviceId",UserDeviceID.class).setParameter("deviceId",
		// ud.getDeviceId()+"DEL").setParameter("id", ud.getId()).executeUpdate();
		entityManager.flush();
	}

	@Override
	public void addWishListByProductId(User user, String productId) {		
		if (null != user) {
			String wishList = user.getWishList();
			int wishCount = user.getWishCount();
			if (!Strings.isBlank(wishList) && !wishList.contains(productId)) {
				wishList = wishList + "," + productId;
				user.setWishCount(wishCount + 1);
			} else if (!Strings.isBlank(wishList) && wishList.contains(productId)) {
				wishList = wishList.startsWith(productId) && wishList.contains(",")
						? wishList.replace(productId + ",", Strings.EMPTY)
						: wishList.replace("," + productId, Strings.EMPTY);
				if (wishList.startsWith(productId) && !wishList.contains(","))
					wishList = wishList.replace(productId, Strings.EMPTY);

				user.setWishCount(wishCount - 1);
			} else if (Strings.isBlank(wishList)) {
				wishList = productId;
				user.setWishCount(wishCount == 0 ? 1 : wishCount + 1);
			}
			user.setWishList(wishList);
			entityManager.persist(user);
			user = null;
		}

	}

	@Override
	public List<User> getUserByUserIdAndShopId(int userId, String shopId) {
		List<User> userList = entityManager.createNamedQuery("User.findByUserIdAndShopId", User.class).setParameter("userId", userId).setParameter("shopId", shopId).getResultList();
		return userList;
	}

	@Override
	public boolean userExistByEmail(String emailId) {
		User user = entityManager.createNamedQuery("User.findByEmail", User.class).setParameter("emailId", emailId)
				.getResultList().stream().findFirst().orElse(null);
		;
		return null != user ? Boolean.TRUE : Boolean.FALSE;}

	@Override
	public List<User> getUserByShopId(String shopId) {
		List<User> userList = entityManager.createNamedQuery("User.findByShopId",User.class).setParameter("shopId", shopId).getResultList();
		return userList;
	}
}
