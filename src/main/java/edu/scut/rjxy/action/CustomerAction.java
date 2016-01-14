package edu.scut.rjxy.action;

import java.util.*;

import edu.scut.rjxy.bo.CustomerBo;
import edu.scut.rjxy.model.Customer;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;

public class CustomerAction implements ModelDriven {


    private String result;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    Customer customer = new Customer();
    List<Customer> customerList = new ArrayList<Customer>();

    CustomerBo customerBo;

    //DI via Spring
    public void setCustomerBo(CustomerBo customerBo) {
        this.customerBo = customerBo;
    }

    public Object getModel() {
        return customer;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    //save customer
    public String addCustomer() throws Exception {

        //save it
        Customer customer = new Customer();
        customer.setCreatedDate(new Date());
        customer.setName("sa");
        customer.setAddress("scut");
        System.out.println(customer);
        customerBo.addCustomer(customer);

        //reload the customer list
//		customerList = null;
//		customerList = customerBo.listCustomer();
        System.out.println("11111111111");
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "name");
        map.put("age", 12);
        map.put("position", "scut");

        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面

        return "success";

    }

    public String demo() {
        System.out.println("come demo function-------------");
        customerBo.addCustomer(customer);
        System.out.println("22222222");
        Map<String, Object> map = new HashMap<String, Object>();
        String n0 = "0";
        String n1 = "0";
        for(int i=1;i<7;i++){
            n0+=","+"1"+i;
            n1+=","+"2"+i;
        }
        map.put("name", n0);
        map.put("city", n1);

        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
        return "success";
    }

    public String sensor(){

        return "success";
    }


    //list all customers
    public String listCustomer() throws Exception {

        customerList = customerBo.listCustomer();

        return "success";

    }

}