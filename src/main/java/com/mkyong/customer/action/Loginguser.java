package com.mkyong.customer.action;

import com.mkyong.customer.model.Customer;
import com.mkyong.customer.model.User;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author bryan
 * @date 2016/1/7.
 */

public class Loginguser  extends ActionSupport
        implements ModelDriven {

    User user = new User();


    public Object getModel() {
        return user;
    }

    public String execute() throws Exception {

        return SUCCESS;

    }


    /**
     * 用户登录系统，动态获取目录
     * @return
     */
    public String getMenu(){



        return "success";
    }
}
