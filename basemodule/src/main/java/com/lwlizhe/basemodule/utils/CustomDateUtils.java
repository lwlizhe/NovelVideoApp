package com.lwlizhe.basemodule.utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.TextView;

import com.lwlizhe.basemodule.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



/**
 * Created by 62445 on 2017/6/10.
 */

public class CustomDateUtils {

    private static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmmss";
    private static final String FORMAT_MM_DD_HH_MM = "MM-dd HH:mm";
    private static final String FORMAT_MM_DD_HH_MM_C = "MM月dd日 HH:mm";
    private static final String FORMAT_DD = "dd";
    private static final String FORMAT_MM = "MM";

    public final static String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";
    public final static String DATE_FORMAT = "yyyy年MM月dd日    HH:mm";
    public final static String DATE_FORMAT_YMDHMS_Z = "yyyy年MM月dd日  HH:mm:ss";
    public final static String DATE_FORMAT_DD = "dd";
    public final static String DATE_FORMAT_YMDHMSA = "yyyy-MM-dd hh:mm:ss a";
    public final static String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_YMDHMA = "yyyy-MM-dd hh:mm a";
    public final static String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public final static String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public final static String DATE_FORMAT_HMA = "hh:mm a";
    public final static String DATE_FORMAT_HM = "HH:mm";
    public static final int DEFAULT_YEAR = 1970;


    public static String transformTimeStampToData(int timeStamp){

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy年MM月dd日");

        return dateFormat.format(new Date(timeStamp*1000L));
    }

    /**
     * 由秒得到转换之后的时间（分钟/小时/天）
     *
     * @param second
     * @return
     */
    public static String getTimeFromMillisecond(Context mContext, long second) {
        long now = (System.currentTimeMillis() / 1000);
        long createTime = now - second;
        if (createTime <= 0) {
            return mContext.getString(R.string.label_now);
        } else {
            long minutes = createTime / 60 + 1;
            if (minutes < 60) {
                return minutes + mContext.getString(R.string.label_minites_before);
            } else {
                long hours = minutes / 60;
                if (hours < 24) {
                    return hours + mContext.getString(R.string.label_hours_before);
                } else {
                    long days = hours / 24;
                    if (days < 30) {
                        return days + mContext.getString(R.string.label_day_before);
                    } else {
                        long month = days / 30;
                        if (month < 12) {
                            return month + mContext.getString(R.string.label_month_before);
                        } else {
                            long year = month / 12;
                            return year + mContext.getString(R.string.label_year_before);
                        }
                    }
                }
            }
        }
    }

    /**
     * 由秒得到转换之后的时间
     *
     * @param second
     * @return
     */
    public static String getCreateTime(long second) {
        long currentMilliseconds = System.currentTimeMillis();
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(currentMilliseconds);
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
        //
        long milliseconds = second * 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String mYear = year + "年";
        String mMonth = month + "月";
        String mDay = day + "日";
        StringBuffer sb = new StringBuffer();
        // 2014-7-14
        // 2014 != 2015
        if (year != currentYear) {
            sb.append(mYear);
            sb.append(mMonth);
            sb.append(mDay);
        } else {
            // 7 != 8
            if (month != currentMonth) {
                sb.append(mMonth);
                sb.append(mDay);
            } else {
                sb.append(mDay);
            }
        }
        return sb.toString();
    }

    /**
     * 由秒得到转换之后的支出时间
     *
     * @param second
     * @return
     */
    public static String getExpenseTime(long second) {
        //
        long milliseconds = second * 1000;
        Date date = new Date(milliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM);
        formatter.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String mMonth = (month < 10 ? "0" : "") + month + "月";
        String mDay = (day < 10 ? "0" : "") + day + "日  ";
        String mTime = (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
        String expenseTime = mMonth + mDay + mTime;
        return expenseTime;
    }

    public static String formatDate(String dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
        return sdf.format(dataStr);
    }
    public static String formatDate(Date dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
        return sdf.format(dataStr);
    }

    public static String formatDateMMDD(Date dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_MM_DD_HH_MM_C);
        return sdf.format(dataStr);
    }

    public static String formatDateMMDD(Context mContext, String dataStr) {
        int time = Integer.parseInt(dataStr);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_MM_DD_HH_MM);
        String dateString = sdf.format(new Date(time * 1000L));
        return dateString + mContext.getString(R.string.label_beijing_time);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getStringDate2() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYYMMDDHHMM);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 根据毫秒数得到当前月份
     *
     * @param millis
     * @return
     */
    public static String getDay(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DD);
        return sdf.format(date);
    }


    private static long lastClickTime;

    /**
     * 判断两次点击的间隔时间
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 2000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 转换为报表每个月title的日期
     * @param year
     * @param month
     */

    public static String formatReportDate(String year, String month) {
        StringBuilder sbDate = new StringBuilder();
        sbDate.append(year)
                .append("年")
                .append(month)
                .append("月");
        return sbDate.toString();

    }


    /**
     * date format to AM/PM format: a
     *
     * @param date
     * @return AM/PM
     */
    public static String toAMPMString(Date date) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("a", Locale.US);
            return myFmt.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String to12HourTimeString(Date date, boolean displayAM) {
        if (date == null) {
            return null;
        }
        String time = to12HourTimeString(date);
        if (!displayAM) {
            return time;
        }
        String am = toAMPMString(date);
        return time + " " + am;
    }

    /**
     * date format to time for 24 hour format format: HH:mm ex. 08:04
     *
     * @param date
     * @return
     */
    public static String to24HourTimeString(Date date) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("HH:mm", Locale.US);
            return myFmt.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * date format to time for 12 hour format format: hh:mm ex. 08:04
     *
     * @param date
     * @return
     */
    public static String to12HourTimeString(Date date) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("hh:mm", Locale.US);
            return myFmt.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String toTimeString(Context context, Date date) {
        final String M12 = "h:mm";
        final String M24 = "HH:mm";
        String formatTime = android.text.format.DateFormat.is24HourFormat(context) ? M24 : M12;
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat(formatTime, Locale.US);
            return myFmt.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * date format to yyyy-MM-dd format ex. 2014-05-28
     *
     * @param date
     * @return
     */
    public static String toDateString(Date date) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.US);
            return myFmt.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * date format to yyyy-MM-dd HH:mm:ss format ex. 2014-05-28
     *
     * @param date
     * @return
     */
    public static String toDateTimeString(Date date) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.US);
            return myFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date toDate(String dateString) {
        if (TextUtils.isEmpty(dateString)) {
            return null;
        }
        DateFormat sdf;
        dateString = dateString.trim();
        int tmpPos = dateString.indexOf("-");
        if (tmpPos > 0) {
            String[] space = dateString.split(" ");
            if (dateString.length() == 10 && space.length == 1) {
                sdf = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.US);
            } else if (dateString.length() == 16 && space.length == 2) {
                sdf = new SimpleDateFormat(DATE_FORMAT_YMDHM, Locale.US);
            } else if (dateString.length() == 19 && space.length == 2) {
                sdf = new SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.US);
            } else if (space.length == 3) {
                if (dateString.length() == 19) {
                    sdf = new SimpleDateFormat(DATE_FORMAT_YMDHMA, Locale.US);
                } else if (dateString.length() == 22) {
                    sdf = new SimpleDateFormat(DATE_FORMAT_YMDHMSA, Locale.US);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            if (dateString.length() == 5) {
                sdf = new SimpleDateFormat(DATE_FORMAT_HM, Locale.US);
            } else if (dateString.length() == 7 || dateString.length() == 8) {
                sdf = new SimpleDateFormat(DATE_FORMAT_HMA, Locale.US);
            } else {
                return null;
            }
        }
        Date d = null;
        try {
            d = sdf.parse(dateString.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * date format to date for locale format
     *
     * @param date
     * @return
     */
    public static String toDateLocaleString(Date date) {
        try {
            DateFormat ddf = DateFormat.getDateInstance(DateFormat.MEDIUM);
            return ddf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * date format to time for locale
     *
     * @param date
     * @return
     */
    public static String toTimeLocaleString(Context context, Date date) {
        try {
            DateFormat dtf = android.text.format.DateFormat.getTimeFormat(context);
            return dtf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * date format to datetime for locale
     *
     * @param date
     * @return
     */
    public static String toDateTimeLocaleString(Context context, Date date) {
        try {
            DateFormat ddf = DateFormat.getDateInstance(DateFormat.MEDIUM);
            DateFormat dtf = android.text.format.DateFormat.getTimeFormat(context);
            return ddf.format(date) + " " + dtf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date toDateTime(Date date, String time12Hour, String am) {
        String dateString = toDateString(date);
        if (TextUtils.isEmpty(time12Hour)) {
            time12Hour = "00:00:00";
            am = "AM";
        }
        if (TextUtils.isEmpty(am)) {
            am = "";
        }
        String[] ts = time12Hour.split(":");
        if (ts.length > 0 && ts[0].length() == 1) {
            String hour = "0" + ts[0];
            ts[0] = hour;
            time12Hour = TextUtils.join(":", ts);
        }
        String dateTimeString = String.format("%1$s %2$s %3$s", dateString, time12Hour, am);
        return toDate(dateTimeString);
    }

    public static String toDateTimeLocaleString(Context context, Date date, String time, String am) {
        Date dt = toDateTime(date, time, am);
        if (dt != null) {
            if (TextUtils.isEmpty(time)) {
                return toDateLocaleString(dt);
            } else {
                return toDateTimeLocaleString(context, dt);
            }
        }
        return null;
    }

    public static void getDate(final Context context, final TextView v, final Calendar calendar, String title) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Date selectedDate = setDate(year, monthOfYear + 1, dayOfMonth);
                v.setText(toDateLocaleString(selectedDate));
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle(title);
        datePickerDialog.show();
    }

    /**
     * 根据指定格式获取指定时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDate(long timeMillis, String formatString) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        Date curDate = new Date(timeMillis);
        return formatter.format(curDate);
    }

    /**
     * set date
     *
     * @param year
     *            , monthOfYear,dayOfMonth
     * @return
     */
    public static Date setDate(int year, int monthOfYear, int dayOfMonth) {
        String date = year + "-" + (monthOfYear < 10 ? ("0" + monthOfYear) : monthOfYear) + "-" + (dayOfMonth < 10 ? ("0" + dayOfMonth) : dayOfMonth);
        return toDate(date);
    }

    /**
     * set datetime
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date setDateTime(int year, int month, int day, int hour, int minute, int second) {
        String datetime = year + "-" + (month < 10 ? ("0" + month) : month) + "-" + (day < 10 ? ("0" + day) : day) + "-" + (hour < 10 ? ("0" + hour) : hour) + "-"
                + (minute < 10 ? ("0" + minute) : minute) + "-" + (second < 10 ? ("0" + second) : second);
        return toDate(datetime);
    }

    /**
     * set time
     *
     * @param hour
     * @param minute
     * @param second
     * @return 1970-01-01 time
     */
    public static Date setTime(int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(DEFAULT_YEAR, 0, 1, hour, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date toTime(String timeString) {
        if (!TextUtils.isEmpty(timeString)) {
            Date time = toDate(timeString);
            Integer hour = null;
            Integer minute = 0;
            if (time != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(time);
                hour = cal.get(Calendar.HOUR_OF_DAY);
                minute = cal.get(Calendar.MINUTE);
            } else {
                String[] hourMinute = timeString.split(":");
                if (hourMinute.length == 2) {
                    hour = Integer.parseInt(hourMinute[0]);
                    minute = Integer.parseInt(hourMinute[1]);
                }
            }
            if (hour != null && minute != null) {
                return setTime(hour, minute, 0);
            }
        }
        return null;
    }

    /**
     * add datetime
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinutes(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * get hour
     *
     * @param minute
     *            minute
     * @return 1.5 = 1 hour, 30 minute
     */
    public static double toHours(int minute) {
        return Double.parseDouble(String.format("%.2f", minute / 60d));
    }

    /**
     * get duration for time
     *
     * @param date
     * @return duration Minutes
     */
    public static int toDiffMinutes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return toMinute(date.getTime() - cal.getTimeInMillis());
    }

    /**
     * get duration for time
     *
     * @param minutes
     * @return hour:minute
     */
    public static String toHourMinuteString(Integer minutes) {
        if (minutes == null) {
            return null;
        }
        long second = minutes * 60;
        long hour = second % (24 * 3600) / 3600;
        long minute = second % 3600 / 60;
        return String.format("%1$s:%2$s", (hour < 10 ? ("0" + hour) : hour), (minute < 10 ? ("0" + minute) : minute));
    }

    /**
     * Convert hours to minutes
     *
     * @param hours
     *            1.5h
     * @return 90min
     */
    public static int toMinute(Double hours) {
        if (hours == null) {
            return 0;
        }
        return Math.round(hours.floatValue() * 60);
    }

    /**
     * get minutes from duration

     *  milliseconds
     * @return minutes
     */
    public static int toMinute(long milliseconds) {
        long sencond = milliseconds / 1000;
        return (int) sencond / 60;
    }

    /**
     * get time difference
     *
     * @param befor
     * @param after
     * @return
     */
    public static int getDiffMinutes(Date befor, Date after) {
        if (befor == null) {
            befor = new Date();
        }
        if (after == null) {
            after = new Date();
        }
        long diffMilliseconds = after.getTime() - befor.getTime();
        return toMinute(diffMilliseconds);
    }

    /**
     * the same date
     *
     * @param leftDate
     * @param rightDate
     * @return
     */
    public static boolean isSameDate(Date leftDate, Date rightDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(leftDate);
        int leftDay = cal.get(Calendar.DATE);
        cal.setTime(rightDate);
        int rightDay = cal.get(Calendar.DATE);
        return leftDay == rightDay;
    }

    /**
     * time overlap
     *
     * @param s1
     * @param e1
     * @param s2
     * @param e2
     * @return
     */
    public static boolean isOverLaps(Date s1, Date e1, Date s2, Date e2) {
        if (s1 == null || e1 == null || s2 == null || e2 == null) {
            return false;
        }
        return e2.getTime() > s1.getTime() && s2.getTime() < e1.getTime();
    }

    public static boolean equalDate(Date left, Date right) {
        if (left == null || right == null) {
            return false;
        }
        Calendar calLeft = Calendar.getInstance();
        Calendar calRight = Calendar.getInstance();
        setDateMillisecondToZero(left, right, calLeft, calRight);
        return calLeft.getTimeInMillis() == calRight.getTimeInMillis();
    }

    public static boolean beforDate(Date left, Date right) {
        if (left == null) {
            return false;
        }
        if (right == null) {
            return true;
        }
        Calendar calLeft = Calendar.getInstance();
        Calendar calRight = Calendar.getInstance();
        setDateMillisecondToZero(left, right, calLeft, calRight);
        return calLeft.before(calRight);
    }

    public static boolean afterDate(Date left, Date right) {
        if (left == null) {
            return true;
        }
        if (right == null) {
            return false;
        }
        Calendar calLeft = Calendar.getInstance();
        Calendar calRight = Calendar.getInstance();
        setDateMillisecondToZero(left, right, calLeft, calRight);
        return calLeft.after(calRight);
    }

    private static void setDateMillisecondToZero(Date left, Date right, Calendar calLeft, Calendar calRight) {
        calLeft.setTime(left);
        calLeft.set(Calendar.MILLISECOND, 0);
        calRight.setTime(right);
        calRight.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 根据指定格式获取时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate(String formatString) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 获取格式为：yyyy年MM月dd日 HH:mm:ss 的当前时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 获取格式为：yyyy年MM月dd日的当前时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DD);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 获取时间秒数，精确到分
     *
     * @param dateString
     *            201508081025
     * @return
     */
    public static long getSecond(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMM);
        long millionSeconds = 0;
        try {
            millionSeconds = sdf.parse(dateString).getTime();
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }// 毫秒
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMM).parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (c.getTimeInMillis() / 1000);
    }


}
