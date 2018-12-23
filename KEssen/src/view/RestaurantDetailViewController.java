package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.RestaurantModel;
import util.Logger;

public class RestaurantDetailViewController extends SceneTemplateController{

	ObservableList<String> list = FXCollections.observableArrayList();
	
	RestaurantModel rm;
	
	public RestaurantModel getRm() {
		return rm;
	}

	public void setRm(RestaurantModel rm) {
		this.rm = rm;
	}
	
	@FXML
	private TableView<RestaurantModel> restaurantTableView;
	@FXML
	private TableView<RestaurantModel> menuTableView;

	@FXML
	private Label nameLabel;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label addressLabel;
	
	@FXML
	private ListView<String> reviewList;
	@FXML
	private ListView<String> menuItemList;

	public RestaurantDetailViewController() {
	}
	
	@FXML
	private void initialize() {

//		initData();
	}
	
	public void initData() {
		
//		nameLabel.setText(rm.getName());
//		scoreLabel.setText(Integer.toString(rm.getScore()));
//		menuItemList.getChildren().add(new Label(i.getMenuItems()[0]));
//		menuItemList.getChildren().add(new Label(i.getMenuItems()[1]));
//		menuItemList.getChildren().add(new Label(i.getMenuItems()[2]));
//		
//		reviewList.getChildren().add(new Label(i.getReviews()[0]));
//		reviewList.getChildren().add(new Label(i.getReviews()[1]));

	}
	
	public void loadMenuData() {
		
		nameLabel.setText(rm.getName());
		scoreLabel.setText(Integer.toString(rm.getScore()));
		Logger.log("Selected Restaurant: " + rm.getName());
		
		if (rm.getName().contains("A")) {
			list.removeAll(list);
			String a = "AM1";
			String b = "AM2";
			String c = "AM3";
			String d = "AM4";
			list.addAll(a, b, c, d);
			menuItemList.getItems().addAll(list);
			
		} else if (rm.getName().contains("B")) {
			list.removeAll(list);
			String a = "BM1";
			String b = "BM2";
			String c = "BM3";
			String d = "BM4";
			list.addAll(a, b, c, d);
			menuItemList.getItems().addAll(list);
			
		} else if (rm.getName().contains("DU")) {
			list.removeAll(list);
			String a = "DUM1";
			String b = "DUM2";
			String c = "DUM3";
			String d = "DUM4";
			list.addAll(a, b, c, d);
			menuItemList.getItems().addAll(list);
			
		} else {
			list.removeAll(list);
			String a = "DM1";
			String b = "DM2";
			String c = "DM3";
			String d = "DM4";
			list.addAll(a, b, c, d);
			menuItemList.getItems().addAll(list);
		}
	}
	
	public void loadReviewData() {
		Logger.log("");
		list.removeAll(list);
		String a = "R1";
		String b = "R2";
		String c = "R3";
		String d = "R4";
		list.addAll(a, b, c, d);
		reviewList.getItems().addAll(list);
	}
	
	@FXML
	private void displaySelectedMenuTableView(MouseEvent event) {
		String selectedMenu = menuItemList.getSelectionModel().getSelectedItem();
		Logger.log("");
	}
	
	@FXML
	private void onBackButton() {
		hide();
		Logger.log("");
	}
	
}