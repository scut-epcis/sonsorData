package edu.scut.rjxy.bo;

import java.util.List;

import edu.scut.rjxy.model.Customer;

public interface CustomerBo{
	
	void addCustomer(Customer customer);
	
	List<Customer> listCustomer();

	void queryTest();
}