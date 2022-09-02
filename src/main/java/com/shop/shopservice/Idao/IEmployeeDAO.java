package com.shop.shopservice.Idao;

import java.util.List;
import com.shop.shopservice.entity.Employee;

public interface IEmployeeDAO {

	List<Employee> getAllEmployee();

	Employee getEmployeeById(int id);

	List<Employee> getAllEmployees(int count);

	List<Employee> getEmployeeByShopId(String shopId);

	List<Employee> getEmployeeByFirstName(String firstName);

	Employee getEmployeeByEmailId(String emailId);

	void updateEmployee(Employee employee);

	boolean employeeExists(String employeeId);

	void addEmployee(Employee employee);

	public Employee getEmployeeByShopId(String shopId, int employeeId);
}
