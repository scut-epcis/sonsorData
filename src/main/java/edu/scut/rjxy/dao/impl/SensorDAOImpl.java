package edu.scut.rjxy.dao.impl;

import edu.scut.rjxy.dao.SensorDAO;
import org.apache.log4j.Logger;
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


    public static final Logger LOG = Logger.getLogger(SensorDAOImpl.class);

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

    public List getMenu() {

        final String menuSql = "select grid.idGrid,grid.gridName,sensor.sensorSerialNo,sensor.sensorName " +
                " from webLogger.dbo.grid,webLogger.dbo.gridsensor,webLogger.dbo.sensor " +
                " where gridsensor.deleted=0 and grid.idGrid = gridsensor.idGrid " +
                " and gridsensor.sensorSerialNo = sensor.sensorSerialNo and sensor.deleted=0 order by grid.idGrid ";
        SQLQuery query = this.getSession().createSQLQuery(menuSql)
                .addScalar("idGrid", new LongType())
                .addScalar("gridName", new StringType())
                .addScalar("sensorSerialNo", new LongType())
                .addScalar("sensorName", new StringType());
        return query.list();
    }

    public String getMainMenu() {

        final String mainMenuSql = "select siteName from webLogger.dbo.sites ";
        SQLQuery query = this.getSession().createSQLQuery(mainMenuSql).addScalar("siteName", new StringType());
        final String str = query.list()==null ?"主目录":query.list().get(0)==null?"主目录":query.list().get(0).toString();
        return str;
    }

    public List<Object[]> getHeadDate(String sensorID) {

        final String sensorDataHeadDate = "select top 1 channel1_Data,captureTime from webLogger.dbo.sensordata " +
                "where sensordata.Sensor_sensorSerialNo= "+ sensorID +" ORDER  BY captureTime ASC ";
        SQLQuery query = this.getSession().createSQLQuery(sensorDataHeadDate);
        return query.list();
    }

    public List<Object[]> getTailDate(String sensorID) {

        final String sensorDataTailDate = "select top 1 channel1_Data,captureTime from webLogger.dbo.sensordata " +
                "where sensordata.Sensor_sensorSerialNo= "+ sensorID +" ORDER  BY captureTime DESC ";;
        SQLQuery query = this.getSession().createSQLQuery(sensorDataTailDate);
        return query.list();
    }

    public List querySensorData(String sensorID, String beginDate, String endDate) {

        final String sql = "select channel1_Data, channel2_Data,channel3_Data,channel4_Data,captureTime from webLogger.dbo.sensordata " +
                " where sensordata.Sensor_sensorSerialNo=" + sensorID +
                " and captureTime BETWEEN  convert(datetime,'"+ beginDate+"')  and convert(datetime,'"+endDate+"') ";
        SQLQuery query = this.getSession().createSQLQuery(sql);

        return query.list();
    }

    public List querySensorData(int dlNo, String sensorID, String beginDate, String endDate) {

        SQLQuery query = null;
        if (dlNo == 1) {
            final String sql = "select captureTime,channel1_Data from webLogger.dbo.sensordata " +
                    " where sensordata.Sensor_sensorSerialNo=" + sensorID +
                    " and captureTime BETWEEN  convert(datetime,'" + beginDate + "')  and convert(datetime,'" + endDate + "') ";
            query= this.getSession().createSQLQuery(sql);
        }else if(dlNo == 2){
            final String sql = "select captureTime, channel1_Data, channel2_Data from webLogger.dbo.sensordata " +
                    " where sensordata.Sensor_sensorSerialNo=" + sensorID +
                    " and captureTime BETWEEN  convert(datetime,'" + beginDate + "')  and convert(datetime,'" + endDate + "') ";
            query= this.getSession().createSQLQuery(sql);
        }else if(dlNo == 3){
            final String sql = "select captureTime, channel1_Data, channel2_Data,channel3_Data from webLogger.dbo.sensordata " +
                    " where sensordata.Sensor_sensorSerialNo=" + sensorID +
                    " and captureTime BETWEEN  convert(datetime,'" + beginDate + "')  and convert(datetime,'" + endDate + "') ";
            query= this.getSession().createSQLQuery(sql);
        }else{
            final String sql = "select captureTime, channel1_Data, channel2_Data,channel3_Data,channel4_Data from webLogger.dbo.sensordata " +
                    " where sensordata.Sensor_sensorSerialNo=" + sensorID +
                    " and captureTime BETWEEN  convert(datetime,'" + beginDate + "')  and convert(datetime,'" + endDate + "') ";
            query= this.getSession().createSQLQuery(sql);
        }

        return query.list();
    }


    public List statictisSensorData(String sensorID, String beginDate, String endDate){

        final String statictisSql = "select  CONVERT(VARCHAR(10), captureTime, 112)  AS dataclass ," +
                "MAX (channel1_Data) AS  maxdata ,min(channel1_Data) AS  minData , avg(channel1_Data)  AS avgdata , " +
                "MAX (channel2_Data) AS  maxdata2 ,min(channel2_Data) AS  minData2 , avg(channel2_Data)  AS avgdata2 , " +
                "MAX (channel3_Data) AS  maxdata3 ,min(channel3_Data) AS  minData3 , avg(channel3_Data)  AS avgdata3 , " +
                "MAX (channel4_Data) AS  maxdata4 ,min(channel4_Data) AS  minData4 , avg(channel4_Data)  AS avgdata4  " +
                "  from webLogger.dbo.sensordata " +
                " where sensordata.Sensor_sensorSerialNo= " + sensorID +
                " and captureTime BETWEEN  convert(datetime,'"+beginDate +"')  and convert(datetime,'"+endDate+"') " +
                " GROUP BY CONVERT(VARCHAR(10), captureTime, 112) ORDER  BY dataclass ";

        SQLQuery query = this.getSession().createSQLQuery(statictisSql);

        return query.list();
    }

    public int getSensorChennalNumber(String sensorID) {

        final String chennalNumber =  "select max(sensortypeproperties.channelNumber) AS  chennalNumber " +
                "from webLogger.dbo.sensortypeproperties,webLogger.dbo.sensor " +
                "where sensor.sensorSerialNo="+ sensorID + " and sensortypeproperties.sensorType_idSensorType=sensor.idSensorType ";
        SQLQuery query = this.getSession().createSQLQuery(chennalNumber).addScalar("chennalNumber", new LongType());
        List list  = query.list()==null?null:query.list();
        Object result = list == null?null:list.get(0);
        int channelNo = 0;
        try{
            channelNo = Integer.parseInt( result==null?"0":result.toString());
        }catch (Exception e){

            LOG.error("获取的channel数据不能转为整形，"+e);
            e.printStackTrace();
            return 0;
        }
        return channelNo ;
    }
}
