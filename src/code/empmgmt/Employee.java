package code.empmgmt;

import java.util.List;

public class Employee {
	private int id;
	private String emilId;
	public String getEmilId() {
		return emilId;
	}
	public void setEmilId(String emilId) {
		this.emilId = emilId;
	}

	private String name;
	private int age;
	private Department department;
	private Level level;
	
	private List<Employee> reportees;
	
	public void addReportee(Employee emp) {
		reportees.add(emp);
	}
	
	private String managerId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public List<Employee> getReportees() {
		return reportees;
	}
	public void setReportees(List<Employee> reportees) {
		this.reportees = reportees;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}
