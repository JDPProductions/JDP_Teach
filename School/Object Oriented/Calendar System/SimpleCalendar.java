//package simplecalendar;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;


/**
 * Main class that displays the views and contains the controllers
 * @author Justin
 */
public class SimpleCalendar 
{
    public static void main(String[] args) throws IOException 
    {
        //Initialize model
        MyCalendarModel model = new MyCalendarModel();
        
        //Load event list
        model.loadEvents();
        
        //Set universal font for whole frame 
        Font font = new Font("Helvetica", Font.PLAIN, 18);
        
        
        //START OF FRAME AND LAYOUT PANELS OF FRAME
        
        JFrame frame = new JFrame();
        frame.setTitle("Simple GUI Calendar");
        frame.setLayout(new BorderLayout());
        
        JPanel top = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JPanel bottom = new JPanel();
        
        
        //START OF BOTTOM PANEL OF FRAME - visible when create is clicked
        
        bottom.setPreferredSize(new Dimension(1000, 150));
        bottom.setBackground(Color.WHITE);
        bottom.setLayout(new BorderLayout());
        
        JPanel temp = new JPanel();
        temp.setLayout(new BorderLayout());
        
        JTextField eventTitle = new JTextField();
        JTextField eventDate = new JTextField();
        JTextField eventStart = new JTextField();
        JTextField eventEnd = new JTextField();
        JLabel titleLabel = new JLabel("Event title");
        JLabel dateLabel = new JLabel("   Event Date   ");
        JLabel startLabel = new JLabel("Start Time ");
        JLabel endLabel = new JLabel(" End Time");
        JLabel preposition = new JLabel("to");
        JButton submitButton = new JButton("Save");
        
        JPanel tempNorth = new JPanel(new GridBagLayout());
        JPanel tempCenter = new JPanel(new GridBagLayout());
        JPanel tempSouth = new JPanel();
        JLabel verifiedLabel = new JLabel("Pending");
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 1;
        c.gridy = 0;
        titleLabel.setFont(font);
        tempNorth.add(titleLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        eventTitle.setColumns(40);
        eventTitle.setFont(font);
        tempNorth.add(eventTitle, c);
        c.gridx=2;
        c.gridy=1;
        submitButton.setFont(font);
        tempNorth.add(submitButton, c);
        c.gridx = 1;
        c.gridy = 1;
        dateLabel.setFont(font);
        tempCenter.add(dateLabel, c);
        c.gridx = 3;
        c.gridy = 1;
        startLabel.setFont(font);
        tempCenter.add(startLabel, c);
        c.gridx = 4;
        c.gridy = 1;
        preposition.setFont(font);
        tempCenter.add(preposition, c);
        c.gridx = 5;
        c.gridy = 1;
        endLabel.setFont(font);
        tempCenter.add(endLabel, c);
        c.gridx=1;
        c.gridy=2;
        eventDate.setColumns(6);
        eventDate.setFont(font);
        tempCenter.add(eventDate, c);
        c.gridx=3;
        c.gridy=2;
        eventStart.setColumns(5);
        eventStart.setFont(font);
        tempCenter.add(eventStart, c);
        c.gridx=5;
        c.gridy=2;
        eventEnd.setColumns(5);
        eventEnd.setFont(font);
        tempCenter.add(eventEnd, c);
        
        tempSouth.add(verifiedLabel);
        
        temp.add(tempNorth, BorderLayout.NORTH);
        temp.add(tempCenter, BorderLayout.CENTER);
        temp.add(tempSouth, BorderLayout.SOUTH);
        
        bottom.add(temp, BorderLayout.CENTER);
        temp.setVisible(false);
        
        
        //START OF TOP PANEL OF FRAME - header
        
        top.setLayout(new BorderLayout());
        top.setPreferredSize(new Dimension(1000, 100));
        top.setBackground(Color.WHITE);
        
        JPanel topBuffer = new JPanel();
        JPanel topLeftSubpanel = new JPanel();
        JPanel topRightSubpanel = new JPanel();
        JPanel bottomBuffer = new JPanel();
        
        topBuffer.setPreferredSize(new Dimension(1,25));
        topLeftSubpanel.setPreferredSize(new Dimension(500,70));
        topRightSubpanel.setPreferredSize(new Dimension(500,70));
        bottomBuffer.setPreferredSize(new Dimension(1,5));
        
        topBuffer.setBackground(Color.WHITE);
        topLeftSubpanel.setBackground(Color.WHITE);
        topRightSubpanel.setBackground(Color.WHITE);
        bottomBuffer.setBackground(Color.GRAY);
        
        top.add(topBuffer, BorderLayout.NORTH);
        top.add(topLeftSubpanel, BorderLayout.WEST);
        top.add(topRightSubpanel, BorderLayout.EAST);
        top.add(bottomBuffer, BorderLayout.SOUTH);
        
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");
        
        prevButton.setFont(font);
        nextButton.setFont(font);
        
        prevButton.setBackground(Color.getHSBColor(26, 0, 55));
        nextButton.setBackground(Color.getHSBColor(26, 0, 55));
        
        topLeftSubpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        prevButton.setPreferredSize(new Dimension(50,40));
        nextButton.setPreferredSize(new Dimension(50,40));
        
        topLeftSubpanel.add(prevButton);
        topLeftSubpanel.add(nextButton);
        
        JButton quitButton = new JButton("Quit");
        
        quitButton.setFont(font);
        
        quitButton.setBackground(Color.getColor("test", Color.HSBtoRGB(0, 0, 26)));
        quitButton.setPreferredSize(new Dimension(100,40));
        
        topRightSubpanel.add(quitButton);
        
        
        //START OF RIGHT PANEL OF FRAME - calendar day and event view
        
        right.setPreferredSize(new Dimension(500, 500));
        right.setBackground(Color.LIGHT_GRAY);
        right.setLayout(new BorderLayout());
        
        JPanel rightField = new JPanel();
        JScrollPane scroll = new JScrollPane(rightField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        rightField.setLayout(new BorderLayout());
        
        
        JLabel dayLabel = new JLabel();
        
        dayLabel.setText(model.getDayViewLabel());    //ISSUE HERE
        dayLabel.setFont(font);
        
        
        right.add(scroll, BorderLayout.CENTER);
        right.add(dayLabel, BorderLayout.NORTH);
        
        
        //START OF LEFT PANEL OF FRAME - calendar month view
        
        left.setLayout(new BorderLayout());
        left.setPreferredSize(new Dimension(500, 500));
        left.setBackground(Color.WHITE);
        
        JPanel leftTopSubpanel = new JPanel();
        JPanel leftBottomSubpanel2 = new JPanel();
        JPanel leftBottomSubpanel = new JPanel();
        
        leftTopSubpanel.setPreferredSize(new Dimension(500, 50));
        leftBottomSubpanel2.setPreferredSize(new Dimension(500, 450));
        
        leftTopSubpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JButton createButton = new JButton("Create");
        
        createButton.setPreferredSize(new Dimension(100, 40));
        
        createButton.setFont(font);
        
        leftTopSubpanel.add(createButton);
        
        leftBottomSubpanel2.setLayout(new BorderLayout());
        
        MyCalendarView calendarView = new MyCalendarView(leftBottomSubpanel2, model, dayLabel);
        
        leftBottomSubpanel.add(leftBottomSubpanel2);
        
        left.add(leftTopSubpanel, BorderLayout.NORTH);
        left.add(leftBottomSubpanel2, BorderLayout.SOUTH);
        
        
        //ADD THE CONTROLLERS
        prevButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
                int previousMonth = model.getMonth();
                model.decrementCalendar();
                
                if(model.getMonth() < previousMonth)
                {
                    leftBottomSubpanel2.removeAll();
                    MyCalendarView decrementCalendar = new MyCalendarView(leftBottomSubpanel2, model, dayLabel);
                    leftBottomSubpanel2.invalidate();
                    leftBottomSubpanel2.validate();
                }
                dayLabel.setText(model.getDayViewLabel());
           }
        });
        
        nextButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
                int nextMonth = model.getMonth();
                model.incrementCalendar();
                
                if(model.getMonth() > nextMonth)
                {
                    leftBottomSubpanel2.removeAll();
                    MyCalendarView incrementCalendar = new MyCalendarView(leftBottomSubpanel2, model, dayLabel);
                    leftBottomSubpanel2.invalidate();
                    leftBottomSubpanel2.validate();

                }
                dayLabel.setText(model.getDayViewLabel());
           }
        });
        
        quitButton.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {               
                model.storeAndDispose();
                frame.dispose();
           }
        });
        
        createButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                eventTitle.setText(null);
                eventDate.setText(model.getMonthValueString() + "/" + model.getDayValueString() + "/" + model.getYearString());
                eventStart.setText(null);
                eventEnd.setText(null);
                temp.setVisible(true);
            }
        });
        
        submitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                verifiedLabel.setText("Pending");
                
                eventDate.setText(model.getMonthValueString() + "/" + model.getDayValueString() + "/" + model.getYearString());
                
                String tempTitle = eventTitle.getText();
                String tempDate = eventDate.getText();
                String tempStart = eventStart.getText();
                String tempEnd = eventEnd.getText();
              
                String verifiedString = model.textFieldsVerified(tempTitle, tempDate, tempStart, tempEnd);
               
                if(verifiedString.equals("verified"))
                {
                    model.storeEvent(tempTitle, tempDate, tempStart, tempEnd);
                    temp.setVisible(false);
                    
                    try 
                    {
                        model.printEvents(rightField, eventDate.getText());
                    } 
                    catch (BadLocationException ex) 
                    {
                        Logger.getLogger(SimpleCalendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    verifiedLabel.setText(verifiedString);
                }               
           }
        });
        
        dayLabel.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pce)
            {
                eventDate.setText(model.getMonthValueString() + "/" + model.getDayValueString() + "/" + model.getYearString());
                
                try {
                    String dateOf = eventDate.getText();
                    model.printEvents(rightField, dateOf);
                } catch (BadLocationException ex) {
                    Logger.getLogger(SimpleCalendar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //ADD THE TOP LEVEL LAYOUT PANELS TO THE FRAME
        frame.add(top, BorderLayout.NORTH);
        frame.add(left, BorderLayout.WEST);
        frame.add(right, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);
        
        //DISPLAY FRAME AND SET FRAME SETTINGS
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}