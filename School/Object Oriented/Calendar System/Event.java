//package simplecalendar;

/**
 * @author Justin Passanisi
 */

/**
 * Event class that provides a framework for how an event should behave and its characteristics
 */

public class Event 
{   
    private String title, date, startTime, endTime;
    private int month, day, year;
    
    /**
     * Empty Constructor
     */
    public Event(){}
    
    /**
     * Full Constructor
     * @param t : Passes in a String type title
     * @param d : Passes in a String type date in format ( MM/DD/YYYY )
     * @param sT : Passes in a String start time in military format
     * @param eT : Passes in a String end time in military format
     */
    public Event(String t, String d, String sT, String eT)
    {
        title = t;
        date = d;
        startTime = sT;
        endTime = eT;
        
        String[] seperated = date.split("/");
        month = Integer.parseInt(seperated[0]);
        day = Integer.parseInt(seperated[1]);
        year = Integer.parseInt(seperated[2]);
    
    }
    
    /**
     * Accessor method for finding and returning the title of an Event object
     * @return title of event
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Accessor method for finding and returning the date of an Event object
     * @return date of event
     */
    public String getDate()
    {
        return date;
    }
    
    /**
     * Accessor method for finding and returning the date in year-month-day format for comparison between dates
     * @return year-month-day formatted date
     */
    public String getCompareDate()
    {
        String compareDate;
        compareDate = String.valueOf(year) + String.valueOf(month) + String.valueOf(date); 
        return compareDate;
    }
    
    /**
     * Accessor method for finding and returning the month of an Event object
     * @return month of event (-1 because of index starting at 0)
     */
    public int getMonth()
    {
        return month-1;
    }
    
    /**
     * Accessor method for finding and returning the day of an Event object
     * @return day of event
     */
    public int getDay()
    {
        return day;
    }
    
    /**
     * Accessor method for finding and returning the year of an Event object
     * @return year of event
     */
    public int getYear()
    {
        return year;
    }
    
    /**
     * Accessor method for finding and returning the start time of an Event object
     * @return start time of event
     */
    public String getStartTime()
    {
        return startTime;
    }
    
    /**
     * Accessor method for finding and returning the end time of an Event object
     * @return end time of event
     */
    public String getEndTime()
    {
        return endTime;
    }
    
    /**
     * Mutator method for assigning a value to the title of an Event object
     * @param t : Passes in a string to be assigned to the title of an Event
     */
    public void setTitle(String t)
    {
        title = t;
    }
    
    /**
     * Mutator method for assigning a value to the date of an Event object when given month, day, and year
     * @param m : Passes in an integer m to represent the month of an Event
     * @param d : Passes in an integer d to represent the day of an Event
     * @param y : Passes in an integer y to represent the year of an Event
     */
    public void setDate(int m, int d, int y)
    {
        date = String.valueOf(m) + "/" + String.valueOf(d) + "/" + String.valueOf(y);
    }
    
    /**
     * Mutator method for assigning a value to the date of an Event object
     * @param d : Passes in a string to be assigned to the date of an Event
     */
    public void setDate(String d)
    {
        date = d;
    }
    
    /**
     * Mutator method for assigning a value to the month of an Event object
     * @param m : Passes in an integer to be assigned to the month of an Event
     */
    public void setMonth(int m)
    {
        month = m;
    }
    
    /**
     * Mutator method for assigning a value to the day of an Event object
     * @param d : Passes in an integer to be assigned to the day of an Event
     */
    public void setDay(int d)
    {
        day = d;
    }
    
    /**
     * Mutator method for assigning a value to the year of an Event object
     * @param y : Passes in an integer to be assigned to the year of an Event
     */
    public void setYear(int y)
    {
        year = y;
    }
    
    /**
     * Mutator method for assigning a value to the start time of an Event object
     * @param sT : Passes in a string to be assigned to the start time of an Event
     */
    public void setStartTime(String sT)
    {
        startTime = sT;
    }
    
    /**
     * Mutator method for assigning a value to the end time of an Event object
     * @param eT : Passes in a string to be assigned to the end time of an Event
     */
    public void setEndTime(String eT)
    {
        endTime = eT;
    }
    
    /**
     * toString method to return a string containing the data within an Event object
     * @return Event object's data in a string; title, date, start time, end time
     */
    @Override
    public String toString()
    {
        return "Title : " + title + "\nDate : " + date + "\nStart Time : " + startTime + "\nEnd Time : " + endTime + "\n";
    }
}