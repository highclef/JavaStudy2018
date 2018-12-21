package application;
	
import javafx.application.Application;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.stage.Stage;
import network.NetworkManager;
import util.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;



public class Main extends Application {
	
	

	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("N013.fxml"));
		
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("회원관리프로그램입니다");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		NetworkManager nm = NetworkManager.getInstance();
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Logger.log("connect server!");
				if (NetworkManager.getInstance().connect()) {
					timer.cancel();
				}
			}
		};

		timer.scheduleAtFixedRate(timerTask, 0, 5000);

		launch(args);
	}
	
}
