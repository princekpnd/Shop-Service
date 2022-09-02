package com.shop.shopservice.service;

import java.util.List;
import com.shop.shopservice.entity.Admin;
import com.shop.shopservice.entity.AdminDeviceID;
public interface IAdminService {
	List<Admin> getAllAdmin();
	public Admin getAdmin(int adminId);
	public List<Admin> getAdminByFirstName(String firstName);
	public List<Admin> getAdminByAdminIdAndShopId(int adminId ,String shopId);
	public Admin getAdminByEmailId(String emailId);
	public Admin getAdminByShopId(String shopId);
	public Admin getByMobileNumber(String mobileNo);
	public List<Admin> getAdminByAdharNumber(int adharNumber);
	public boolean updateAdmin(Admin admin);
	public Admin loginAdmin(String emailId, String pwd);
	Admin validateAdminByDeviceId(String deviceId);
	public void deleteAdminDevice(AdminDeviceID ad);	
	public boolean adminExists(String mobileNo);
	public boolean createAdmin(Admin admin);
}
