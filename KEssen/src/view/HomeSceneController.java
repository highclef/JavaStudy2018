package view;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import util.Logger;

public class HomeSceneController extends SceneTemplateController {
	@FXML
	private void initialize() {
		Logger.log("");
	}

	@FXML
	private void onNextBtn() {
		showHomeSubScene();
		Logger.log("");
	}

	public void showHomeSubScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.HOMESUBSCENE));
			AnchorPane pane = (AnchorPane) loader.load();

			HomeSubSceneController controller = loader.getController();
			controller.setMyTab(super.getMyTab());
			controller.setMyNode(pane);

			SceneController.getInstance().showRequest(controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
