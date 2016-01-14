package edu.scut.rjxy.dao;

import java.util.List;

import edu.scut.rjxy.model.Customer;

public interface CustomerDAO{
	
	void addCustomer(Customer customer);
	
	List<Customer> listCustomer();
	
}