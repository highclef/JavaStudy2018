package view;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.StaticModelData;
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
	Tab reviewTab;

	@FXML
	private void initialize() {
		tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> changedTab(newTab));
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

	public void showLoginScene() {
		if (StaticModelData.getInstance().getLoginModel().logined()) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource(MainApp.LOGINFERTIGSCENE));
				Node pane = (Node) loader.load();

				LoginFertigController controller = loader.getController();
				controller.setMyTab(loginTab);
				controller.setMyNode(pane);

				SceneController.getInstance().showAndAllHideRequest(controller);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource(MainApp.LOGINSCENE));
				Node pane = (Node) loader.load();

				LoginController controller = loader.getController();
				controller.setMyTab(loginTab);
				controller.setMyNode(pane);

				SceneController.getInstance().showAndAllHideRequest(controller);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void showCommunityScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.COMMUNITYSCENE));
			Node pane = loader.load();

			CommunitySceneControlloer controller = loader.getController();
			controller.setMyTab(communityTab);
			controller.setMyNode(pane);

			SceneController.getInstance().showAndAllHideRequest(controller);
//			Logger.log("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showInformationScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.RESTAURANTLISTSCENE));
			Node pane = loader.load();

//			InformationOverviewController controller = loader.getController();
			RestaurantListViewController controller = loader.getController();
			controller.setMyTab(informationTab);
			controller.setMyNode(pane);

			SceneController.getInstance().showAndAllHideRequest(controller);
			Logger.log("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showReviewScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.REVIEWFIRST));
			Node pane = loader.load();

			ReviewFirstController controller = loader.getController();
			controller.setMyTab(reviewTab);
			controller.setMyNode(pane);

			SceneController.getInstance().showAndAllHideRequest(controller);
			Logger.log("");
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
		if (reviewTab != null) {
			reviewTab.setContent(null);
		}
	}

	@FXML
	private void onHomeTabSelected() {
		if (homeTab.isSelected()) {
			initTabContent();
			showHomeScene();
		}
	}

	@FXML
	private void onLoginTabSelected() {
		if (loginTab.isSelected()) {
			initTabContent();
			showLoginScene();
		}
	}

	@FXML
	private void onInformationTabSelected() {
		if (informationTab.isSelected()) {
			initTabContent();
			showInformationScene();
		}
	}

	@FXML
	private void onCommunityTabSelected() {
		Logger.log(" selected !! : " + communityTab.isSelected());
		if (communityTab.isSelected()) {
			initTabContent();
			showCommunityScene();
		}
	}

	@FXML
	private void onReviewTabSelected() {
		if (reviewTab.isSelected()) {
			initTabContent();
			showReviewScene();
		}
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
