package edu.scut.rjxy.service;

import java.util.Map;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public interface SensorService {

    /**
     * 根据传感器ID和时间区间查询传感器数据
     * @param sensorID 传感器ID
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return  传感器采集数据和单位
     */
    public Map querySonsor(String sensorID,String beginTime,String endTime);

    /**
     * 获取主页面目录
     * @return 目录结构
     */
    public Map getMenu();

    /**
     * 获取指定sensor，初始数据的时间
     * @param sensorID
     * @return
     */
    public Map querySonsorHeadDate(String sensorID);

    /**
     * 获取指定sensor，最新数据的时间
     * @param sensorID
     * @return
     */
    public Map querySonsorTailDate(String sensorID);

    /**
     * 查询指定sensor在指定时间内的统计数据
     * <br>
     *     最大值，最小值，平均值
     * @param sensorID sensorID
     * @param beginTime 开始统计时间 （天） 格式：yyyy-MM-dd
     * @param endTime 结束时间 （天） 格式：yyyy-MM-dd
     * @return map格式结果
     */
    public Map getStatictis(String sensorID,String beginTime,String endTime);

}
