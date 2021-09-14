package com.bridgelabs.employeepayroll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollIo {
	public static String Payroll_String = "payroll.txt";
	public static File Payroll_File = new File("F:\\Pritesh\\payroll");

	public void writeData(ArrayList<EmployeePayroll> employeePayrollData) {
		StringBuffer empData = new StringBuffer();
		employeePayrollData.stream().forEach(n -> {
			String emp = n.toString().concat("\n");
			empData.append(emp);
		});
		try {
			Files.write(empData.toString().getBytes(), Payroll_File);
		} catch (IOException x) {
			x.printStackTrace();
		}

	}
	public long countEntries() {
		long entries=0;
		try {
			entries=Files.lines(new File("payroll.txt").toPath()).count();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<EmployeePayroll> readData(){
		List<EmployeePayroll> employeePayrollList=new ArrayList<>();
		try {
			Files.lines(new File("payroll-file.txt").toPath())
			.map(line->line.trim())
			.forEach(line->System.out.println(line));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
		
	}
	public void printData() {
		  
		try {
			Files.lines(new File("payroll-file.txt").toPath())
			.forEach(System.out::println);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
