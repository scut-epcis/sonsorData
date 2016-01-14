package edu.scut.rjxy.model;

/**
 * @author bryan
 * @date 2016/1/11.
 */

public class SensorParameter {

    public String datefield ;
    public String dategap;
    public String sensorno;

    public String getDategap() {
        return dategap;
    }

    public void setDategap(String dategap) {
        this.dategap = dategap;
    }

    public String getSensorno() {
        return sensorno;
    }

    public void setSensorno(String sensorno) {
        this.sensorno = sensorno;
    }

    public String getDatefield() {
        return datefield;
    }

    public void setDatefield(String datefield) {
        this.datefield = datefield;
    }

    public String toString(){
        return "datefield="+datefield+",dategap="+dategap+",sensorno="+sensorno;
    }
}
