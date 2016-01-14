package edu.scut.rjxy.dao.impl;

import java.util.List;

import edu.scut.rjxy.model.Customer;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.scut.rjxy.dao.CustomerDAO;

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