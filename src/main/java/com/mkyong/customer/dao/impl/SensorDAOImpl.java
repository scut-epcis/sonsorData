package com.mkyong.customer.dao.impl;

import com.mkyong.customer.dao.SensorDAO;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public class SensorDAOImpl extends HibernateDaoSupport implements SensorDAO {


    private static final String metaSql = "select channelNumber,propertyName,defaultUnits from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties, webLogger.dbo.sensor  " +
            "where sensortypeproperties.sensorType_idSensorType=sensor.idSensorType " +
            "and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties " +
            "and sensor.sensorSerialNo=? and deleted=0";

    private static final String dataSql = "select channel1_Data, channel2_Data,channel3_Data,channel4_Data,captureTime from webLogger.dbo.sensordata " +
            "where sensordata.Sensor_sensorSerialNo=? " +
            "and captureTime BETWEEN  convert(datetime,'?')  and convert(datetime,'?') ";




    public List querySensorMetaData(String sensorID) {

        SQLQuery query = this.getSession().createSQLQuery(metaSql);
        query.setString(0,sensorID);

        return query.list();
    }

    public List querySensorData(String sensorID, String beginDate, String endDate) {
        SQLQuery query = this.getSession().createSQLQuery(dataSql);

        query.setString(0,sensorID);
        query.setString(1,beginDate);
        query.setString(2,endDate);

//        List result = query.list();

        return query.list();
    }
}
