package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author bryan
 * @date 2016/1/13.
 */

public final class DateTimeConvert {

    private static  final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static final String getWeekFirst(String now) throws ParseException {

        Calendar cal = Calendar.getInstance();
        Date date11=format.parse(now);
        cal.setTime(date11);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String calfirst = format.format(cal.getTime());
//        System.out.println("本周周一0点时间：" + calfirst);
        return calfirst;
    }

    public static final String getWeekLast(String now) throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date date11=format.parse(now);
        cal.setTime(date11);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(cal.getTime());
        cal1.add(Calendar.DAY_OF_WEEK, 7);
        String callast = format.format(cal1.getTime());
//        System.out.println("本周周日24点时间：" + callast);
        return callast;
    }
    public static final String getMonthFirst(String now) throws ParseException {

        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        Date date11=format.parse(now);
        c.setTime(date11);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
//        System.out.println("===============first:"+first);
        return first;
    }

    public static final String getMonthLast(String now) throws ParseException {
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        Date date11=format.parse(now);
        ca.setTime(date11);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
//        System.out.println("===============last:"+last);
        return last;
    }
    public static final String getNextDay(String now) throws ParseException {

        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(format.parse(now));
        rightNow.add(Calendar.DAY_OF_MONTH,1);
        String nextDate = format.format(rightNow.getTime());
        return nextDate;
    }
}
