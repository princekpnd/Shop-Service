package com.shop.shopservice.service;

import java.util.List;
import com.shop.shopservice.entity.Employee;

public interface IEmployeeService {

	List<Employee> getAllEmployee();

	public Employee getEmployee(int id);

	public Employee getEmployeeByEmailId(String emailId);

	List<Employee> getAllEmployees(int count);

	public List<Employee> getEmployeeByFirstName(String firstName);

	public List<Employee> getEmployeeByShopId(String shopId);

	public boolean updateEmployee(Employee employee);

	public boolean employeeExists(String employeeId);

	public boolean createEmployee(Employee employee);

	public Employee getEmployeeByShopId(String shopId, int employeeId);

}
