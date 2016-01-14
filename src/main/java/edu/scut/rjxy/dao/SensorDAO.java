package edu.scut.rjxy.dao;

import java.util.List;
import java.util.Map;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public interface SensorDAO {


    /**
     * 查询传感器在某个时段的数据
     * @param sensorID 传感器ID
     * @param beginDate 开始时间段
     * @param endDate   结束时间段
     * @return
     */
    public List querySensorData(String sensorID,String beginDate,String endDate);

    /**
     * 查询传感器对应的元数据
     * <br>
     * 包括：数据集个数，每个数据集的单位名称，每个数据集的单位缩写
     * @param sensorID
     * @return
     */
    public List querySensorMetaData(String sensorID);

    /**
     * 获取主页动态导航（目录）
     * @return
     */
    public List getMenu();
}
