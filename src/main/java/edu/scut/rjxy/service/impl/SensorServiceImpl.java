package edu.scut.rjxy.service.impl;

import edu.scut.rjxy.dao.SensorDAO;
import edu.scut.rjxy.model.SensorData;
import edu.scut.rjxy.model.SensorMeta;
import edu.scut.rjxy.service.SensorService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public class SensorServiceImpl implements SensorService {

    public static final Logger LOG = Logger.getLogger(SensorServiceImpl.class);

    SensorDAO sensorDAO;

    //DI via Spring
    public void setSensorDAO(SensorDAO sensorDAO) {
        this.sensorDAO = sensorDAO;
    }

    public Map querySonsor(String sensorID,String beginTime,String endTime) {

        Map<String, Object> map = new HashMap<String, Object>();
        List<Object[]> metaData = sensorDAO.querySensorMetaData(sensorID);
        LOG.info(metaData.size());
        int metaSum = metaData.size();

        SensorMeta[] sensorMetas = new SensorMeta[metaSum];
        int metaindex = 0;
        for(Object[] row : metaData) {
            sensorMetas[metaindex] = new SensorMeta();
            sensorMetas[metaindex].setChannelID(row[0].toString());
            sensorMetas[metaindex].setName(row[1].toString());
            sensorMetas[metaindex].setDefaultUnit(row[2].toString());
            LOG.debug(sensorMetas[metaindex]);
            metaindex++;
        }
        map.put("util1", sensorMetas[0].getDefaultUnit());
        map.put("util2",sensorMetas[1].getDefaultUnit());
        map.put("util1name",sensorMetas[0].getName());
        map.put("util2name",sensorMetas[1].getName());

        List<Object[]> data = sensorDAO.querySensorData(sensorID, beginTime, endTime);
        LOG.info(data.size());
        int dataSum = data.size();
        String n0 = "";
        String n1 = "";
        String n2 = "";
        String n3 = "";
        String n4 = "";

        for(Object[] row:data){
//            SensorData sensorData = new SensorData();
            if(metaSum==1){
//                sensorData.setChannel1_data(row[0].toString());
                n0+=row[0].toString()+",";
            }else if(metaSum == 2){
//                sensorData.setChannel2_data(row[1].toString());
//                sensorData.setChannel1_data(row[0].toString());
                n0+=row[0].toString()+",";
                n1+=row[1].toString()+",";
            }else if(metaSum == 3){
//                sensorData.setChannel1_data(row[0].toString());
//                sensorData.setChannel2_data(row[1].toString());
//                sensorData.setChannel3_data(row[2].toString());
                n0+=row[0].toString()+",";
                n1+=row[1].toString()+",";
                n2+=row[2].toString()+",";

            }else if(metaSum == 4){
//                sensorData.setChannel1_data(row[0].toString());
//                sensorData.setChannel2_data(row[1].toString());
//                sensorData.setChannel3_data(row[2].toString());
//                sensorData.setChannel4_data(row[3].toString());
                n0+=row[0].toString()+",";
                n1+=row[1].toString()+",";
                n2+=row[2].toString()+",";
                n3+=row[3].toString()+",";
            }

//            sensorData.setTime(row[4].toString());
            n4+=row[4].toString()+",";
//            LOG.info(sensorData);
        }


        n0 = n0.substring(0,n0.length()-1);
        n1 = n1.substring(0,n1.length()-1);
        n4 = n4.substring(0,n4.length()-1);
        map.put("result1", n0);
        map.put("result2", n1);
        map.put("shaft",n4);

        return  map;
    }

    public Map getMenu() {

        List<Object[]> menus = sensorDAO.getMenu();
        Map<String, Object> map = handleMenu(menus);

        return map;
    }

    /**
     * 将查询数据返回的list<Object[]>
     * <br>
     *   转换为map 格式的目录
     *   <br>
     *   一级目录：二级目录，二级目录
     * @param menus 数据库记录
     * @return 被格式化的目录
     */
    private Map handleMenu(List<Object[]> menus){

        if(menus == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        Object[] previous = menus.get(0);

        String firstMenu = previous[1].toString()+",";

        String sonsorName = previous[3].toString()+",";
        String sonsorKey = previous[2].toString()+",";

        int firstMenuIdx = 0;
        for(int i = 1;i<menus.size();i++){

            Object[] menu = menus.get(i);
            if(menu[0].equals(previous[0])){
                // first second menu
                sonsorName+=menu[3].toString()+",";
                sonsorKey+=menu[2].toString()+",";

            }else{
                // next first menu
                firstMenu += menu[1].toString()+",";
                map.put("second_"+firstMenuIdx+"name",sonsorName.substring(0,sonsorName.length()-1));
                map.put("second_"+firstMenuIdx+"key",sonsorKey.substring(0,sonsorKey.length()-1));
                firstMenuIdx++;

                sonsorName =menu[3].toString()+",";
                sonsorKey = menu[2].toString()+",";
                previous = menu;
            }

            if(i == menus.size()-1){
                // 末尾
                firstMenu += menu[1].toString();
                map.put("second_"+firstMenuIdx+"name",sonsorName.substring(0,sonsorName.length()-1));
                map.put("second_"+firstMenuIdx+"key",sonsorKey.substring(0,sonsorKey.length()-1));
            }
        }

        map.put("firstMenu",firstMenu);

        if(menus.size() == 1){
            map.put("second_1name",sonsorName.substring(0,sonsorName.length()-1));
            map.put("second_1key",sonsorKey.substring(0,sonsorKey.length()-1));
        }

        LOG.debug("menu map :"+map);
        return map;
    }

//    private final String[] convertMeta(List<String> metas){
//        if(metas == null ||metas.size() == 0){
//            return null;
//        }
//
//        String[] metaData = new String[2*metas.size()];
//        for(String meta:metas){
//
//        }
//
//
//        return metaData;
//    }
}
