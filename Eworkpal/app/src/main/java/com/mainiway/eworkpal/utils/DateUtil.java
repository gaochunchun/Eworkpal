package com.mainiway.eworkpal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by gao_chun on 2016/11/27.
 */
public class DateUtil {

    private DateUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static int daysOfMonth = 0;      //某月的天数
    private static int dayOfWeek = 0;        //具体某一天是星期几

    public static String defaultDatePattern = "yyyy-MM-dd";
    public static String defaultTimePattern = "HH:mm:ss";
    public static String defaultDatePattern1 = "yyyy-MM-dd HH:mm:ss";
    public static String defaultDatePattern2 = "yyyyMMdd";
    public static String defaultDatePattern3 = "yyyyMMddHHmmss";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * yyyy-MM-dd
     * 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        Date today = new Date();
        return format(today);
    }
    /**
     * HH:mm:ss
     * 返回预设Format的当前时间字符串
     */
    public static String getTime() {
        Date today = new Date();
        return formatTime(today);
    }
    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getAllToday() {
        Date today = new Date();
        return formatAllDate(today);
    }

    /**
     * 获取当前时间
     * @return 返回格式 yyyyMMdd
     */
    public static String getNotFlagToday() {
        Date today = new Date();
        return formatNotFlagDate(today);
    }

    /**
     * 获取当前时间
     * @return 返回格式 yyyyMMddHHmmss
     */
    public static String getToDayTimestamp(){
        Date today = new Date();
        return formatgetToDayTimestampDate(today);
    }


    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }
    public static String formatTime(Date date) {
        return format(date,defaultTimePattern);
    }

    public static String formatAllDate(Date date) {
        return format(date, defaultDatePattern1);
    }

    public static String formatNotFlagDate(Date date) {
        return format(date, defaultDatePattern2);
    }

    public static String formatgetToDayTimestampDate(Date date) {
        return format(date, defaultDatePattern3);
    }
    /**
     * 格式化日期为
     * yyyyMMddHHmmss
     * @param strDate
     * @return
     * @throws Exception
     */
    public static String formatToDayTimestamp(String strDate) throws Exception{
        Date date = parse(strDate, defaultDatePattern1);
        return format(date, defaultDatePattern3);
    }

    /**
     * 格式化日期为
     * yyyy-MM-dd
     * @param strDate yyyy-MM-dd HH:mm:ss
     * @return
     * @throws Exception
     */
    public static String formatToDayStirng(String strDate) throws Exception{
        Date date = parse(strDate, defaultDatePattern1);
        return format(date, defaultDatePattern2);
    }
    /**
     * 格式化日期为
     * yyyy-MM-dd
     * @param strDate yyyyMMdd
     * @return
     * @throws Exception
     */
    public static String formatToDayStirng2(String strDate){
        Date date;
        try {
            date = parse(strDate, defaultDatePattern2);
            return format(date, defaultDatePattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";

        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, getDatePattern());
    }

    public static String parseString(String strDate) throws ParseException {
        if (strDate == null || "".equals(strDate)) {
            return null;
        }
        if (strDate.length() > 19) {
            strDate = strDate.substring(0, 19);
        }
        Date bufferDate = parse(strDate, defaultDatePattern1);
        return format(bufferDate);
    }

    public static Date parseAll(String strDate) throws ParseException {
        if (strDate == null || "".equals(strDate)) {
            return null;
        }
        return parse(strDate, defaultDatePattern1);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(strDate);
    }


    /**
     * * 将日期转化成 oracle 的 to_date('xxx','xxx') 格式
     *
     * @param d
     *            日期
     * @param format
     *            日期格式，例如 "yyyy-MM-dd HH:mm"
     * @param hqlFormat
     *            oracle的日期格式，例如："yyyy-mm-dd hh24:mi"
     * @return
     */

    public static String toDate(final Date d, final String format, final String hqlFormat) {
        StringBuffer bf = new StringBuffer();
        bf.append("to_date('");
        bf.append(dateFormat(d, format));
        bf.append("','");
        bf.append(hqlFormat);
        bf.append("')");
        return bf.toString();
    }

    /**
     * * 将日期转化成 oracle 的 to_date('xxx','xxx') 格式
     *
     * @param d
     *            日期
     *
     * @param hqlFormat
     *            oracle的日期格式，例如："yyyy-mm-dd hh24:mi"
     * @return
     */

    public static String toDate(final String date, final String hqlFormat) {
        StringBuffer bf = new StringBuffer();
        bf.append("to_date('");
        bf.append(date);
        bf.append("','");
        bf.append(hqlFormat);
        bf.append("')");
        return bf.toString();
    }

    /**
     * * 将日期转化成 oracle 的 to_date('xxx','xxx') 格式
     *
     * @param d
     *            日期
     *
     * @param hqlFormat
     *            oracle的日期格式，例如："yyyy-mm-dd hh24:mi"
     *
     * @param i
     *            加一天或者减一天  如：i=-1;i=1
     * @return
     */

    public static String toDate(final String date, final String hqlFormat,final int i) {
        StringBuffer bf = new StringBuffer();
        bf.append("to_date('");
        bf.append(date);
        bf.append("','");
        bf.append(hqlFormat);
        bf.append("')+"+i);
        return bf.toString();
    }

    /**
     * 将日期转化成指定格式的字符串
     *
     * @param d
     *            日期
     * @param formatStr
     *            字符串格式
     * @return
     */
    private static String dateFormat(Date d, String formatStr) {
        return (new java.text.SimpleDateFormat(formatStr).format(d));
    }

    /**
     *
     * 比较两个日期相差多少天。
     * 两个日期（a,b）相差天数 是否是（C ）天/包含。 如果是就返回true 。
     * 比如2011-08-31 5:20:30  和 2011-09-01 5:19:20 他们相隔的不是一天
     * 又如2011-08-31 5:20:30  和 2011-09-01 5:21:20 他们相隔的是一天
     */
    public static Boolean getPoorSeconds(Date a,Date b,int c){
        Calendar timea = Calendar.getInstance();
        Calendar timeb = Calendar.getInstance();
        timea.setTime(a);
        timeb.setTime(b);
        return (timea.getTimeInMillis() - timeb.getTimeInMillis())/(60*60*24*1000)>=c;
    }

    /**
     * 比较日期大小 格式 yyyy-MM-dd hh:mm
     * @param startdate
     * @param enddate
     * @return
     */
    public static int compare_date(String startdate, String enddate) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date startTime = df.parse(startdate);
            Date endTime = df.parse(enddate);
            if (startTime.getTime() > endTime.getTime()) {
                return -1;
            } else if (startTime.getTime() < endTime.getTime()) {

                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    /**
     * 比较日期大小 格式 yyyy-MM-dd
     * @param startdate
     * @param enddate
     * @return
     */
    public static int compare_date2(String startdate, String enddate) {

        DateFormat df = new SimpleDateFormat(defaultDatePattern);
        try {
            Date startTime = df.parse(startdate);
            Date endTime = df.parse(enddate);
            if (startTime.getTime() > endTime.getTime()) {
                return -1;
            } else if (startTime.getTime() < endTime.getTime()) {

                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    /**
     * 得到日期减一个月日期
     * @return
     */
    public static String getAfterMonthDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对象
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MONTH, -1);//月份减一
        return sdf.format(calendar.getTime());
    }

    /**
     * 将String日期转为格式化日期
     * @return
     */
    public static String formattingDate(String date,String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化对象
        Date dates = sdf.parse(date);
        return sdf.format(dates);
    }

    /**
     * 得到指定日期的后几天天
     * @param dates 日期 yyyy-MM-dd
     * @param days 指定后几天
     * @return
     * @throws Exception
     */
    public static String getdayAfter(String dates,int days) throws Exception {
        Calendar c = Calendar.getInstance();
        Date date=null;
        date = DateUtil.parse(dates);
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day + days);
        String dayAfter = DateUtil.format(c.getTime());
        return dayAfter;
    }



    /**
     * 得到指定日期的天几天
     * @param dates 日期 yyyy-MM-dd
     * @param days 指定前几天
     * @return
     * @throws Exception
     */
    public static String getdaybefore(String dates,int days) throws Exception {
        Calendar c = Calendar.getInstance();
        Date date=null;
        date = DateUtil.parse(dates);
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day - days);
        String dayAfter = DateUtil.format(c.getTime());
        return dayAfter;
    }




    /**
     * 获得指定日期所在的星期六
     * @param time
     * @return
     */
    public static String convertWeekByDate(Date time) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        //获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);
        //获取指定日期的所在周的星期六
        cal.add(Calendar.DATE, 5);
        String imptimeEnd = sdf.format(cal.getTime());
        //System.out.println("所在周星期日的日期："+imptimeEnd);
        return imptimeEnd;
    }



    /**
     *
     * @param datea 开始时间
     * @param dateb 结束时间
     * 比较两个日期 相差多少时长 精确到小时和分钟
     * 比如2014-02-19 12:00:00  和 2014-02-20 12:00:00 他们相隔24h
     * 又如2014-02-19 12:00:00  和 2014-02-20 13:30:00他们相隔25h30m
     * @return
     * @throws ParseException
     */
    public static String getTimeDifference(String datea,String dateb) throws ParseException{

        SimpleDateFormat sf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = sf.parse(datea);

        Date d2 = sf.parse(dateb);

        long stamp = (d2.getTime() - d1.getTime())/1000;
        int MM = (int) stamp / 60; // 共计分钟数
        int hh = (int) stamp / 3600; // 共计小时数
        int mm =  (int)MM - hh * 60 ;

        return hh + " 小时 " + mm + " 分钟";
    }



    //将整形秒数转为具体时分秒
    public static String secondTohms(long secnod){
        int s,m,h;
        if(secnod < 60){
            return secnod+"秒";
        } else if(secnod < 3600){
            s = (int)secnod % 60;
            m = (int)secnod /60;
            return m+"分钟"+s+"秒";
        } else {
            int count = (int)secnod /60;
            h = (int)secnod / 3600;//获取小时
            m = count - h * 60;//获取分钟
            s = (int)secnod % 60;//获取秒
            return h+"小时"+m+"分钟"+s+"秒";
        }
    }




    /**
     *
     * TODO  时间秒转格式日期
     * @param second
     * @param fmt
     * @return
     */
    public static  String secondToFmtDate(long second,String fmt) {
        GregorianCalendar gc=new GregorianCalendar();
        gc.setTimeInMillis(second*1000);
        //SimpleDateFormat format = new SimpleDateFormat(ValidateUtil.str_isEmpty(fmt) ? defaultDatePattern1:fmt);

        SimpleDateFormat format = new SimpleDateFormat(fmt != null && !fmt.equals("")?defaultDatePattern1:fmt);
        return format.format(gc.getTime());
    }



    /**
     *
     * TODO  格式日期转时间秒
     * @param fmtDate
     * @return
     * @throws ParseException
     */
    public static  long fmtDateToSecond(String fmtDate) {

        SimpleDateFormat df = new SimpleDateFormat(defaultDatePattern1);
        Date date=null;
        try {
            date = df.parse(fmtDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
        }
        return date.getTime()/1000;
    }



    // 得到时间和日期
    public static String getTimeAndDate() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String hourstr, minuter;
        if (hour < 10) {
            hourstr = "0" + hour;
        } else {
            hourstr = hour + "";
        }
        if (minute < 10) {
            minuter = "0" + minute;
        } else {
            minuter = minute + "";
        }
        String timestr = hourstr + ":" + minuter;
        String date = year + "-" + month + "-" + day;
        String timeAndDate = timestr + "&" + date;
        return timeAndDate;
    }

    // 判断是否为闰年
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    //得到某月有多少天数
    public static int getDaysOfMonth(boolean isLeapyear, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapyear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }

        }
        return daysOfMonth;
    }


    /**
     * 指定某年中的某月的第一天是星期几
     * @param year
     * @param month
     * @return
     */
    public static int getWeekdayOfMonth(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, 1);
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
        return dayOfWeek;
    }


    /**
     * 取得当前日期是多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime (date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得当前日期是多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfDay(int year,int month,int day){

        String today = year+"-"+month+"-"+day;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }




    /**
     * 得到某一年周的总数
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }
    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set (Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime ());
    }
    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE , week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得指定日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime ();
    }


    /**
     * 取得指定日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek() {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime ();
    }
    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek() {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }


    /**
     * 将时间格式2016-08-03T10:24:48 去掉T
     * @param dateStr
     * @return
     */
    public static String formatData(String dateStr)
    {
        String label = "T";
        if (!dateStr.equals("") && dateStr != null) {
            if (dateStr.contains(label)) {
                int indexT = dateStr.indexOf(label); //得到T的下标
                return dateStr.substring(0,indexT)+" "+dateStr.substring(indexT+1);
            }
            return dateStr;
        }
        return "";

        /*线程不安全的
        StringBuilder sb = new StringBuilder(dateStr);
        int indexT = dateStr.indexOf("T"); //得到T的下标
        sb.replace(indexT,indexT+1," ");
        return sb.toString();*/
    }


    public static void main(String[] arg) throws Exception {
        //		Date date=new Date();
        //		System.out.println(date.getTime());
        //		System.out.println(date.getTime()/1000);
        ////		System.out.println(date.getTimezoneOffset());
        //		   long time=1393896819;//秒
        //	          GregorianCalendar gc = new GregorianCalendar();
        //	          gc.setTimeInMillis(time * 1000);
        //	          java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd ");
        //	          System.out.print( format.format(gc.getTime()));
        //		String fmtDate="2014-03-04 09:49:49";
        //		SimpleDateFormat df = new SimpleDateFormat(defaultDatePattern1);
        //		Date date1= df.parse(fmtDate);
        //		System.out.println(date1.getTime()/1000);
        //System.out.println(secondTohms(3601));
        //	System.out.println(getAllToday());
        //System.out.println(formatsDate("20150101","yyyyMMdd","yyyy-MM-dd"));


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        int year = 2016;
        int week = 19;

        Calendar c = new GregorianCalendar();
        c.set(2016, Calendar.MAY, 12); //默认设置为2016年5月12日为测试
        Date d = c.getTime();

        System.out.println("当前日期 = " + dateFormat.format(d));
        System.out.println("当前日期是多少周 = " + getWeekOfYear(d));
        System.out.println("某一年周的总数 = " + getMaxWeekNumOfYear(year));

        System.out.println("得到某年某周的第一天 = " + getFirstDayOfWeek(year, week).getDate());
        System.out.println("得到某年某周的最后一天 = " + getLastDayOfWeek(year, week).getDate());


        /*System.out.println ("取得指定日期所在周的第一天 = " + getFirstDayOfWeek(d).getDate());
        System.out.println("取得指定日期所在周的最后一天 = " + getLastDayOfWeek(d).getDate());
        System.out.println ("取得当前日期所在周的第一天 = " + getFirstDayOfWeek().getDate());
        System.out.println("取得当前日期所在周的最后一天 = " + getLastDayOfWeek().getDate());*/
    }

}
