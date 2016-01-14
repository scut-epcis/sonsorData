package edu.scut.rjxy.bo.impl;

import java.util.List;

import edu.scut.rjxy.bo.CustomerBo;
import edu.scut.rjxy.dao.CustomerDAO;
import edu.scut.rjxy.model.Customer;

public class CustomerBoImpl implements CustomerBo {
	
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