package com.shop.shopservice.daoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shopservice.Idao.ILookUp;
import com.shop.shopservice.Idao.INotificationDAO;
import com.shop.shopservice.Idao.IOfflineDAO;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.Cart;
import com.shop.shopservice.entity.LookUp;
import com.shop.shopservice.entity.Notification;
import com.shop.shopservice.entity.User;
import com.shop.shopservice.entity.Withdraw;
import com.shop.shopservice.service.IAdminService;
import com.shop.shopservice.service.ICartService;
import com.shop.shopservice.service.IUserService;
import com.shop.shopservice.service.IWithdrawService;

@Repository
@Transactional
public class NotificationDAOImpl implements INotificationDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ILookUp lookup;

	@Autowired
	ICartService cartService;
	
	@Autowired
	private IWithdrawService withdrawService;
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IUserService UserService;

	@Override
	public List<Notification> getAll() {
		List<Notification> notificationList = entityManager.createNamedQuery("Notification.getAll", Notification.class)
				.getResultList();
		return notificationList;
	}

	@Override
	public boolean createNotification(int articleId, int notificationType) {
		Notification notification = new Notification();
		LookUp lookUp = lookup.findLookUpIdByName("MILAAN", "ORDER_NOTIFICATION");
		LookUp lookUp1 = lookup.findLookUpIdByName("MILAAN", "RECHARGE_NOTIFICATION");
		LookUp lookUp2 = lookup.findLookUpIdByName("MILAAN", "WITHDRAW_NOTIFICATION");
		LookUp lookUp3 = lookup.findLookUpIdByName("MILAAN", "TRANSFER_NOTIFICATION");
		LookUp lookUp4 = lookup.findLookUpIdByName("MILAAN", "PLACED");
		LookUp lookUp5 = lookup.findLookUpIdByName("MILAAN", "PACKED");
		LookUp lookUp6 = lookup.findLookUpIdByName("MILAAN", "SHIPPED");
		LookUp lookUp7 = lookup.findLookUpIdByName("MILAAN", "DELIVERED");
		LookUp lookUp8 = lookup.findLookUpIdByName("MILAAN", "SUCCESSFUL");
		LookUp lookUp9 = lookup.findLookUpIdByName("MILAAN", "ACCEPTED");
		LookUp lookUp10 = lookup.findLookUpIdByName("MILAAN", "REJECTED");
//	LookUp lookUp11 = lookup.findLookUpIdByName("MILAAN", "DEACTIVE");
		LookUp lookUp12 = lookup.findLookUpIdByName("MILAAN", "DENIED");
		LookUp lookUp13 = lookup.findLookUpIdByName("MILAAN", "RECEIVED");
		LookUp lookUp14 = lookup.findLookUpIdByName("MILAAN", "ADMIN");
		LookUp lookUp15 = lookup.findLookUpIdByName("MILAAN", "CUSTOMER");
		LookUp lookUp16 = lookup.findLookUpIdByName("MILAAN", "BY_DELIVERY_BOY");
		LookUp lookUp17 = lookup.findLookUpIdByName("MILAAN", "SELF_DELIVERY");
		LookUp lookUp18 = lookup.findLookUpIdByName("MILAAN", "COURIER");

		int orderNotification = lookUp.getLookUpId();
		int rechargeNotification = lookUp1.getLookUpId();
		int withdrawNotification = lookUp2.getLookUpId();
		int transferNotification = lookUp3.getLookUpId();
		int placed = lookUp4.getLookUpId();
		int packed = lookUp5.getLookUpId();
		int shipped = lookUp6.getLookUpId();
		int delivered = lookUp7.getLookUpId();
		int successful = lookUp8.getLookUpId();
		int accepted = lookUp9.getLookUpId();
		int reject = lookUp10.getLookUpId();
//	int deactive = lookUp11.getLookUpId();
		int denied = lookUp12.getLookUpId();
		int received = lookUp13.getLookUpId();
		int admin = lookUp14.getLookUpId();
		int customer = lookUp15.getLookUpId();
		int deliveryBoy = lookUp16.getLookUpId();
		int selfDelivery = lookUp17.getLookUpId();
		int courier = lookUp18.getLookUpId();

		Cart cart = cartService.getCart(articleId);
		String summeryDetails = " ", summaryDetailForAdmin = "";
		if (notificationType == orderNotification) {

			int orderStatus = cart.getOrderStatus(), cartId = cart.getCartId(), deliveryType = cart.getDeliveryType();
			String adminId = cart.getAdminId(), shopId = cart.getShopId(), shopName = cart.getShopName(),
					userId = cart.getUserId(), userName = cart.getUserName();
		
			float payableAmount = cart.getPayableAmount();
			if (orderStatus == placed) {
//				summaryDetail = shopName + " you have got an order ( ID " + cartId + " ), of Rs. " + payableAmount
//						+ " from " + userName + ".";
				summeryDetails = shopName +"you have got an order (ID " +cartId +"), "
						+ "of Rs. +payableAmount" + "from" + userName + ".";

				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(admin);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(customer);

				entityManager.persist(notification);

			} else if (orderStatus == packed) {
				
				summeryDetails = userName + " your order ( ID " + cartId + " ) of Rs. " + payableAmount
						+ " has been packed by " + shopName + ".";
				
				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(admin);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(customer);

				entityManager.persist(notification);
			} else if (orderStatus == shipped) {

				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(customer);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(admin);
				if (deliveryType == deliveryBoy) {

					String dboyName = cart.getdBoyName(), dMobileNumber = cart.getdBoyNumber();
					String dBoyName = cart.getdBoyName(), dBoyNumber = cart.getdBoyNumber();
					summeryDetails = userName + " your order ( ID " + cartId + " ) of Rs. " + payableAmount
							+ " has been shipped by " + shopName + " through delivery boy " + dBoyName
							+ " and mobile number is " + dBoyNumber + ".";
					notification.setSummeryDetails(summeryDetails);
				} else if (deliveryType == courier) {
					String courierName = cart.getCourierName(), shippingId = cart.getShippingId();
					summeryDetails = userName + " your order ( ID " + cartId + " ) of Rs. " + payableAmount
							+ " has been shipped by " + shopName + " through courier " + courierName
							+ " and tracking ID is " + shippingId + ".";
					notification.setSummeryDetails(summeryDetails);
				} else if (deliveryType == selfDelivery) {
					summeryDetails = userName + " your order ( ID " + cartId + " ) of Rs. " + payableAmount
							+ " is ready to self pick up at " + shopName + ".";
					notification.setSummeryDetails(summeryDetails);
				}
				entityManager.persist(notification);

			} else if (orderStatus == delivered) {
				
				summeryDetails = userName + " you have received your order ( ID " + cartId + " ) of Rs. " + payableAmount
						+ " by " + shopName + ".";
				
				summaryDetailForAdmin = shopName + " you have successfully delivered ( ID " + cartId + " ) of Rs. " + payableAmount
						+ " by " + userName + ".";
				
				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(admin);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(customer);
				entityManager.persist(notification);

				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(customer);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summaryDetailForAdmin);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(admin);
				entityManager.persist(notification);

			} else if (orderStatus == successful) {
				summeryDetails = "you have received your order (" + ") of Rs. " + " by " + "Vishal.";
				
				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(customer);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(admin);
				entityManager.persist(notification);

			} else if (orderStatus == accepted) {
				summeryDetails = userName + " your order ( ID " + cartId + " ) of Rs. " + payableAmount
						+ " has been accepted by " + shopName + ".";
				
				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(customer);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(admin);
				entityManager.persist(notification);

			} else if (orderStatus == reject) {
				
				summeryDetails = userName + " your order ( ID " + cartId + " ) of Rs. " + payableAmount
						+ " has been rejected by " + shopName + ".";
				
				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(admin);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(customer);
				entityManager.persist(notification);

			} else if (orderStatus == denied) {
				summeryDetails = "you have received your order (" + ") of Rs. " + " by " + "Vishal.";
				
				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(admin);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(customer);
				entityManager.persist(notification);

			} else if (orderStatus == received) {
				summeryDetails = userName + " you have received your order ( ID " + cartId + " ) of Rs. " + payableAmount
						+ " by " + shopName + ".";
				
				notification.setActive(Boolean.TRUE);
				notification.setAdminId(Integer.parseInt(adminId));
				notification.setArticleId(cartId);
				notification.setCreatedOn(new Date());
				notification.setDeleted(Boolean.FALSE);
				notification.setNotificationFor(admin);
				notification.setNotificationType(notificationType);
				notification.setShopId(shopId);
				notification.setShopName(shopName);
				notification.setSummeryDetails(summeryDetails);
				notification.setUserId(Integer.parseInt(userId));
				notification.setUserName(userName);
				notification.setUserType(customer);
				entityManager.persist(notification);

			}
		} else if (notificationType == withdrawNotification) {
			Withdraw withdraw = withdrawService.getById(articleId);
			int userId = withdraw.getUserId(), withdrawBalance = withdraw.getWithdrawBalance();
			int adminId = withdraw.getAdminId();
			String shopId = withdraw.getShopId();
			Admin admin1 = adminService.getAdmin(adminId);
			String shopName = admin1.getShopName();
			User user = UserService.getUser(userId);
			String userName = user.getFirstName();
			
			summeryDetails = userName + " you have made a withdrawl request of Rs. " + withdrawBalance + ", Request ID ( " + articleId + " )."
					+ " Your requested amount will be deposited in your given Bank Account within 3 to 6 working days. Thank you for being a part of MILAAN.";
			
			notification.setActive(Boolean.TRUE);
			notification.setAdminId(adminId);
			notification.setArticleId(articleId);
			notification.setCreatedOn(new Date());
			notification.setDeleted(Boolean.FALSE);
			notification.setNotificationFor(admin);
			notification.setNotificationType(notificationType);
			notification.setShopId(shopId);
			notification.setShopName(shopName);
			notification.setSummeryDetails(summeryDetails);
			notification.setUserId(userId);
			notification.setUserName(userName);
			notification.setUserType(customer);
			entityManager.persist(notification);
			 
		} else if (notificationType == transferNotification) {

		} else if (notificationType == rechargeNotification) {

 		}
		return false;
	}

}
