package edu.scut.rjxy.dao.impl;

import edu.scut.rjxy.dao.SensorDAO;
import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public class SensorDAOImpl extends HibernateDaoSupport implements SensorDAO {


//    private static final String metaSql = "select channelNumber,propertyName,defaultUnits from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties, webLogger.dbo.sensor  " +
//            "where sensortypeproperties.sensorType_idSensorType=sensor.idSensorType " +
//            "and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties " +
//            "and sensor.sensorSerialNo= :sensorSerialNo and deleted=0";
//
//    private static String dataSql = "select channel1_Data, channel2_Data,channel3_Data,channel4_Data,captureTime from webLogger.dbo.sensordata " +
//            "where sensordata.Sensor_sensorSerialNo= ? " +
//            "and captureTime BETWEEN  convert(datetime,'?')  and convert(datetime,'?') ";




    public List querySensorMetaData(String sensorID) {

        final String metaSql = "select channelNumber,propertyName,defaultUnits from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties, webLogger.dbo.sensor  " +
                "where sensortypeproperties.sensorType_idSensorType=sensor.idSensorType " +
                "and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties " +
                "and sensor.sensorSerialNo= "+ sensorID+" and deleted=0  order by channelNumber ";
        SQLQuery query = this.getSession().createSQLQuery(metaSql)
                .addScalar("channelNumber", new LongType())
                .addScalar("propertyName", new StringType())
                .addScalar("defaultUnits", new StringType());
//        query.setParameter("sensorSerialNo", sensorID);
        return query.list();
    }

    public List querySensorData(String sensorID, String beginDate, String endDate) {


        final String sql = "select channel1_Data, channel2_Data,channel3_Data,channel4_Data,captureTime from webLogger.dbo.sensordata " +
                " where sensordata.Sensor_sensorSerialNo=" + sensorID +
                " and captureTime BETWEEN  convert(datetime,'"+ beginDate+"')  and convert(datetime,'"+endDate+"') ";
        SQLQuery query = this.getSession().createSQLQuery(sql);

        return query.list();
    }
}
