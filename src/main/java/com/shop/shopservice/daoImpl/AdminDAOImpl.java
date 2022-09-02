package com.shop.shopservice.daoImpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.IAdminDAO;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.AdminDeviceID;

@Repository
@Transactional
public class AdminDAOImpl implements IAdminDAO{     
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> adminList= entityManager.createNamedQuery("Admin.findAll", Admin.class).getResultList();
		return adminList;
	}
	
	@Override
	public Admin getAdminById(int adminId) {
		return this.entityManager.find(Admin.class, adminId);
	}
	
	@Override
	public Admin getAdminByEmailId(String emailId) {
			return (Admin)entityManager.createNamedQuery("Admin.findByEmail",Admin.class).setParameter("emailId", emailId).getSingleResult();
	}
	
	
	@Override
	public Admin getAdminByShopId(String shopId) {
			return (Admin)entityManager.createNamedQuery("Admin.findByShop",Admin.class).setParameter("shopId", shopId).getSingleResult();
	}
	
	@Override
	public List<Admin> getAdminByFirstName(String firstName) {
		List<Admin> adminList= entityManager.createNamedQuery("Admin.findByFirstName",Admin.class).setParameter("firstName", firstName).getResultList();
		return adminList;
	}
	
	@Override
	public List<Admin> getAdminByAdharNumber(int adharNumber) {
		return this.entityManager.createNamedQuery("Admin.findAdminByAdharNumber", Admin.class).setParameter("adharNumber", adharNumber).getResultList();
	}
	
	@Override
	public void updateAdmin(Admin admin) {
	entityManager.merge(admin);
	}
	
	@Override
	public Admin validateAdminByPwd(String emailId, String pwd) {
		Admin admin = null;
		try {
			admin = (Admin) entityManager.createNamedQuery("Admin.validatePwd", Admin.class).setParameter("emailId", emailId)
					.setParameter("pwd", pwd).getSingleResult();
		} catch (NoResultException nre) {
			nre.printStackTrace();
		}
		return admin;
	}
	
	@Override
	public Admin validateAdminByDeviceId(String deviceId) {
		Admin ad = null;
		try {
			ad =  (Admin)entityManager.createNamedQuery("AdminDeviceID.fetchAdminId",Admin.class).setParameter("deviceId", deviceId).getSingleResult();
		}
		catch(NoResultException nre) {}
		return null != ad ? ad:null;
	}
	@Override
	public void deleteAdminDevice(AdminDeviceID ad) {
		Query query = entityManager.createQuery("delete AdminDeviceID where id = "+ad.getId());
		query.executeUpdate();
	//	entityManager.createNamedQuery("UserDeviceID.updateDeviceId",UserDeviceID.class).setParameter("deviceId", ud.getDeviceId()+"DEL").setParameter("id", ud.getId()).executeUpdate();
		entityManager.flush();
	}
	
	@Override
	public boolean adminExists(String mobileNo) {
		Admin admin = entityManager.createNamedQuery("Admin.findByShopId",Admin.class).setParameter("mobileNo", mobileNo).getResultList().stream().findFirst().orElse(null);;
		return null != admin ?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public void addAdmin(Admin admin) {
		entityManager.persist(admin);

	}

	@Override
	public List<Admin> getAdminByAdminIdAndShopId(int adminId, String shopId) {
		List<Admin> adminList = entityManager.createNamedQuery("Admin.findAdminByProductIdAndShopId",Admin.class).setParameter("adminId", adminId).setParameter("shopId", shopId).getResultList();
		return adminList;
	}

	@Override
	public Admin getByMobileNumber(String mobileNo) {
		Admin admin = entityManager.createNamedQuery("Admin.findByMobileNo",Admin.class).setParameter("mobileNo", mobileNo).getSingleResult();
		return admin;
	}

}
