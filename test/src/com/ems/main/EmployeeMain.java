package com.ems.main;

import java.util.List;

import com.ems.dto.Employee;
import com.ems.exception.EmployeeNotFoundException;
import com.ems.service.EmployeeService;
import com.ems.service.EmployeeServiceImpl;

public class EmployeeMain {

	public static void main(String[] args) {
		
		EmployeeService eService = new EmployeeServiceImpl();
		
		//adding employees
		eService.addEmployee(new Employee(1111, "Michael", "Sales", 28));
		eService.addEmployee(new Employee(2222, "Samuel", "Technology", 25));
		eService.addEmployee(new Employee(3333, "John", "Operations", 30));
//		
		//show all employees
		List<Employee> employeeList = eService.showAllEmployees();
				
		for(Employee e: employeeList) {
			System.out.println("ID: " + e.getId() + 
					" | Name: " + e.getName() + 
					" | Department: " + e.getDepartment() + 
					" | Days Attended: " + e.getDaysAttended());
		}
		
		//delete an employee
//		eService.deleteEmployee(1111);
		
		//updata an enployee 
		eService.updateEmployee(new Employee(1111, "Sohta", "Sutudent", 22));
		
		//find an employee
		try {
			Employee emp = eService.findEmployee(new Employee(2222, "Samuel", "Technology", 25));
			System.out.println("Employee ID: " + emp.getId() + " | Name: " + emp.getName());
		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	}
}
