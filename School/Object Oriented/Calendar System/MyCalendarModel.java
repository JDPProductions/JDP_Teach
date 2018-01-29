//package simplecalendar;

/**
 * @author Justin Passanisi
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 * MyCalendar class deals with the array list of events as well as a running calendar for the view to manipulate
 * Model class in the MVC pattern.
 */

public class MyCalendarModel
{
    List<Event> events = new ArrayList<>(); 
    GregorianCalendar cal = new GregorianCalendar();
    Font font = new Font("Helvetica", Font.PLAIN, 18);
    
    /**
     * String array of times of day
     */
    String[] times = {"12A.M.","1A.M.","2A.M.","3A.M.","4A.M.","5A.M.","6A.M.",
                      "7A.M.","8A.M.","9A.M.","10A.M.","11A.M.","12P.M.","1P.M.","2P.M.",
                      "3P.M.","4P.M.","5P.M.","6P.M.","7P.M.","8P.M.","9P.M.","10P.M.","11P.M."};
    
    /**
     * Enum string value of days in a week
     */
    private enum DAYS
    {
    Su , Mo, Tu, We, Th, Fr, Sa ;
    }
    
    /**
     * Enum string values of days in a week
     */
    private enum MONTHS
    {
    Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
    }
    
    //initialization of arrays
    final MONTHS[] arrayOfMonths = MONTHS.values();
    final DAYS[] arrayOfDays = DAYS.values();
    final int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    
    //current calendar values
    private int day = cal.get(Calendar.DAY_OF_MONTH);
    private int month = cal.get(Calendar.MONTH);
    private int year = cal.get(Calendar.YEAR);
    
    //offset needed when printing calendar
    private int offset = setOffset(cal);
    
    //String values for current calendar fields
    private String dayString = " ";
    private String dayValueString = String.valueOf(day);
    private String monthString = arrayOfMonths[month].toString();
    private String monthValueString = String.valueOf(month);
    private String yearString = String.valueOf(year);
    
    /**
     * Method that sets the offset needed when printing the calendar view
     * @param c: Calendar to be used inside of method
     * @return integer of offset value
     */
    public int setOffset(GregorianCalendar c)
    {
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 0);
        offset = (c.get(Calendar.DAY_OF_WEEK));
        if(offset == 7)
            offset = 0;
        return offset;
    }
    
    /**
     * Method to return the offset value
     * @return offset value
     */
    public int getOffest()
    {
        return offset;
    }
    
    /**
     * Method to set the value of the day of the month
     * @param d: integer that determines the day
     */
    public void setDay(int d)
    {
        cal.set(Calendar.DAY_OF_MONTH , d);
        day = cal.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * Method to set the value of the month
     * @param m: integer that determines the month
     */
    public void setMonth(int m)
    {
        cal.set(Calendar.MONTH, m);
        month = cal.get(Calendar.MONTH);
    }
    
    /**
     * Method to set the value of the year
     * @param y: integer that determines the year
     */
    public void setYear(int y)
    { 
        cal.set(Calendar.YEAR, y);
        year = cal.get(Calendar.YEAR);
    }
    
    /**
     * Private method to set the string of the day
     */
    private void setDayString()
    {
        if((day+offset)%7 == 0)
            dayString = arrayOfDays[6].toString();
        else
            dayString = arrayOfDays[((day+offset)%7)-1].toString(); 
    }
    
    /**
     * Private method to set the string of the day value
     */
    private void setDayValueString(){ dayValueString = String.valueOf(day); }
    //private void setMonthString(){ monthString = arrayOfMonths[cal.get(Calendar.MONTH)].toString(); }
    
    /**
     * Private method to set the string of the month value
     */
    private void setMonthValueString(){ monthValueString = String.valueOf(month+1);}
    
    /**
     * Private method to set the string of the year
     */
    private void setYearString(){ yearString = String.valueOf(year); }
    
    /**
     * Method to return the day value
     * @return day value
     */
    public int getDay(){ return day; }
    
    /**
     * Method to return the month value
     * @return month value
     */
    public int getMonth(){ return month; }
    
    /**
     * Method to return the year value
     * @return year value
     */
    public int getYear(){ return year; }
    
    /**
     * Method to return the day in a string
     * @return day string
     */
    public String getDayString()
    {
        setDayString();
        return dayString; 
    }
    
    /**
     * Method to return the day value in a string
     * @return day value string
     */
    public String getDayValueString()
    {
        setDayValueString();
        return dayValueString; 
    }
    
    /**
     * Method to return the month in a string
     * @return month string
     */
    public String getMonthString()
    {
        return monthString; 
    }
    
    /**
     * Method to return the month value in a string
     * @return month value string
     */
    public String getMonthValueString()
    {
        setMonthValueString();
        return monthValueString; 
    }
    
    /**
     * Method to return the year in a string
     * @return year string
     */
    public String getYearString()
    {
        setYearString();
        return yearString; 
    }
    
    /**
     * Method to print a specific value in the enum of DAYS
     * @param i: The index of the value to print
     * @return string of the value at index i
     */
    public String printEnumDay(int i)
    {
        return arrayOfDays[i].toString();
    }
    
    /**
     * Method to load events from a file names "events.txt"
     * @throws IOException: handles exception if file could not be found
     */
    public void loadEvents() throws IOException
    {
        File eventsFile = new File("events.txt");
        
        //if file does not exist then create it
        if (!eventsFile.exists())
        {
            eventsFile.createNewFile();
        }
        
        try
        {
            Scanner eventScan = new Scanner(eventsFile);
            while (eventScan.hasNext())
            {
                String input = eventScan.nextLine();
                String[] seperated = input.split(",");
                                    
                String title = seperated[0];
                String date = seperated[1];
                String startTime = seperated[2];
                String endTime = seperated[3];
                                    
                Event temp = new Event(title, date, startTime, endTime);
                
                events.add(temp);
            }
        }
        catch (FileNotFoundException f) 
        {
            System.err.println(f.getMessage());
        }
    }
    
    /**
     * Method to store the events into a text file and dispose of array list in this program
     */
    public void storeAndDispose()
    {
            try
            {
                File outputFile = new File("events.txt");
        
                FileWriter writeOutput = new FileWriter (outputFile.getAbsoluteFile());
                try
                {
                    BufferedWriter bWriter = new BufferedWriter(writeOutput);
                    for(int i = 0; i < events.size(); i++)
                    {
                        bWriter.write(storeEvents(i));
                        bWriter.newLine();
                    }
                    bWriter.close();
                }
                catch(Exception ex)
                {
                        System.out.println(ex.getMessage());
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
    }
    
    /**
     * Method to return a specific array to be applied to the day label
     * @return strings in specified order
     */
    public String getDayViewLabel()
    {   
        
        return " " + getDayString() + " " + getDayValueString() + "/" + String.valueOf(month+1);   
    }
    
    /**
     * Method to return a specific array to be applied to the month label
     * @return strings in specified order
     */
    public String getMonthViewLabel()
    {
        return " " + getMonthString() + " " + getYear();
    }
    
    /** Method to return the total number of days in a specific month
     * @return integer value of the total number of days
     */
    public int dayInMonth()
    {
        return days[month];
    }
    
    /**
     * Method that increases the calendar by one day
     * Comment: If it is the last day of the month, method will increase the month and the day to be the 1st
     */
    public void incrementCalendar()
    {
        day = day + 1;
        if(day > days[month])
        {
            month++;
            day = 1;
        }
    }
    
    /**
     * Method that decreases the calendar by one day
     * Comment: If it is the first day of the month, method will decrease the month and the day to be the last day of the previous month
     */
    public void decrementCalendar()
    {
        day = day - 1;
        if(day <= 0)
        {
            month--;
            day = days[month];
        }
    }
    
    /**
     * Stores an event into our database list of events
     * @param title: String containing the title of the event to be stored
     * @param date: String containing the date of the event to be stored
     * @param start: String containing the starting time of the event to be stored
     * @param end: String containing the ending time of the event to be stored
     */
    public void storeEvent(String title, String date, String start, String end)
    {
        events.add(new Event(title, date, start, end));
    }
    
    /**
     * Method that prints the list of events on the view when the controller activates it
     * @param pa: JPanel for the text area to be updated on
     * @param da: Date to be used for printing the events
     * @throws BadLocationException 
     */
    public void printEvents(JPanel pa, String da) throws BadLocationException
    {   
        String[] seperated = da.split("/");
        int monthSeperated = Integer.parseInt(seperated[0]) - 1;
        int daySeperated = Integer.parseInt(seperated[1]);
        int yearSeperated = Integer.parseInt(seperated[2]);
        
        
        pa.removeAll();
        
        JTextArea timesText = new JTextArea();
        JTextArea eventText = new JTextArea();
        timesText.setRows(48);
        timesText.setColumns(6);
        eventText.setRows(48);
        for(int fill = 1; fill < 48; fill++)
        {
            eventText.append("\n");
        }
        eventText.setColumns(20);
        
        for(int i = 0; i < times.length; i++)
        {
            timesText.append(times[i] + "\n\n");
        }
        
        timesText.setFont(font);
        eventText.setFont(font);
        
        int prevCount = 0;
        int count = 0;
    
        for(int i = 0; i < times.length; i++)
        {
            String tempString = times[i];
            count = i*2;
            
            for(int j = 0; j < events.size(); j++)
            {
                Event temp = events.get(j);
                
                if((temp.getMonth()) == monthSeperated && temp.getDay()== daySeperated)// && temp.getYear()== yearSeperated)
                {
                    if(tempString.equals(temp.getStartTime()))
                    {                   
                        eventText.insert(temp.getTitle(), count + prevCount);
                        prevCount = prevCount + temp.getTitle().length();
                        eventText.insert(temp.getStartTime() + " to " + temp.getEndTime(), count+prevCount+1);
                        prevCount = prevCount + temp.getStartTime().length() + temp.getEndTime().length() + 4;
                    }
                    
                }
                
            }
        }
        
        pa.add(timesText, BorderLayout.WEST);
        pa.add(eventText, BorderLayout.CENTER);
        pa.invalidate();
        pa.validate();
    }        
    
    /**
     * Method to store an event into a line in a "events.txt" file
     * @param i: index of event to be printed
     * @return specifically ordered string to be written into "events.txt"
     */
    public String storeEvents(int i)
    {
        Event temp = new Event();
        temp = events.get(i);
        return temp.getTitle() + "," + temp.getDate() + "," + temp.getStartTime() + "," + temp.getEndTime();
    }
    
    /**
     * Method to verify event fields with conflicting formats and/or times
     * @param title: title of event to be verified
     * @param date: date of event to be verified
     * @param start: start time of event to be verified 
     * @param end: end time of event to be verified
     * @return String value of error type
     */
    public String textFieldsVerified(String title, String date, String start, String end)
    {
        String[] seperated = date.split("/");
        int monthSeperated = Integer.parseInt(seperated[0]) - 1;
        int daySeperated = Integer.parseInt(seperated[1]);
        int yearSeperated = Integer.parseInt(seperated[2]);
        
        String errorLabel = null;
        
        if(title.isEmpty() || date.isEmpty() ||  start.isEmpty() || end.isEmpty())
            errorLabel = "A field was left empty. Please fill the field.";
        else
        {
            boolean timeFormat = false;
        
            for(int i = 0; i < times.length; i++)
            {
                if(start.equals(times[i]))
                {
                    timeFormat = true;
                    i = times.length;
                }
            }
            
            if(timeFormat == false)
            {
                errorLabel = "Your start time is wrongly formatted. Please correct the field.";
            }
            
            for(int i = 0; i < times.length; i++)
            {
                if(end.equals(times[i]))
                {
                    timeFormat = true;
                    i = times.length;
                }
            }
            
            if(timeFormat == false)
            {
                errorLabel = "Your end time is wrongly formatted. Please correct the field.";
            }
            
            else
            {
                char startChar1 = start.charAt(0);
                char startChar2 = start.charAt(1);
                char startChar3;
                char endChar1 = end.charAt(0);
                char endChar2 = end.charAt(1);
                char endChar3;
            
                int startTime = 0;
                int endTime = 0;
                int tempStart = 0;
                int tempEnd = 0;
                    
                for(int i = 0; i < events.size(); i++)
                {
                    Event temp = events.get(i);
                
                    startTime = 0;
                    endTime = 0;
                    tempStart = 0;
                    tempEnd = 0;

                    char tempStartChar1 = temp.getStartTime().charAt(0);
                    char tempStartChar2 = temp.getStartTime().charAt(1);  
                    char tempStartChar3;
                    char tempEndChar1 = temp.getEndTime().charAt(0);
                    char tempEndChar2 = temp.getEndTime().charAt(1);
                    char tempEndChar3;
                    
                    if((temp.getMonth()) == monthSeperated && temp.getDay()== daySeperated && temp.getYear()== yearSeperated)
                    {
                        if(Character.isDigit(startChar2))
                        {
                            startTime = Integer.valueOf(String.valueOf(startChar2));
                            startTime = startTime + 10;
                            startChar3 = start.charAt(2);
                            if(String.valueOf(startChar3).equals("P"))
                            {
                                startTime = startTime + 12;
                                if(startTime == 24)
                                    startTime = 12;
                            }
                            else if(startTime == 12)
                            {
                                startTime = 0;
                            }
                        }
                        else if(Character.isDigit(startChar1))
                        {
                            startTime = Integer.valueOf(String.valueOf(startChar1));
                            startChar3 = start.charAt(1);
                            if(String.valueOf(startChar3).equals("P"))
                                startTime = startTime + 12;
                        }

                        if(Character.isDigit(endChar2))
                        {
                            endTime = Integer.valueOf(String.valueOf(endChar2));
                            endTime = endTime + 10;
                            endChar3 = end.charAt(2);
                            if(String.valueOf(endChar3).equals("P"))
                            {
                                endTime = endTime + 12;
                                if(endTime == 24)
                                    endTime = 12;
                            }
                            else if(endTime == 12)
                            {
                                endTime = 0;
                            }
                            
                        }
                        else if(Character.isDigit(endChar1))
                        {
                            endTime = Integer.valueOf(String.valueOf(endChar1));
                            endChar3 = start.charAt(1);
                            if(String.valueOf(endChar3).equals("P"))
                                endTime = endTime + 12;
                        }

                        if(Character.isDigit(tempStartChar2))
                        {
                            tempStart = Integer.valueOf(String.valueOf(tempStartChar2));
                            tempStart = tempStart + 10;
                            tempStartChar3 = temp.getStartTime().charAt(2);
                            if(String.valueOf(tempStartChar3).equals("P"))
                            {
                                tempStart = tempStart + 12;
                                if(tempStart == 24)
                                    tempStart = 12;
                            }
                            else if(tempStart == 12)
                            {
                                tempStart = 0;
                            }
                        }
                        else if(Character.isDigit(tempStartChar1))
                        {
                            tempStart = Integer.valueOf(String.valueOf(tempStartChar1));
                            tempStartChar3 = temp.getStartTime().charAt(1);
                            if(String.valueOf(tempStartChar3).equals("P"))
                                tempStart = tempStart + 12;
                        }

                        if(Character.isDigit(tempEndChar2))
                        {
                            tempEnd = Integer.valueOf(String.valueOf(tempEndChar2));
                            tempEnd = tempEnd + 10;
                            tempEndChar3 = temp.getEndTime().charAt(2);
                            if(String.valueOf(tempEndChar3).equals("P"))
                            {
                                tempEnd = tempEnd + 12;
                                if(tempEnd == 24)
                                    tempEnd = 12;
                            }
                            else if(tempEnd == 12)
                            {
                                tempEnd = 0;
                            }
                        }
                        else if(Character.isDigit(tempEndChar1))
                        {
                            tempEnd = Integer.valueOf(String.valueOf(tempEndChar1));
                            tempEndChar3 = temp.getEndTime().charAt(1);
                            if(String.valueOf(tempEndChar3).equals("P"))
                                tempEnd = tempEnd + 12;
                        }
                        
                        if(startTime <= tempStart && endTime > tempStart)
                            return errorLabel = "The event you are trying to add has a conflicting time. Please adjust.";
                        if(startTime >= tempStart && startTime < tempEnd)
                            return errorLabel = "The event you are trying to add has a conflicting time. Please adjust.";
                        if(startTime == tempStart && endTime == tempEnd)
                            return errorLabel = "The event you are trying to add has a conflicting time. Please adjust.";
                    }
                }
            }
        }
        if(errorLabel == null)
            errorLabel = "verified";
        return errorLabel;
    }
}