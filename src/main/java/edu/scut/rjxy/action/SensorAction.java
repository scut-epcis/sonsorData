package edu.scut.rjxy.action;

import edu.scut.rjxy.model.SensorParameter;
import edu.scut.rjxy.service.SensorService;
import edu.scut.rjxy.utils.DateTimeConvert;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bryan
 * @date 2016/1/11.
 */

public class SensorAction extends ActionSupport
        implements ModelDriven {

    private static final Logger LOG = Logger.getLogger(SensorAction.class);

    // 传入查询参数
    SensorParameter sensorParameter = new SensorParameter();

    public Object getModel() {
        return sensorParameter;
    }

//    SensorService sensorService;
//
//    //DI via Spring
//    public void setSensorService(SensorService sensorService) {
//        this.sensorService = sensorService;
//    }

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
     * 查询传感器数据
     *
     * @return
     */
    public String query() {

        LOG.debug("execut query function");
        LOG.debug("sensor parameter is " + sensorParameter);
        if(isNullSenPara(sensorParameter)){
            result = new JSONObject().put("error", "输入参数有误!").toString();
            return "false";
        }
        String sensorID = sensorParameter.getSensorno();
        String[] queryTime = new String[2];
        try {
            queryTime = convertQueryTime(sensorParameter.getDatefield(), sensorParameter.getDategap());
        } catch (ParseException e) {
            LOG.error("获取开始日期有误" + e);
            e.printStackTrace();
        }
        LOG.debug("查询时间段：开始时间："+queryTime[0]+"，结束时间"+queryTime[1]);
        Map map = new HashMap();
        map.put("metaSum","1");
        map.put("dataSum","6");
        map.put("unit0name","time");
        map.put("unit0abbr","t");
        map.put("result0","2014-7-30 11:27:29,2014-7-30 17:21:02,2014-7-31 08:17:11,2014-7-31 08:17:55,2014-7-31 08:32:08,2014-7-31 08:32:41");
        map.put("shaft","1,2,3,4,5,6");

        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
        return "success";
    }





    /**
     * 判断 sensor 参数是否为空，
     * <br>
     *     传输查询起始时间，传感器id,时间间隔都不能为空
     * @param sensorParameter
     * @return
     */
    private boolean isNullSenPara(SensorParameter sensorParameter){

        if(sensorParameter.getDatefield() == null ||sensorParameter.getDatefield().equals("")){
            return true;
        }
        if(sensorParameter.getDategap() == null||sensorParameter.getDategap().equals("")){
            return true;
        }
        if(sensorParameter.getSensorno() == null||sensorParameter.getSensorno().equals("")){
            return true;
        }
        return false;
    }

    /**
     * 判断 sensor 查询参数是否为空
     * <br>
     *     传感器ID不能为空
     * @param sensorParameter
     * @return
     */
    private boolean isNullPartSenPara(SensorParameter sensorParameter){

        if(sensorParameter.getSensorno() == null||sensorParameter.getSensorno().equals("")){
            return true;
        }
        return false;
    }
    /**
     * 获取sensor项目的导航（目录）
     *
     * @return
     */
    public String getMenu() {

        LOG.debug("execut getMenu function");
        LOG.debug("no parameter");

//        Map map = sensorService.querySonsor(sensorID,queryTime[0],queryTime[1]);

        Map map = new HashMap() ;//sensorService.getMenu();
        map.put("firstMenu","nihao");
        map.put("second_0name","lala");
        map.put("second_0key","1");
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面

        return "success";
    }

    /**
     * 将用户提交的时间初始化时间和时间周期
     * <br>
     * 转换为标准的开始时间和结束时间
     *
     * @param date     初始时间值
     * @param dateType 时间跨度周期
     * @return 开始时间，结束时间
     */
    private String[] convertQueryTime(String date, String dateType) throws ParseException {

        date += " 00:00:00";
        String[] time = new String[2];
        if (dateType.equals("week")) {

//            time[0] = DateTimeConvert.getWeekFirst(date);
//            time[1] = DateTimeConvert.getWeekLast(date);
            time[0] = date;
            time[1] = DateTimeConvert.getNextSevenDay(date);
        } else if (dateType.equals("month")) {

//            time[0] = DateTimeConvert.getMonthFirst(date);
//            // 下月一号
//            time[1] = DateTimeConvert.getNextDay(DateTimeConvert.getMonthLast(date));

            time[0] = date;
            time[1] = DateTimeConvert.getNextThirtyDay(date);
        } else {
            time[0] = date;
            time[1] = DateTimeConvert.getNextDay(date);

        }
        LOG.debug("date:"+time[0]+",nextDate="+time[1]);
        return time;
    }
}
