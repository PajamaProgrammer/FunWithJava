package myclock;

import java.util.Calendar;

/**
 * The Model stores all the data needed for the clock and performs any calculations needed. 
 * It is doesn't know the View exists
 * @author RKOwen
 */
public class MyClockModel {
   
    //Use a Calender to store the data and time
    private Calendar today;

    //Constructor will update the calendar
    public MyClockModel()
    {
        this.today= Calendar.getInstance();
    }
    //Update time updates the calendar
    public void updateTime()
    {
        this.today= Calendar.getInstance();
    }
    
    //Getters will return the hour, minute, second, etc...
    public int getHour()
    {
       int hour = today.get(Calendar.HOUR);
        if (hour == 0)
            hour = 12;
        return hour;
    }
    
    public int getMinute()
    {
       return today.get(Calendar.MINUTE);
    }
    
    public int getSecond()
    {
        return today.get(Calendar.SECOND);
    }
    
    public boolean isAM()
    {
        return today.get(Calendar.AM_PM)==0;
    }
    
    public boolean isPM()
    {
        return today.get(Calendar.AM_PM)==1;
    }
    
    //Not Implemented, just showing that theModel could easily be modified 
    //to also return the parts of the Date (year, month, day...)
    public String getMonth()
    {
        switch(today.get(Calendar.MONTH))
        {
            case Calendar.JANUARY:
                return "January";
            case Calendar.FEBRUARY:
                return "February";
            case Calendar.MARCH:
                return "March";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "June";
            case Calendar.JULY:
                return "July";
            case Calendar.AUGUST:
                return "August";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "October";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";    
            default:
                return"";
        }
    }
}
