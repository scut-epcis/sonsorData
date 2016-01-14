package com.mkyong.customer.service;

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

}
