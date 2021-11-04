package com.ems.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ems.dto.Employee;
import com.ems.exception.EmployeeNotFoundException;

public class EmployeeDAOMySQLImpl implements EmployeeDAO {
	
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	//performing database operations
	private static final String INSERT_COMMAND = "INSERT INTO employees_tbl VALUES (?,?,?,?)"; // placeholder ---> ?
	private static final String DELETE_COMMAND = "DELETE FROM employees_tbl WHERE id=?";
	private static final String UPDATA_COMMAND = "UPDATE employees_tbl SET name=?,department=?,daysAttended=? WHERE id=?";
	private static final String FIND_COMMAND = "SELECT * FROM employees_tbl WHERE id=?";
	private static final String SELECT_ALL = "SELECT * FROM employees_tbl";
	
	
	
	public EmployeeDAOMySQLImpl() {
		try {
			conn = DriverManager.getConnection(EmployeeDAO.URL, EmployeeDAO.USERNAME, EmployeeDAO.PASSWORD);
		} catch (SQLException e) {
			System.out.println("Unable to establish the connection with DB");
			e.printStackTrace();
		}
	}

	@Override
	public void addEmployee(Employee e) {
		int i = 0;
		
		try {
			statement = conn.prepareStatement(INSERT_COMMAND);
			statement.setInt(1, e.getId());
			statement.setString(2, e.getName());
			statement.setString(3, e.getDepartment());
			statement.setInt(4, e.getDaysAttended());
			
			i = statement.executeUpdate();
			
			
		} catch (SQLException e1) {
			System.out.println("Adding record failed... Unable to execute INSERT");
			e1.printStackTrace();
		} finally {
			
			try {
				statement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(i > 1) {
			System.out.println("Record Added...");
		}
		
	}

	@Override
	public void deleteEmployee(int id) {
		int i = 0;
		
		try {
			statement = conn.prepareStatement(DELETE_COMMAND);
			statement.setInt(1, id);
			i = statement.executeUpdate();
		} catch (SQLException e2) {
			System.out.println("Delete operation failed... Unable to execute DELETE");
			e2.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(i > 1) {
			System.out.println("Record Deleted...");
		}
	}

	@Override
	public void updateEmployee(Employee e) {
		int i = 0;
		try {
			statement = conn.prepareStatement(UPDATA_COMMAND);
			statement.setInt(1, e.getId());
			statement.setString(2, e.getName());
			statement.setString(3, e.getDepartment());
			statement.setInt(4, e.getDaysAttended());
			i = statement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
				
		
		
		
	}

	@Override
	public Employee findEmployee(Employee e) throws EmployeeNotFoundException {
		
		Employee findTemp = null;
		
		try {
			statement = conn.prepareStatement(FIND_COMMAND);
			statement.setInt(1, e.getId());
			resultSet = statement.executeQuery();
			
			if(!resultSet.next()) {
				throw new EmployeeNotFoundException(e.getId());
			}
			
			findTemp = new Employee();
			findTemp.setId(resultSet.getInt("id"));
			findTemp.setName(resultSet.getString("name"));
			findTemp.setDepartment(resultSet.getString("department"));
			findTemp.setDaysAttended(resultSet.getInt("daysAttended"));
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return findTemp;
	}

	@Override
	public List<Employee> showAllEmployees() {
		
		Employee selectAllTemp = null;
		List<Employee> empList = new LinkedList<>();
		
		try {
			statement = conn.prepareStatement(SELECT_ALL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				selectAllTemp = new Employee();
				selectAllTemp.setId(resultSet.getInt("id"));
				selectAllTemp.setName(resultSet.getString("name"));
				selectAllTemp.setDepartment(resultSet.getString("department"));
				selectAllTemp.setDaysAttended(resultSet.getInt("daysAttended"));
				
				empList.add(selectAllTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empList;
	}

}
