package com.ems.dto;

public class Employee {

	private int id;
	private String name;
	private String department;
	private int daysAttended;
	
	public Employee(int id, String name, String department, int daysAttended) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.daysAttended = daysAttended;
	}
	
	public Employee() {
		
	}

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getDaysAttended() {
		return daysAttended;
	}

	public void setDaysAttended(int daysAttended) {
		this.daysAttended = daysAttended;
	}
	
}
