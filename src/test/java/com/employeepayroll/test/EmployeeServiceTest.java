package com.employeepayroll.test;

import java.util.Arrays;

import org.junit.Test;

import com.bridgelabs.employeepayroll.EmployeePayroll;
import com.bridgelabs.employeepayroll.EmployeePayrollService;
import com.bridgelabs.employeepayroll.IOService;



public class EmployeeServiceTest {
	@Test
	public void givenEmployeeWhenWrittenToFileShouldMatchEmployeeEntries() {
		EmployeePayroll[] arrayOfEmps= {
				new EmployeePayroll(1,"Jeff Bezos",100000.0),
				new EmployeePayroll(2,"Bill Gates",200000.0),
				new EmployeePayroll(3,"Mark Zuckerberg",300000.0)
		};
		EmployeePayrollService eps;
		eps=new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		eps.writeEmployeePayrollData(IOService.FILE_IO);
    	eps.printData(IOService.FILE_IO);
		long entries=eps.countEnteries(IOService.FILE_IO);
		org.junit.Assert.assertEquals(3, entries);
	
	}
	
	@Test
	public void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
		EmployeePayrollService eps = new EmployeePayrollService();
		long entries = eps.readEmployeePayrolData(IOService.FILE_IO);
		org.junit.Assert.assertEquals(3,entries);
	}
	

}
