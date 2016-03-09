package test;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bryan
 * @date 2016/1/7.
 */


public class Test {



    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;

        try {

            // happy\\happy:1435;DatabaseName=Myschool"
            //jdbc:sqlserver:// localhost:1433;databaseName=mytest
            // jdbc:sqlserver://222.201.139.21:1435;databaseName=webtest1
//            String dbURL = "jdbc:mysql://localhost:3306/weblogger";
            String dbURL = "jdbc:sqlserver://222.201.139.21:1435;databaseName=weblogger";
            String user = "sa";
            String pass = "Test123";
            conn = DriverManager.getConnection(dbURL, user, pass);
//            if (conn != null) {
//                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//
//            }

            stmt = conn.createStatement();

//            String sql = "CREATE TABLE  mycustomer( " +
//                    " NAME varchar(45) NOT NULL, " +
//                    " city varchar(45) NOT NULL " +
//                    " )";
//            System.out.println(sql);
//            stmt.executeUpdate(sql);
//            System.out.println("create table in given database...");


//            String sqlinsert = "insert into mycustomer(NAME,city) values('zhangSan', 'scut123')";
//            stmt.executeUpdate(sqlinsert);
//            System.out.println("insert into table in given database...");

            String querySql = "select sensorSerialNo,sensorName,physical_ID,sensorStatus,idSensorType,deleted  from webLogger.dbo.sensor " +
                    "where sensor.sensorSerialNo=2 and deleted =0";

            String querySql1 = "select top 2 * from webLogger.dbo.sensordata " +
                    "where sensordata.Sensor_sensorSerialNo=2 " +
                    "and captureTime BETWEEN  convert(datetime,'2015-10-06')  and convert(datetime,'2015-10-07') ";

            String querySql2 = "select * from webLogger.dbo.sensortypeproperties where sensortypeproperties.sensorType_idSensorType=108";

            String querySql2_1 = "select max(sensortypeproperties.channelNumber) AS  chennalNumber " +
                    "from webLogger.dbo.sensortypeproperties,webLogger.dbo.sensor " +
                    "where sensor.sensorSerialNo=2 and sensortypeproperties.sensorType_idSensorType=sensor.idSensorType ";

            String querySql3 = "select * from webLogger.dbo.sensorproperties where sensorproperties.idSensorProperties in(1,2)";

            String querySql4 = "select  channelNumber,propertyName,defaultUnits " +
                    "from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties " +
                    "where  sensortypeproperties.sensorType_idSensorType=11 " +
                    "and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties";

            String querySql5 = "select channelNumber,propertyName,defaultUnits from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties, webLogger.dbo.sensor  " +
                    "where sensortypeproperties.sensorType_idSensorType=sensor.idSensorType " +
                    "and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties " +
                    "and sensor.sensorSerialNo=2 and deleted=0";

            String querySql6 = "select channel1_Data, channel2_Data,channel3_Data,channel4_Data,captureTime from webLogger.dbo.sensordata " +
                    "where sensordata.Sensor_sensorSerialNo=2 " +
                    "and captureTime BETWEEN  convert(datetime,'2015-10-06')  and convert(datetime,'2015-10-07') ";


            String menuSql0 = "select * from webLogger.dbo.sites";
            String menuSql = "select * from webLogger.dbo.grid";
            String menuSql1 = "select * from webLogger.dbo.gridsensor ";
            String menuSql2 = "select * from webLogger.dbo.sensor";
            String menuSql3 = "select grid.idGrid,grid.gridName,sensor.sensorSerialNo,sensor.sensorName " +
                    " from webLogger.dbo.grid,webLogger.dbo.gridsensor,webLogger.dbo.sensor " +
                    " where gridsensor.deleted=0 and grid.idGrid = gridsensor.idGrid " +
                    " and gridsensor.sensorSerialNo = sensor.sensorSerialNo and sensor.deleted=0";
            String menuSql4 = "select grid.idGrid,grid.gridName,sensor.sensorSerialNo,sensor.sensorName " +
                    " from webLogger.dbo.grid,webLogger.dbo.gridsensor,webLogger.dbo.sensor " +
                    " where gridsensor.deleted=0 and grid.idGrid = gridsensor.idGrid " +
                    " and gridsensor.sensorSerialNo = sensor.sensorSerialNo and sensor.deleted=0  order by grid.idGrid ";

            ///--------------units
            String unitsSql = " select * from webLogger.dbo.sensorproperties ";

            ///------------------------test
            String testsql1 = "select channelNumber,propertyName,defaultUnits from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties, webLogger.dbo.sensor  where sensortypeproperties.sensorType_idSensorType=sensor.idSensorType and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties and sensor.sensorSerialNo= 1 and deleted=0";
            String testsql2 = "select channelNumber,propertyName,defaultUnits from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties, webLogger.dbo.sensor  where sensortypeproperties.sensorType_idSensorType=sensor.idSensorType and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties and sensor.sensorSerialNo= 5 and deleted=0 order by channelNumber";
            String testSql3 = "select channelNumber,propertyName,defaultUnits from webLogger.dbo.sensorproperties,webLogger.dbo.sensortypeproperties, webLogger.dbo.sensor  where sensortypeproperties.sensorType_idSensorType=sensor.idSensorType and sensorproperties.idSensorProperties = sensorProperties_idSensorProperties and sensor.sensorSerialNo= 8 and deleted=0  order by channelNumber";


            /////--------------------Sensor Data product Date
            String headDate = "select top 1 channel1_Data, channel2_Data,channel3_Data,channel4_Data,captureTime from webLogger.dbo.sensordata " +
                    "where sensordata.Sensor_sensorSerialNo=5 " +" ORDER  BY captureTime ASC ";
            String tailDate = "select top 1 channel1_Data, channel2_Data,channel3_Data,channel4_Data,captureTime from webLogger.dbo.sensordata " +
                    "where sensordata.Sensor_sensorSerialNo=5 " +" ORDER  BY captureTime desc ";


            ///---------------------sql server sql statistics
            String statictisSql1 = "select " +
                    "MAX (channel1_Data) AS  maxdata ,min(channel1_Data) AS  minData , avg(channel1_Data)  AS avgdata , " +
                    "MAX (channel2_Data) AS  maxdata2 ,min(channel2_Data) AS  minData2 , avg(channel2_Data)  AS avgdata2 , " +
                    "MAX (channel3_Data) AS  maxdata3 ,min(channel3_Data) AS  minData3 , avg(channel3_Data)  AS avgdata3 , " +
                    "MAX (channel4_Data) AS  maxdata4 ,min(channel4_Data) AS  minData4 , avg(channel4_Data)  AS avgdata4 , " +
                    " CONVERT(VARCHAR(10), captureTime, 112)  AS dataclass  " +
                    "  from webLogger.dbo.sensordata " +
                    " where sensordata.Sensor_sensorSerialNo=2 " +
                    " and captureTime BETWEEN  convert(datetime,'2015-10-06')  and convert(datetime,'2015-10-07') " +
                    " GROUP BY CONVERT(VARCHAR(10), captureTime, 112) ORDER  BY dataclass ";

            // CONVERT(VARCHAR(10), fmdate(), 101)

            ResultSet rs=stmt.executeQuery(querySql2_1);

            ResultSetMetaData rsm=rs.getMetaData();
            System.out.println("t_student表有几个字段？"+rsm.getColumnCount());

            int colNum=rsm.getColumnCount();
            String[] colName=new String[colNum]; //字段名
            System.out.println("------------------------");
            System.out.println("遍历列名");
            for(int i=1;i<=colNum;i++)
            {
                colName[i-1]=rsm.getColumnName(i);

            }
            System.out.println(Arrays.asList(colName));
            System.out.println("------------------------");
            System.out.println("遍历数据");

            while(rs.next())
            {

                for(int i=1;i<=colNum;i++)
                {
                    System.out.print(rs.getString(colName[i - 1]) + ",");
                }
                System.out.println();
            }



        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
