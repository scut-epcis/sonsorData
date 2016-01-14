package com.mkyong.customer.bo.impl;

import java.util.List;

import com.mkyong.customer.bo.CustomerBo;
import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;
 
public class CustomerBoImpl implements CustomerBo{
	
	CustomerDAO customerDAO;

	//DI via Spring
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	//call DAO to save customer
	public void addCustomer(Customer customer){
		System.out.println("come addCustomer function");
		System.out.println("resultsize = "+customerDAO.listCustomer().size());
		//customerDAO.addCustomer(customer);

	}
	
	//call DAO to return customers
	public List<Customer> listCustomer(){
		
		return customerDAO.listCustomer();
		
	}

	public void queryTest(){
		System.out.println("come addCustomer function");
		System.out.println("resultsize = "+customerDAO.listCustomer().size());
		//customerDAO.addCustomer(customer);
	}
	
}