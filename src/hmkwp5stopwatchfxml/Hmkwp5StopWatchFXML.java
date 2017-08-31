/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmkwp5stopwatchfxml;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class Hmkwp5StopWatchFXML extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        //first attempt at adding this font into the app
        //doesn't return null when called so it should be working
        //not sure what to do once its called
        //will try using stylesheets
        /*
        if(Font.loadFont(
        Hmkwp5StopWatchFXML.class.getResource("Computerfont.ttf").toExternalForm(), 
        10
        ) == null)  {
            System.out.println("failed to load font");
        }
        */
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("StopWatchFXML.fxml"));
             
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
