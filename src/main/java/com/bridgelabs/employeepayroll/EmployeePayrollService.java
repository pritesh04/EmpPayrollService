package com.bridgelabs.employeepayroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

	public static List<EmployeePayroll> empData;

	public EmployeePayrollService() {
	}

	public EmployeePayrollService(List<EmployeePayroll> employeePayrollList) {

	}

	public static ArrayList<EmployeePayroll> employeePayrollData;

	public void writeEmployeePayrollData(IOService ioService) {
		// System.out.println("\nWriting Employee Payroll Roaster to Console\n" +
		// employeePayrollList);
		if (ioService.equals(IOService.CONSOLE_IO)) {
			System.out.println(employeePayrollData);
		} else if (ioService.equals(IOService.FILE_IO)) {
			new EmployeePayrollIo().writeData((ArrayList<EmployeePayroll>) empData);
		}
	}

	public void readData(Scanner consoleInputReader) {
		System.out.println("Enter employee Id :- ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter name :- ");
		String name = consoleInputReader.next();
		System.out.println("Enter salary :- ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollData.add(new EmployeePayroll(id, name, salary));
	}

	public void writeData(ArrayList<EmployeePayroll> employeePayrollData2) {
		if (employeePayrollData2.equals(IOService.CONSOLE_IO)) {
			System.out.println("List Of Employee Payroll Data\n");
			System.out.println(employeePayrollData);
		} else if (employeePayrollData2.equals(IOService.FILE_IO)) {
			new EmployeePayrollService().writeData(employeePayrollData);
		}
	}

	public static void main(String[] args) {
		empData = new ArrayList<>();
		EmployeePayrollService employee = new EmployeePayrollService();
		Scanner sc = new Scanner(System.in);
		employee.readData(sc);

	}

	public void printData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO)) {
			((EmployeePayrollIo) new EmployeePayrollIo()).printData();
		}
	}

	public long countEnteries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO)) {
			return new EmployeePayrollIo().countEntries();
		}
		return 0;
	}

	public long readEmployeePayrolData(IOService IOService) {
		if (IOService.equals(IOService.FILE_IO))
			this.empData = new EmployeePayrollIo().readData();
		return empData.size();
	}
}
