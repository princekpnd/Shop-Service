package com.shop.shopservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "WORK")
@NamedQueries({ @NamedQuery(name = "Work.findAll",
              query = "SELECT wr FROM Work wr"),
	@NamedQuery(name="Work.findByShopId",
               query="SELECT wr FROM Work wr WHERE wr.shopId= :shopId"),
	@NamedQuery(name= "Work findByEmployeeId",
	         query="SELECT wr FROM Work wr WHERE wr.employeeId = :employeeId"),
    @NamedQuery(name= "Work.findWorkByShopId",
               query="SELECT wr FROM Work wr WHERE wr.shopId = :shopId  and  isActive is TRUE and  isDeleted is FALSE "),
    @NamedQuery(name= "Work.findActiveEmployee",
    query="SELECT wr FROM Work wr WHERE wr.shopId = :shopId  and wr.employeeId = :employeeId and wr.isActive = :active"),

})



public class Work implements Serializable{
	
	private static final long serialVersionUID = 1385794955661915701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "ABSENT", nullable = false)
	private int absent;
	
	@Column(name = "PRESENT", nullable = false)
	private int present;
	
	@Column(name = "BONUS", nullable = false)
	private float bonus;
	
	@Column(name = "CREATED_ON", nullable = false)
	private Date createdOn;
	
	@Column(name = "ADVANCE_SALARY", nullable = false)
	private float advanceSalary;
	
	@Column(name = "SALARY", nullable = false)
	private float salary;
	
	@Column(name = "SALARY_TYPE", nullable = false)
	private int salaryType;
	
	@Column(name = "LEAVE_FROM", nullable = false)
	private Date leaveFrom;
	
	@Column(name = "LEAVE_TO", nullable = false)
	private Date leaveTo;
	
	@Column(name = "LEAVE_TYPE", nullable = false)
	private int leaveType;
	

	@Column(name = "INCENTIVE", nullable = false)
	private float incentive;
	
	
	@Column(name = "ATTENDANCE", nullable = false)
	private int attendance;


	@Column(name = "EMPLOYEE_ID", nullable = false)
	private String employeeId;
	
	@Column(name = "SHOP_ID", nullable = false)
	private String shopId;
	
	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;
	
	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "TOTAL_SALARY", nullable = false)
	private float totalSalary;
	
	@Column(name ="UPDATED_ON", nullable =false)
	private Date updatedOn;
	
	@Column(name ="DEDUCTION", nullable =false)
	private float deduction;
	
	@Column(name ="PAYMENT_STATUS", nullable =false)
	private boolean paymentStatus;
	
	@Column(name ="PAID_SALARY", nullable =false)
	private float paidSalary;
	
	@Column(name ="DUES_SALARY", nullable =false)
	private float duesSalary;
	
	
	
	public Work() {
		super();
	}
	
	
	public Work(String shopId, String employeeId) {
		this.shopId = shopId;
		this.employeeId = employeeId;
	}



	@Transient
	private List<Attendance> Attendance;


	/**
	 * @param attendance the attendance to set
	 */
	public void setAttendance(List<Attendance> attendance) {
		Attendance = attendance;
	}


	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}


	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}


	/**
	 * @return the totalSalary
	 */
	public float getTotalSalary() {
		return totalSalary;
	}


	/**
	 * @param totalSalary the totalSalary to set
	 */
	public void setTotalSalary(float totalSalary) {
		this.totalSalary = totalSalary;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the absent
	 */
	public int getAbsent() {
		return absent;
	}


	/**
	 * @param absent the absent to set
	 */
	public void setAbsent(int absent) {
		this.absent = absent;
	}


	/**
	 * @return the present
	 */
	public int getPresent() {
		return present;
	}


	/**
	 * @param present the present to set
	 */
	public void setPresent(int present) {
		this.present = present;
	}


	/**
	 * @return the bonus
	 */
	public float getBonus() {
		return bonus;
	}


	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}


	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}


	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	/**
	 * @return the advanceSalary
	 */
	public float getAdvanceSalary() {
		return advanceSalary;
	}


	/**
	 * @param advanceSalary the advanceSalary to set
	 */
	public void setAdvanceSalary(float advanceSalary) {
		this.advanceSalary = advanceSalary;
	}


	/**
	 * @return the salary
	 */
	public float getSalary() {
		return salary;
	}


	/**
	 * @param salary the salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}


	/**
	 * @return the salaryType
	 */
	public int getSalaryType() {
		return salaryType;
	}


	/**
	 * @param salaryType the salaryType to set
	 */
	public void setSalaryType(int salaryType) {
		this.salaryType = salaryType;
	}


	/**
	 * @return the leaveFrom
	 */
	public Date getLeaveFrom() {
		return leaveFrom;
	}


	/**
	 * @param leaveFrom the leaveFrom to set
	 */
	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}


	/**
	 * @return the leaveTo
	 */
	public Date getLeaveTo() {
		return leaveTo;
	}


	/**
	 * @param leaveTo the leaveTo to set
	 */
	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}


	/**
	 * @return the leaveType
	 */
	public int getLeaveType() {
		return leaveType;
	}


	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(int leaveType) {
		this.leaveType = leaveType;
	}


	/**
	 * @return the incentive
	 */
	public float getIncentive() {
		return incentive;
	}


	/**
	 * @param incentive the incentive to set
	 */
	public void setIncentive(float incentive) {
		this.incentive = incentive;
	}


	
	
	/**
	 * @return the attendance
	 */
	public int getAttendance() {
		return attendance;
	}


	/**
	 * @param attendance the attendance to set
	 */
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}


	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}


	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	/**
	 * @return the shopId
	 */
	public String getShopId() {
		return shopId;
	}


	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}


	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}


	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	/**
	 * @return the deduction
	 */
	public float getDeduction() {
		return deduction;
	}


	/**
	 * @param deduction the deduction to set
	 */
	public void setDeduction(float deduction) {
		this.deduction = deduction;
	}


	/**
	 * @return the paymentStatus
	 */
	public boolean isPaymentStatus() {
		return paymentStatus;
	}


	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	/**
	 * @return the paidSalary
	 */
	public float getPaidSalary() {
		return paidSalary;
	}


	/**
	 * @param paidSalary the paidSalary to set
	 */
	public void setPaidSalary(float paidSalary) {
		this.paidSalary = paidSalary;
	}


	/**
	 * @return the duesSalary
	 */
	public float getDuesSalary() {
		return duesSalary;
	}


	/**
	 * @param duesSalary the duesSalary to set
	 */
	public void setDuesSalary(float duesSalary) {
		this.duesSalary = duesSalary;
	}


	

	
}
