package edu.scut.rjxy.utils;

/**
 * @author bryan
 * @date 2016/1/15.
 */

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public final class EnToCnUnits {


//    private final static Properties prop = new Properties();

    private static final Logger LOG = Logger.getLogger(EnToCnUnits.class);

    /**
     * @param key
     * @return
     */
    public static final String getPropertValue(String key) {

        String value = "";
        InputStream input = null;

        try {
            LOG.debug("---------------------------------------------------test");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Properties properties = new Properties();
            properties.load(new InputStreamReader(classLoader.getResourceAsStream("translate.properties"),"UTF-8"));

            value = properties.getProperty(key);
            LOG.debug("中文 " + value);
        } catch (IOException ex) {
            LOG.error(ex);
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error(e);
                    e.printStackTrace();
                }
            }
        }

        return value;
    }


}
