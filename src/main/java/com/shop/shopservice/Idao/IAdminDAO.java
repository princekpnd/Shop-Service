package com.shop.shopservice.Idao;

import java.util.List;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.AdminDeviceID;
public interface IAdminDAO {
	
	List<Admin> getAllAdmin();
	Admin getAdminById(int adminId);
	Admin getAdminByEmailId(String emailId);
	Admin getAdminByShopId(String shopId);
	List<Admin> getAdminByAdminIdAndShopId(int adminId, String shopId);
	List<Admin> getAdminByFirstName(String firstName);
	List<Admin> getAdminByAdharNumber(int adharNumber);
	Admin getByMobileNumber(String mobileNo);
	  void updateAdmin(Admin admin);
	  Admin validateAdminByPwd(String emailId,String pwd);
	  Admin validateAdminByDeviceId(String deviceId);
	  void deleteAdminDevice(AdminDeviceID ad);
	  boolean adminExists(String mobileNo);
	  void addAdmin(Admin admin);

}
