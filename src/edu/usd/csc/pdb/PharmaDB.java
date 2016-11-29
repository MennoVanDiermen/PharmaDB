/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.csc.pdb;

import edu.usd.csc.pdb.test.FillDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public class PharmaDB extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //FillDB.fill();
        
        Parent root = FXMLLoader.load(getClass().getResource("PharmaView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
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