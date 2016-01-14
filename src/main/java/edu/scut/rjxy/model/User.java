package edu.scut.rjxy.model;

/**
 * @author bryan
 * @date 2016/1/11.
 */

public class User {

    private String name;
    private String city;
    private String passwd;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String toString(){
        return "name=" + name + " ,city=" + city + ",passwd=" + passwd;
    }
}

