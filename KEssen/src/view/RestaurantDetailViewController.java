package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	}
	
	public void loadMenuData() {
		
		nameLabel.setText(rm.getName());
		scoreLabel.setText(Integer.toString(rm.getScore()));
		Logger.log("Selected Restaurant: " + rm.getName());
		
		RestaurantListViewController.RESTAURANTNAME = rm.getName();
		
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
		
		// TODO: if(selectedMenu == a) {...} else if() 분기문 -> 메뉴 이미지 테이블에 출력
	}
	
	@FXML
	private void displaySelectedReviewDetail(MouseEvent event) {
		
		Logger.log("");
		
		// TODO: 리스트에서 리뷰 선택 시 어떻게 할 것인지
	}
	
	@FXML
	private void displayReviewPostingView() throws IOException {
		
		// Open New Window
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("ReviewPostingView.fxml"));
				Scene scene = new Scene(root);		
				
				primaryStage.setTitle("Review posting");
				primaryStage.initModality(Modality.WINDOW_MODAL);
				primaryStage.initOwner(this.getMyNode().getScene().getWindow());
				primaryStage.setScene(scene);
				primaryStage.show();
	}
	
	@FXML
	private void onBackButton() {
		
		// Back
		hide();
		Logger.log("");
	}
	
}