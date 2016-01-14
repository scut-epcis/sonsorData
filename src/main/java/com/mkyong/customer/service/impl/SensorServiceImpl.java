package com.mkyong.customer.service.impl;

import com.mkyong.customer.dao.SensorDAO;
import com.mkyong.customer.service.SensorService;

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

        List<String> metaData = sensorDAO.querySensorMetaData(sensorID);

        List data = sensorDAO.querySensorData(sensorID, beginTime, endTime);

        Map<String, Object> map = new HashMap<String, Object>();

        String n0 = "0";
        String n1 = "0";
        String n2 = "0";
        for(int i=1;i<7;i++){
            n0+=","+"1"+i;
            n1+=","+"2"+i;
            n2+=","+i;
        }
        map.put("result1", n0);
        map.put("result2", n1);
        map.put("shaft",n2);
        map.put("util1", ".c");
        map.put("util2","ml");
        map.put("util1name","温度");
        map.put("util2name","湿度");
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
