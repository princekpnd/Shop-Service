package com.shop.shopservice.Idao;

import java.util.List;

import com.shop.shopservice.entity.Address;
import com.shop.shopservice.entity.EmployeeAddress;

public interface IEmployeeAddressDAO {
	
	public List<EmployeeAddress> getAllEmployeeAddress();
	public List<EmployeeAddress> getAddressByShopId(String shopId);
	public List<EmployeeAddress> getAddressByEmployeeId(String employeeId);
	public EmployeeAddress getEmployeeAddress(String employeeId);
	 boolean employeeAddressExists(String employeeId);
	 void addEmployeeAddress(EmployeeAddress employeeAddress);
	 void updateEmployeeAddress(EmployeeAddress employeeAddress);

}
