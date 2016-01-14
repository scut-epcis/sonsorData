package edu.scut.rjxy.model;

/**
 * @author bryan
 * @date 2016/1/14.
 */

public class SensorMeta {
    private String channelID;
    private String name;
    private String defaultUnit;

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public String toString(){
        return "channelID="+channelID + ",name="+name + ",defaultUnit="+defaultUnit;
    }
}
