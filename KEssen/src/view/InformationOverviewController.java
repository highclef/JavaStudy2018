package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Information;

public class InformationOverviewController extends SceneTemplateController{

	@FXML
	private TableView<Information> informationTable;

	@FXML
	private Label nameLabel;
	@FXML
	private Label scoreLabel;
	@FXML
	private VBox reviewsList;
	@FXML
	private VBox menuItemsList;

	public InformationOverviewController() {
	}
	
	@FXML
	private void initialize() {
		initData();
	}
	
	public void initData() {
		Information i = new Information();
		nameLabel.setText(i.getName());
		scoreLabel.setText(Integer.toString(i.getScore()));
		menuItemsList.getChildren().add(new Label(i.getMenuItems()[0]));
		menuItemsList.getChildren().add(new Label(i.getMenuItems()[1]));
		menuItemsList.getChildren().add(new Label(i.getMenuItems()[2]));
		
		reviewsList.getChildren().add(new Label(i.getReviews()[0]));
		reviewsList.getChildren().add(new Label(i.getReviews()[1]));

	}
}