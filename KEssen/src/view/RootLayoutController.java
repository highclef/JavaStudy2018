package view;

import application.MainApp;
import javafx.fxml.FXML;
import util.Logger;

public class RootLayoutController {
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize() {
//		System.out.println("RootLayoutController initialize");
		Logger.log("");
	}
}
