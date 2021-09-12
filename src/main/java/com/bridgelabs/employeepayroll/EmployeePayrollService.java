package com.bridgelabs.employeepayroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

	public static ArrayList<EmployeePayroll> empData;
	public EmployeePayrollService() {}
	public EmployeePayrollService(List<EmployeePayroll> employeePayrollList) {
		
	}

	public void readData(Scanner sc) {
		System.out.println("Enter employee Id :- ");
		int id = sc.nextInt();
		System.out.println("Enter name :- ");
		String name = sc.next();
		System.out.println("Enter salary :- ");
		double salary = sc.nextDouble();
		empData.add(new EmployeePayroll(id, name, salary));
	}

	public void writeData() {
		System.out.println("List Of Employee Payroll Data\n" + empData);
		System.out.println(empData);
	}

	public static void main(String[] args) {
		empData = new ArrayList<>();
		EmployeePayrollService employee = new EmployeePayrollService();
		Scanner sc = new Scanner(System.in);
		employee.readData(sc);
		employee.writeData();
	}
	}
