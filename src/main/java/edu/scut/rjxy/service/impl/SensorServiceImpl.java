package edu.scut.rjxy.service.impl;

import edu.scut.rjxy.dao.SensorDAO;
import edu.scut.rjxy.model.SensorData;
import edu.scut.rjxy.model.SensorMeta;
import edu.scut.rjxy.service.SensorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public class SensorServiceImpl implements SensorService {


    SensorDAO sensorDAO;

    //DI via Spring
    public void setSensorDAO(SensorDAO sensorDAO) {
        this.sensorDAO = sensorDAO;
    }

    public Map querySonsor(String sensorID,String beginTime,String endTime) {

        Map<String, Object> map = new HashMap<String, Object>();
        List<Object[]> metaData = sensorDAO.querySensorMetaData(sensorID);
        System.out.println(metaData.size());
        int metaSum = metaData.size();

        SensorMeta[] sensorMetas = new SensorMeta[metaSum];
        int metaindex = 0;
        for(Object[] row : metaData) {
            sensorMetas[metaindex] = new SensorMeta();
            sensorMetas[metaindex].setChannelID(row[0].toString());
            sensorMetas[metaindex].setName(row[1].toString());
            sensorMetas[metaindex].setDefaultUnit(row[2].toString());
            System.out.println(sensorMetas[metaindex]);
            metaindex++;
        }
        map.put("util1", sensorMetas[0].getDefaultUnit());
        map.put("util2",sensorMetas[1].getDefaultUnit());
        map.put("util1name",sensorMetas[0].getName());
        map.put("util2name",sensorMetas[1].getName());

        List<Object[]> data = sensorDAO.querySensorData(sensorID, beginTime, endTime);
        System.out.println(data.size());
        int dataSum = data.size();
        String n0 = "";
        String n1 = "";
        String n2 = "";
        String n3 = "";
        String n4 = "";

        for(Object[] row:data){
            SensorData sensorData = new SensorData();
            if(metaSum==1){
                sensorData.setChannel1_data(row[0].toString());
                n0+=","+row[0].toString();
            }else if(metaSum == 2){
                sensorData.setChannel1_data(row[0].toString());
                sensorData.setChannel2_data(row[1].toString());
                n0+=","+row[0].toString();
                n1+=","+row[1].toString();
            }else if(metaSum == 3){
                sensorData.setChannel1_data(row[0].toString());
                sensorData.setChannel2_data(row[1].toString());
                sensorData.setChannel3_data(row[2].toString());
                n0+=","+row[0].toString();
                n1+=","+row[1].toString();
                n2+=","+row[2].toString();

            }else if(metaSum == 4){
                sensorData.setChannel1_data(row[0].toString());
                sensorData.setChannel2_data(row[1].toString());
                sensorData.setChannel3_data(row[2].toString());
                sensorData.setChannel4_data(row[3].toString());
                n0+=","+row[0].toString();
                n1+=","+row[1].toString();
                n2+=","+row[2].toString();
                n3+=","+row[3].toString();
            }

            sensorData.setTime(row[4].toString());
            n4+=","+row[4].toString();
            System.out.println(sensorData);
        }


        map.put("result1", n0);
        map.put("result2", n1);
        map.put("shaft",n4);

        return  map;
    }

    private final String[] convertMeta(List<String> metas){
        if(metas == null ||metas.size() == 0){
            return null;
        }

        String[] metaData = new String[2*metas.size()];
        for(String meta:metas){

        }


        return metaData;
    }
}
