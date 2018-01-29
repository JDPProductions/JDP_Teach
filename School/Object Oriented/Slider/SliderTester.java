//package slidertester2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
   This program implements a slider that resizes a car shape. 
*/

public class SliderTester
{
    private static int iconWidth = 400;
    private static int iconHeight = 100;
    private static int width = 100;
    private static int min = -100, init = 0, max = 100;
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        SizableShape shape = new CarShape(0, 0, width);
      
        ShapeIcon icon = new ShapeIcon(shape, iconWidth, iconHeight);

        JLabel label = new JLabel(icon);
        
        frame.setLayout(new BorderLayout());
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        frame.add(label, BorderLayout.CENTER);
        
        JLabel sliderLabel = new JLabel("Size Amount", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        //Create the slider.
        JSlider sliderSizer = new JSlider(JSlider.HORIZONTAL, min, max, init);
        sliderSizer.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) 
            {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) 
                {
                    int width = (int) source.getValue();
                    shape.resize(width);
                    label.repaint();
                }
            }
        });
        Font font = new Font("Serif", Font.ITALIC, 15);
        sliderSizer.setFont(font);
        //frame.add(sliderSizer, BorderLayout.SOUTH);
        frame.add(sliderSizer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}