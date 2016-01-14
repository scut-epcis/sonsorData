package com.mkyong.customer.dao.impl;

import java.util.List;

import com.mkyong.customer.model.Mycustomer;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;
 
public class CustomerDAOImpl extends HibernateDaoSupport implements CustomerDAO{
	
	//add the customer
	public void addCustomer(Customer customer){
		
		getHibernateTemplate().save(customer);
		
	}
	
	//return all the customers in list
	public List<Customer> listCustomer(){


		String sql = "select * from mycustomer";
		System.out.println("sql="+sql);
		SQLQuery query = this.getSession().createSQLQuery(sql);
//		query.addEntity(Mycustomer.class);

		List results = query.list();
		System.out.println("list size = " + results.size());
		//return getHibernateTemplate().find("from Customer");
		return results;
	}
	
}