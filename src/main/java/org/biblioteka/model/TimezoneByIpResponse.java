package org.biblioteka.model;

public class TimezoneByIpResponse {

    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int seconds;
    public int milliSeconds;

    public String dateTime;
    public String date;
    public String timeZone;
    public String time;
    public String dayOfWeek;

    public boolean dstActive;

    public TimezoneByIpResponse() {
    }
}
