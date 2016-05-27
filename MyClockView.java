package myclock;

import java.awt.*;
import javax.swing.*;

/**
 * This is the view, its only job is to display what the user sees.
 * It does not perform any calculations, just passes the information 
 * to the user in a graphical format.
 * @author RKOwen
 */
public class MyClockView  extends JFrame{
    
    //Variables that control the clock size and text
    //private int clockWidth = 800;
    //private int clockHeight = 800;
    private JTextField timeField;

    
    MyClockView()
    {
        //Give Window Frame a Title...
        super("Digital Clock by Pajama Programmer");
        
        //Closing the window will end the program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(clockWidth, clockHeight);
        this.setResizable(false);   
        this.setLocation(100, 100);
  
        //timeField is used for displaying the time
        timeField = new JTextField(10);
        timeField.setBorder(null);  //there was an ever so small gray border, this gets rid of it
        timeField.setBackground(Color.BLACK);   
        timeField.setForeground(Color.GREEN);
        timeField.setHorizontalAlignment(JTextField.CENTER);
        timeField.getCaret().setVisible(false);
        timeField.setEditable(false);
        timeField.setFont(new Font("Comic Sans MS", Font.BOLD, 75));    //The Clock Text...
        
        //Content pane for the clock face
        JPanel clockPanel = new JPanel();
        clockPanel.setBackground(Color.GREEN);
        clockPanel.add(timeField);
        
        this.add(clockPanel);
        this.pack();   //Just pack it to the size needed...   
        this.setVisible(true);
    }
    
    //This method simply updates the textField to display the time it is given.
    public void updateDisplay(int h, int m, int s, boolean isAM)
    {
        String AM_PM;
        if(isAM)
            AM_PM = "AM";
        else
            AM_PM = "PM";
        
        //String.format("%02d"... forces the numbers to be padded on the left with a zero
        timeField.setText("" + String.format("%02d", h) + ":" + String.format("%02d", m) + ":" 
                + String.format("%02d", s) + " " + AM_PM);
    }   
}
