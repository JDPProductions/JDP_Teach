//package mvc;

/**
 * Program to test the MVC pattern
 * @author Justin
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Class to test the mvc model.
 * This contains the view and controller /w a controller attachment to the model for controller communication
 */
public class MVCTester {
     public static void main(String[] args){
        //JFrame is the View
        JFrame frame = new JFrame("MVC Line Addition");
        frame.setSize(200, 400);
        //JTextField is the View
        final JTextField txtField = new JTextField();
        
        final MVCModel text = new MVCModel();
       
        //JButton is the Controller
        JButton button = new JButton("Add");
        
        //Action Listener is the Controller to update the model
        button.addActionListener(new 
            ActionListener(){
                public void actionPerformed(ActionEvent event){
                   text.addText(txtField.getText());
                }
        });
        
        final JTextArea textArea = new JTextArea(15, 20);
        
        //Change Listener is the Controller to update the model
        ChangeListener listener = new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e){
	        textArea.setText(text.getLine());
	    }

	};
        text.attach(listener);
        
        //Adds the elements to the view
        frame.add(txtField, BorderLayout.SOUTH);
        frame.add(textArea, BorderLayout.CENTER);
        frame.add(button, BorderLayout.NORTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
