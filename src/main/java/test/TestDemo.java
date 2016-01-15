package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public class TestDemo {


    public static void main(String[] args) throws ParseException {

        String n0 = "";
        System.out.println(n0.substring(0,n0.length()-1));
        String now = "2016-1-30";

        System.out.println(DateTimeConvert.getWeekFirst(now));
        System.out.println(DateTimeConvert.getWeekLast(now));
        System.out.println(DateTimeConvert.getMonthFirst(now));
        System.out.println(DateTimeConvert.getNextDay(DateTimeConvert.getMonthLast(now)));
        System.out.println(DateTimeConvert.getNextDay(now));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        //获取当前月第一天：
//        Calendar c = Calendar.getInstance();
//        Date date11=format.parse("2016-1-13");
//        c.setTime(date11);
//        c.add(Calendar.MONTH, 0);
//        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
//        String first = format.format(c.getTime());
//        System.out.println("===============first:"+first);
//
//        //获取当前月最后一天
//        Calendar ca = Calendar.getInstance();
//        ca.setTime(date11);
//        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//        String last = format.format(ca.getTime());
//        System.out.println("===============last:"+last);
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date11);
//        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        String calfirst = format.format(cal.getTime());
//
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(cal.getTime());
//        cal1.add(Calendar.DAY_OF_WEEK, 7);
//        String callast = format.format(cal1.getTime());
//
//
//        System.out.println("本周周一0点时间：" + calfirst);
//        System.out.println("本周周日24点时间：" + callast);


/////////////////////////////

//        System.out.println("1");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Date date=sdf.parse("");
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(date);
//        rightNow.add(Calendar.DAY_OF_MONTH,1);
//        Date date1=rightNow.getTime();
//        String reStr = sdf.format(date1);
//        System.out.println(reStr);
    }

//    // 获得本周一0点时间
//    public static Date getTimesWeekmorning() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        return cal.getTime();
//    }
//
//    // 获得本周日24点时间
//    public static Date getTimesWeeknight() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(getTimesWeekmorning());
//        cal.add(Calendar.DAY_OF_WEEK, 7);
//        return cal.getTime();
//    }
}
