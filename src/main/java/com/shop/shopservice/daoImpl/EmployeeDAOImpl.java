package com.shop.shopservice.daoImpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shop.shopservice.Idao.IEmployeeDAO;
import com.shop.shopservice.entity.Employee;

@Repository
@Transactional
public class EmployeeDAOImpl implements IEmployeeDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	
	
	
	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> profileList = entityManager.createNamedQuery("Employee.findAllEmployee", Employee.class).getResultList();
		return profileList;
	}
	@Override
	public Employee getEmployeeById(int id) {
		return this.entityManager.find(Employee.class, id);
	}
	
//	@Override
//	public Employee getEmployeeById(int id) {
//		Employee employee = entityManager.createNamedQuery("Employee.findAllEmployeeById",Employee.class).setParameter("id", id).getSingleResult();
//		return employee;
//	}
	
	
	@Override
	public List<Employee> getAllEmployees(int count) {
		List<Employee> profileList = entityManager.createNamedQuery("Employee.findAll", Employee.class).setMaxResults(count).getResultList();
		return profileList;
	}
	
	@Override
	public List<Employee> getEmployeeByFirstName(String firstName) {
		List<Employee> employeeList = entityManager.createNamedQuery("Employee.findByFirstName", Employee.class).setParameter("firstName", firstName).getResultList();
		return employeeList;
	}
	
	@Override
	public List<Employee> getEmployeeByShopId(String shopId) {
		List<Employee> employeeList =entityManager.createNamedQuery("Employee.findByShopId",Employee.class).setParameter("shopId", shopId).getResultList();
		return  employeeList;
	}
	

	@Override
	public Employee getEmployeeByEmailId(String emailId) {
			return (Employee)entityManager.createNamedQuery("Employee.findByEmail", Employee.class).setParameter("emailId", emailId).getSingleResult();
	}
	
	@Override
	public void updateEmployee(Employee employee) {
	entityManager.merge(employee);
	}
	
	@Override
	public boolean employeeExists(String employeeId) {
		Employee employee = entityManager.createNamedQuery("Employee.findByEmployeeId",Employee.class).setParameter("employeeId", employeeId).getResultList().stream().findFirst().orElse(null);;
		return null != employee ?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);
	}
	@Override
	public Employee getEmployeeByShopId(String shopId, int employeeId) {
		Employee profileList = entityManager.createNamedQuery("Employee.findEmployeeByShopId", Employee.class).setParameter("shopId", shopId).setParameter("id", employeeId).getSingleResult();
		return profileList;
	}
	
}
