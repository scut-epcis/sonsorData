package edu.scut.rjxy.service.impl;

import edu.scut.rjxy.dao.SensorDAO;
import edu.scut.rjxy.model.SensorData;
import edu.scut.rjxy.model.SensorMeta;
import edu.scut.rjxy.service.SensorService;
import edu.scut.rjxy.utils.DateTimeConvert;
import edu.scut.rjxy.utils.EnToCnUnits;
import org.apache.log4j.Logger;

import java.util.Date;
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

    public Map querySonsor(String sensorID, String beginTime, String endTime) {

        Map<String, Object> map = new HashMap<String, Object>();
        List<Object[]> metaData = sensorDAO.querySensorMetaData(sensorID);
        int metaSum = metaData.size();
        LOG.debug("元数据数目：" + metaSum);
        map.put("metaSum", metaSum);
        if (metaSum == 0) {
            LOG.error("异常，元数据记录为空，metaSum = " + metaData);
            map.put("result0", "-");
            map.put("shaft", "-");
            return null;
        }

        SensorMeta[] sensorMetas = new SensorMeta[metaSum];
        int metaindex = 0;

        for (Object[] row : metaData) {
            String name_tmp = row[1] == null ? "-" : row[1].toString();
            String abbr_tmp = row[2] == null ? "-" : row[2].toString();
            map.put("unit" + metaindex + "name", convertToChinese(name_tmp));
            map.put("unit" + metaindex + "abbr", abbr_tmp);

            metaindex++;
        }

        List<Object[]> data = sensorDAO.querySensorData(metaSum,sensorID, beginTime, endTime);
        int dataSum = data.size();
        LOG.debug("记录数目：" + dataSum);
        map.put("dataSum", dataSum);
        String n0 = "";
        String n1 = "";
        String n2 = "";
        String n3 = "";
        String n4 = "";

        for (Object[] row : data) {
            if (metaSum == 1) {
                n0 += row[1].toString() + ",";
            } else if (metaSum == 2) {
                n0 += row[1].toString() + ",";
                n1 += row[2].toString() + ",";
            } else if (metaSum == 3) {
                n0 += row[1].toString() + ",";
                n1 += row[2].toString() + ",";
                n2 += row[3].toString() + ",";

            } else if (metaSum == 4) {
                n0 += row[1].toString() + ",";
                n1 += row[2].toString() + ",";
                n2 += row[3].toString() + ",";
                n3 += row[4].toString() + ",";
            }

            n4 += row[0].toString() + ",";
        }

        if (dataSum == 0) {
            n0 = "-";
            n1 = "-";
            n2 = "-";
            n3 = "-";
            n4 = "-";
        } else {
            n0 = n0.substring(0, n0.length() - 1);

            if (metaSum == 2) {
                n1 = n1.substring(0, n1.length() - 1);
                LOG.debug("result1=" + n1);
                map.put("result1", n1);
            } else if (metaSum == 3) {
                n1 = n1.substring(0, n1.length() - 1);
                n2 = n2.substring(0, n2.length() - 1);
                LOG.debug("result1=" + n1);
                map.put("result1", n1);
                LOG.debug("result2=" + n2);
                map.put("result2", n2);
            } else if (metaSum == 4) {
                n1 = n1.substring(0, n1.length() - 1);
                n2 = n2.substring(0, n2.length() - 1);
                n3 = n3.substring(0, n3.length() - 1);
                LOG.debug("result1=" + n1);
                map.put("result1", n1);
                LOG.debug("result2=" + n2);
                map.put("result2", n2);
                LOG.debug("result3=" + n3);
                map.put("result3", n3);
            }

            n4 = n4.substring(0, n4.length() - 1);

        }
        LOG.debug("result0=" + n0);
        LOG.debug("shaft=" + n4);

        map.put("result0", n0);
        map.put("shaft", n4);
        return map;
    }

    /**
     * 获取主页面动态导航（目录）
     *
     * @return
     */
    public Map getMenu() {

        List<Object[]> menus = sensorDAO.getMenu();
        String mainMenu = sensorDAO.getMainMenu();
        LOG.debug("主目录主干名称："+mainMenu);
        Map<String, Object> map = handleMenu(mainMenu, menus);

        return map;
    }

    public Map querySonsorHeadDate(String sensorID) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Object[]> menus = sensorDAO.getHeadDate(sensorID);

        if (menus == null || menus.size() == 0) {
            map.put("headDate", DateTimeConvert.formatDate(new Date().toString()));
            LOG.error("当前sensor 没有有效数据");
            return map;
        }
        String headDate = menus.get(0)[1] == null ? new Date().toString() : menus.get(0)[1].toString();
        LOG.debug("sensor对应的初始记录产生时间：" + DateTimeConvert.formatDate(headDate));
        map.put("headDate", DateTimeConvert.formatDate(headDate));
        return map;
    }

    public Map querySonsorTailDate(String sensorID) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Object[]> menus = sensorDAO.getTailDate(sensorID);

        if (menus == null || menus.size() == 0) {
            map.put("tailDate", DateTimeConvert.formatDate(new Date().toString()));
            LOG.error("当前sensor 没有有效数据");
            return map;
        }
        String tailDate = menus.get(0)[1] == null ? new Date().toString() : menus.get(0)[1].toString();
        LOG.debug("sensor对应的最新的记录产生时间：" + DateTimeConvert.formatDate(tailDate));
        map.put("tailDate", DateTimeConvert.formatDate(tailDate));
        return map;
    }

    public Map getStatictis(String sensorID, String beginTime, String endTime) {
        {
            Map<String, Object> map = new HashMap<String, Object>();

            // 元数据
            List<Object[]> metaData = sensorDAO.querySensorMetaData(sensorID);
            int metaSum = metaData.size();
            LOG.debug("元数据数目：" + metaSum);
            map.put("metaSum", metaSum);
            if (metaSum == 0) {
                LOG.error("异常，元数据记录为空，metaSum = " + metaData);
                map.put("result0", "-");
                map.put("channelNo", 0);
                return null;
            }

            int metaindex = 0;
            for (Object[] row : metaData) {
                String name_tmp = row[1] == null ? "-" : row[1].toString();
                String abbr_tmp = row[2] == null ? "-" : row[2].toString();
                map.put("unit" + metaindex + "name", convertToChinese(name_tmp));
                map.put("unit" + metaindex + "abbr", abbr_tmp);
                metaindex++;
            }

            // 数据
            List<Object[]> statictisSensorData = sensorDAO.statictisSensorData(sensorID, beginTime, endTime);
            int channelNo = sensorDAO.getSensorChennalNumber(sensorID);
            LOG.debug("统计记录数目：" + statictisSensorData.size() + ",数据集维度：" + channelNo);

            if (statictisSensorData == null || statictisSensorData.size() == 0) {
                map.put("statictisNo", 0);
                map.put("channelNo", channelNo);
                LOG.error("当前sensor 没有有效数据");
                return map;
            }

            String[] result = dealStaticResult(channelNo, statictisSensorData);
            for (int i = 0; (i * 3) < result.length - 1; i++) {
                map.put("static_" + i + "max", result[i + 1]);//static_0max
                map.put("static_" + i + "min", result[i + 2]);
                map.put("static_" + i + "avg", result[i + 3]);

            }
            map.put("statictisDate", result[0]);
            map.put("statictisNo", statictisSensorData.size());
            map.put("channelNo", channelNo);
            return map;
        }
    }

    private String[] dealStaticResult(int channelNo, List<Object[]> items) {
        String[] res = new String[channelNo * 3 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = "";
        }

        int itemIndex = 0;
        for (Object[] item : items) {
            itemIndex++;
            if (itemIndex >= items.size()) {
                res[0] += item[0].toString();

            } else {
                res[0] += item[0].toString() + ",";

            }
//            LOG.debug("统计时间刻度 ：" + res[0]);
            if (channelNo == 0) {
                continue;
            }
            for (int i = 1; i <= channelNo * 3; ) {
                if (itemIndex >= items.size()) {
                    res[i] += item[i].toString();
                    LOG.debug("最大值统计_：" + res[i]);
                    res[i + 1] += item[i + 1].toString();
                    LOG.debug("最小值统计_：" + res[i + 1]);
                    res[i + 2] += item[i + 2].toString().substring(0, 5);
                    LOG.debug("平均值统计_：" + res[i + 2]);
                    i += 3;
                } else {
                    res[i] += item[i].toString() + ",";
//                    LOG.debug("最大值统计：" + res[i]);
                    res[i + 1] += item[i + 1].toString() + ",";
//                    LOG.debug("最小值统计：" + res[i + 1]);
                    res[i + 2] += item[i + 2].toString().substring(0, 5) + ",";
//                    LOG.debug("平均值统计：" + res[i + 2]);
                    i += 3;
                }
            }
        }

        return res;
    }

    /**
     * 将关键字转换为中文
     *
     * @param key 英文关键字
     * @return 中文
     */
    private String convertToChinese(String key) {

        String value = EnToCnUnits.getPropertValue(key);
        LOG.debug("英译中 " + key + ":" + value);
        if (value == null || value == "") {
            return key;
        }
        return value;
    }

    /**
     * 将查询数据返回的list<Object[]>
     * <br>
     * 转换为map 格式的目录
     * <br>
     * 一级目录：二级目录，二级目录
     * @param mainMenu 主目录主干名称
     * @param menus 数据库记录
     * @return 被格式化的目录
     */
    private Map handleMenu(String mainMenu, List<Object[]> menus) {

        if (menus == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("mainMenu",mainMenu);

        Object[] previous = menus.get(0);

        String firstMenu = previous[1].toString() + ",";

        String sonsorName = previous[3].toString() + ",";
        String sonsorKey = previous[2].toString() + ",";

        int firstMenuIdx = 0;
        for (int i = 1; i < menus.size(); i++) {

            Object[] menu = menus.get(i);
            if (menu[0].equals(previous[0])) {
                // first second menu
                sonsorName += menu[3].toString() + ",";
                sonsorKey += menu[2].toString() + ",";

            } else {
                // next first menu
                firstMenu += menu[1].toString() + ",";
                map.put("second_" + firstMenuIdx + "name", sonsorName.substring(0, sonsorName.length() - 1));
                map.put("second_" + firstMenuIdx + "key", sonsorKey.substring(0, sonsorKey.length() - 1));
                firstMenuIdx++;

                sonsorName = menu[3].toString() + ",";
                sonsorKey = menu[2].toString() + ",";
                previous = menu;
            }

            if (i == menus.size() - 1) {
                // 末尾
//                firstMenu += menu[1].toString();
                map.put("second_" + firstMenuIdx + "name", sonsorName.substring(0, sonsorName.length() - 1));
                map.put("second_" + firstMenuIdx + "key", sonsorKey.substring(0, sonsorKey.length() - 1));
            }
        }

        map.put("firstMenu", firstMenu.substring(0, firstMenu.length() - 1));

        if (menus.size() == 1) {
            map.put("second_1name", sonsorName.substring(0, sonsorName.length() - 1));
            map.put("second_1key", sonsorKey.substring(0, sonsorKey.length() - 1));
        }

        LOG.debug("menu map(目录情况) :" + map);
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
