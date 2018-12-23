package view;

import java.io.IOException;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Logger;

public class RestaurantListViewController extends SceneTemplateController {
	
	ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML
	private ListView<String> cityList;
	
	@FXML
	private ListView<String> restaurantList;	

	@FXML
	public void initialize() {
		loadCityData();
	}
	
	private void loadCityData() {
		list.removeAll(list);
		String a = "Aachen";
		String b = "Bochum";
		String c = "Duisburg";
		String d = "Duesseldorf";
		list.addAll(a, b, c, d);
		cityList.getItems().addAll(list);
	}
	
	@FXML
	private void displaySelectedItems(MouseEvent event) {
		String selectedCity = cityList.getSelectionModel().getSelectedItem();
		Logger.log("");
		
		if(selectedCity == null || selectedCity.isEmpty()) {
			// TODO: No city is selected
		}
		else {
			loadRestaurantData(selectedCity);
		}
	}
	
	private void loadRestaurantData(String selectedCity) {
		list.removeAll(list);
		restaurantList.setItems(list);
		Logger.log("");
		
		if (selectedCity == "Aachen") {
			
			String a = "A1";
			String b = "A2";
			String c = "A3";
			String d = "A4";
			list.addAll(a, b, c, d);
			restaurantList.getItems().addAll(list);
			
		} else if (selectedCity == "Bochum") {

			String a = "B1";
			String b = "B2";
			String c = "B3";
			String d = "B4";
			list.addAll(a, b, c, d);
			restaurantList.getItems().addAll(list);
			
		} else if (selectedCity == "Duisburg") {

			String a = "DU1";
			String b = "DU2";
			String c = "DU3";
			String d = "DU4";
			list.addAll(a, b, c, d);
			restaurantList.getItems().addAll(list);
			
		} else if (selectedCity == "Duesseldorf") {

			String a = "D1";
			String b = "D2";
			String c = "D3";
			String d = "D4";
			list.addAll(a, b, c, d);
			restaurantList.getItems().addAll(list);
		} 

	}
	
	@FXML
	private void showRestaurantDetails(MouseEvent event) {
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
