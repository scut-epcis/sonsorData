package com.mkyong.customer.action;

import com.mkyong.customer.bo.CustomerBo;
import com.mkyong.customer.model.SensorParameter;
import com.mkyong.customer.service.SensorService;
import com.mkyong.customer.utils.DateTimeConvert;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bryan
 * @date 2016/1/11.
 */

public class SensorAction extends ActionSupport
        implements ModelDriven {

    // 传入查询参数
    SensorParameter sensorParameter = new SensorParameter();

    public Object getModel() {
        return sensorParameter;
    }

    SensorService sensorService;

    //DI via Spring
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    // 返回结果
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }




    public String execute() throws Exception {

        return SUCCESS;
    }


    /**
     *  查询传感器数据
     * @return
     */
    public String query(){

        System.out.println("execut query function");

        System.out.println("sensor parameter is "+sensorParameter);


        String sensorID = sensorParameter.getSensorno();
        String[] queryTime = new String[0];
        try {
            queryTime = convertQueryTime(sensorParameter.getDatefield(),sensorParameter.getDategap());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Map map = sensorService.querySonsor(sensorID,queryTime[0],queryTime[1]);

        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
        return "success";
    }

    /**
     * 将用户提交的时间初始化时间和时间周期
     * <br>
     *  转换为标准的开始时间和结束时间
     * @param date  初始时间值
     * @param dateType  时间跨度周期
     * @return  开始时间，结束时间
     */
    private String[] convertQueryTime(String date,String dateType) throws ParseException {

        String[] time = new String[2];
        if(dateType.equals("week")){

            time[0] = DateTimeConvert.getWeekFirst(date);
            time[1] = DateTimeConvert.getWeekLast(date);

        }else if(dateType.equals("month")){

            time[0] = DateTimeConvert.getMonthFirst(date);
            // 下月一号
            time[1] = DateTimeConvert.getNextDay(DateTimeConvert.getMonthLast(date));
        }else{
            time[0] = date;
            time[1] = DateTimeConvert.getNextDay(date);
        }

        return time;
    }
}
