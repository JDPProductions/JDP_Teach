//package simplecalendar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * View class to only control the appearance of the Calendar month view
 */
public class MyCalendarView extends JPanel
{
    int offset;
    GregorianCalendar date = new GregorianCalendar();
    GregorianCalendar todayDate = new GregorianCalendar();
    
    /**
     * Constructor to create the view of the calendar
     * @param p: panel to insert the calendar on
     * @param model: model that the calendar is using
     * @param label: day label for controller purposes
     */
    public MyCalendarView(JPanel p, MyCalendarModel model, JLabel label)
    {
        date.set(model.getYear(), model.getMonth(), model.getDay());
        
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new GridLayout(0, 7));
        Font font = new Font("Helvetica", Font.PLAIN, 18);
        
        for(int i = 1; i <= 7; i++)
        {
            JLabel day = new JLabel();
            day.setText("  " + model.printEnumDay(i-1));
            day.setFont(font);
            subpanel.add(day);
        }
        
        model.setOffset(date);
        int offset = model.getOffest();
        for(int j = 1; j <= model.dayInMonth() ; j++)
        {
            
            while(offset != 0)
            {
                JLabel offsetLabel = new JLabel();
                subpanel.add(offsetLabel);
                offset--;
            }       
            JButton dateButtons = new JButton();
            dateButtons.setFont(font);
            subpanel.add(dateButtons);
            dateButtons.setText(Integer.toString(j));
            dateButtons.setOpaque(false);
            dateButtons.setContentAreaFilled(false);
            
            if(todayDate.get(Calendar.DAY_OF_MONTH) == Integer.valueOf(dateButtons.getText()) && todayDate.get(Calendar.MONTH) == model.getMonth())
            {
                dateButtons.setOpaque(true);
                dateButtons.setBorderPainted(true);
                dateButtons.setBackground(Color.GRAY);
            }
            else
                dateButtons.setBorderPainted(false);
            
            //Controller for each button on Calendar
            dateButtons.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    dateButtons.setBorderPainted(true);
                    
                    model.setDay(Integer.valueOf(dateButtons.getText()));
                    label.setText(model.getDayViewLabel());
                    
                    int dateButtonText = Integer.parseInt(dateButtons.getText());
                    
                    label.addPropertyChangeListener(new PropertyChangeListener()
                    {
                       @Override
                       public void propertyChange(PropertyChangeEvent pce)
                       {
                           int month = model.getMonth();
                           if(model.getDay() == Integer.parseInt(dateButtons.getText()))
                            {
                                dateButtons.setBorderPainted(true);
                            }
                            else
                                dateButtons.setBorderPainted(false);
                            dateButtons.updateUI();
                       }
                    });
                }
            });
        }
        p.add(subpanel);
    }
}