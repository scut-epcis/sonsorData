package edu.scut.rjxy.model;

/**
 * @author bryan
 * @date 2016/1/14.
 */

public class SensorData {

    private String channel1_data;
    private String channel2_data;
    private String channel3_data;
    private String channel4_data;
    private String time;


    public String getChannel1_data() {
        return channel1_data;
    }

    public void setChannel1_data(String channel1_data) {
        this.channel1_data = channel1_data;
    }

    public String getChannel2_data() {
        return channel2_data;
    }

    public void setChannel2_data(String channel2_data) {
        this.channel2_data = channel2_data;
    }

    public String getChannel3_data() {
        return channel3_data;
    }

    public void setChannel3_data(String channel3_data) {
        this.channel3_data = channel3_data;
    }

    public String getChannel4_data() {
        return channel4_data;
    }

    public void setChannel4_data(String channel4_data) {
        this.channel4_data = channel4_data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return "channel_1data="+channel1_data+",channel_2data="+channel2_data+
                ",channel_3data="+channel3_data+",channel_4data="+channel4_data+",time="+time;
    }
}
