//package mvc;

import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//The Model Class
public class MVCModel {
    private ArrayList<String> text;
    private ArrayList<ChangeListener> listeners;
    
    /**
      Constructor for MVCModel object
   */
    public MVCModel(){
        text = new ArrayList<>();
        listeners = new ArrayList<>();
    }
    
    /**
     * Adds data to MVC database
     * @param text 
     */
    public void addText(String text){
        this.text.add(text);
        
        ChangeEvent event = new ChangeEvent(this);
        for(ChangeListener listener: listeners){
            listener.stateChanged(event);
        }
    }

    /**
     * Retrieves the line test 
     * @return 
     */
    public String getLine(){
        String line = "";
      
        for(String str: text ){		
            line += str + "\n";	
        }   
        return line;
    }

    /**
     * Attaches a listener to the model
     * @param listener 
     */
    public void attach(ChangeListener listener){
        listeners.add(listener);
    }
}