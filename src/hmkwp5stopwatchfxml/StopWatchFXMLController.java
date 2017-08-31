/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmkwp5stopwatchfxml;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HG King
 */
public class StopWatchFXMLController implements Initializable {

    public String time = "yeet";
    public Label label;
    
    private static int secondsElapsed = 0; 
    private static int minutesElapsed = 0;
    
    //values for how many ticks occur every second/minute
    private final double tickPerSecond = 1;
    private final double tickPerMinute = tickPerSecond / 60;
    
    //dont know why netbeans is beefin about my naming convention here
    private static final double anglePerTickSeconds = 6.0;
    
    //timeline and keyframe used by the clocks
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    //ui parts that are affected by the java code    
    public Button startButton, stopButton, resetButton;
    public ImageView minuteHand, secondHand;
    
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        setupTimer();
    }   
    @FXML
    public void setupTimer() {
        boolean running = false;
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                running = true;
                timeline.stop();
            }
        }

        keyFrame = new KeyFrame(Duration.millis(tickPerSecond * 1000), (ActionEvent event) -> {
            updateClock();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE); 
        
        if (running) {
            timeline.play();
        }
        
        reset();
    }
    
    public void useRealTime()  {
        
        Date minutes = new Date( );
        SimpleDateFormat min = 
        new SimpleDateFormat ("m");
        
        Date seconds = new Date( );
        SimpleDateFormat sec = 
        new SimpleDateFormat ("s");

        secondsElapsed = Integer.parseInt(sec.format(seconds));
        minutesElapsed = Integer.parseInt(min.format(minutes));
        
        timeline.play();
        
    }
    
    @FXML
    private void updateClock()  {
        secondsElapsed += tickPerSecond;
        minutesElapsed += tickPerMinute;
           
        //reset to 0 after 60
        if(secondsElapsed >= 60)  {
            secondsElapsed = 0;
            minutesElapsed++;
        }    
        if(minutesElapsed >= 60)  {
            minutesElapsed = 0;
        }
        
        //apply rotation to time passed
        double rotationSeconds = secondsElapsed * anglePerTickSeconds;
        double rotationMinutes = minutesElapsed * anglePerTickSeconds;
        secondHand.setRotate(rotationSeconds);
        minuteHand.setRotate(rotationMinutes);
        
        //update string and label
        time = String.format("%2d:%2d", minutesElapsed, secondsElapsed);
        label.setText(time);
    }
    
    private void start() {
        timeline.play();
    }
    
    private void stop() {
        timeline.stop();
    }
    
    private void reset() {
        stop();
        
        secondsElapsed = 00;
        minutesElapsed = 00;
        
        secondHand.setRotate(0);
        minuteHand.setRotate(0);
        
        time = String.format("%2d:%2d", minutesElapsed, secondsElapsed);
        
        label.setText(time);

    }
    
    private boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        } 
        return false;
    }
    
    @FXML
    public void handleStart(ActionEvent event)  {
        start();
    }
    
    @FXML
    public void handleStop(ActionEvent event)  {
        stop();
    }
    
    @FXML
    public void handleReset(ActionEvent event)  {
        reset();
    }
    
    @FXML
    public void handleSetRealTime(ActionEvent event)  {
        useRealTime();
    }
    
}
