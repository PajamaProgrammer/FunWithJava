package myclock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * MyClockController coordinates the actions between theView and theModel.
 * A timer is used to generate an action every second. Every second the Controller
 * ask theModel to update the time and then will send the updated time to theView for display.
 * @author RKOwen
 */
public class MyClockController {
    
    private MyClockView theView;
    private MyClockModel theModel;
    Timer controlTimer;
    
    //Constructor takes starts a timer to drive the interactions between theView and TheModel
    public MyClockController(MyClockView theView, MyClockModel theModel)
    {
        this.theModel = theModel;
        this.theView = theView;
        
        //Start the Timer, timer will send an action every second, and implement an object of ClockListener
        controlTimer = new Timer(1000, new ClockListener());
        controlTimer.start();
    }
    
    //Responds to Timer Events
    class ClockListener implements ActionListener
    {
        //Upon contruction, ask theModel to updateTime and theView to updateDisplay...
        ClockListener ()
        {
            theModel.updateTime();
            theView.updateDisplay(theModel.getHour(), theModel.getMinute(), theModel.getSecond(), theModel.isAM());
        }
        //Every Timer Event, ask theModel to updateTime and theView to updateDisplay...
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            theModel.updateTime();
            theView.updateDisplay(theModel.getHour(), theModel.getMinute(), theModel.getSecond(), theModel.isAM());
            //System.out.println(theModel.getHour() + " : " + theModel.getMinute() + " : " + theModel.getSecond());
        } 
    }
}
