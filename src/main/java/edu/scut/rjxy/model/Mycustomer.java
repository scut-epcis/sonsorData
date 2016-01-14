package edu.scut.rjxy.model;

/**
 * @author bryan
 * @date 2016/1/7.
 */

public class Mycustomer {

    private String NAME;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String toString(){
        return "name=" + NAME + " ,city=" + city;
    }
}
