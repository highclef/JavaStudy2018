package view;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import util.Logger;

public class RestaurantListViewController extends SceneTemplateController {
	
	@FXML
	private void onListButton() {
		showListOverview();
		Logger.log("");
	}
	
	public void showListOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.RESTAURANTDETAILSCENE));
			AnchorPane pane = (AnchorPane) loader.load();

			RestaurantDetailViewController controller = loader.getController();
			controller.setMyTab(super.getMyTab());
			controller.setMyNode(pane);

			SceneController.getInstance().showRequest(controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
