package view;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import util.Logger;

public class MainSceneController {
	@FXML
	TabPane tabPane;
	@FXML
	Tab homeTab;
	@FXML
	Tab loginTab;
	@FXML
	Tab informationTab;
	@FXML
	Tab communityTab;

	@FXML
	private void initialize() {
		tabPane.getSelectionModel().selectedItemProperty().addListener(
				(ov, oldTab, newTab) -> changedTab(newTab));
		Logger.log("");
	}

	public void changedTab(Tab tab) {
		Logger.log(tab.getId());
	}
	public void showHomeScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.HOMESCENE));
			BorderPane pane = (BorderPane) loader.load();

			HomeSceneController controller = loader.getController();
			controller.setMyTab(homeTab);
			controller.setMyNode(pane);
			
			SceneController.getInstance().showAndAllHideRequest(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initTabContent() {
		if (homeTab != null) {
			homeTab.setContent(null);
		}
		if (loginTab != null) {
			loginTab.setContent(null);
		}
		if (informationTab != null) {
			informationTab.setContent(null);
		}
		if (communityTab != null) {
			communityTab.setContent(null);
		}
	}

	@FXML
	private void onHomeTabSelected() {
		initTabContent();
		showHomeScene();
	}
	
	@FXML
	private void onLoginTabSelected() {
		initTabContent();
	}
	
	@FXML
	private void onInformationTabSelected() {
		initTabContent();
	}
	
	@FXML
	private void onCommunityTabSelected() {
		initTabContent();
	}
	
	@FXML
	private void onModifyInfo() {
		Logger.log("");
	}
	
	@FXML
	private void onMessageBox() {
		Logger.log("");
	}
}
