package view;

import view.ReviewFirstController;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import util.Logger;

public class ReviewFirstController extends SceneTemplateController {

	@FXML
	private void initialize() {
		Logger.log("");
	}

	@FXML
	private void RegisterButton() {
		showReviewSecond();
		Logger.log("");
	}

	public void showReviewSecond() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.REVIEWSECOND));
			SplitPane pane = (SplitPane) loader.load();

			ReviewSecondController controller = loader.getController();
			controller.setMyTab(super.getMyTab());
			controller.setMyNode(pane);

			SceneController.getInstance().showRequest(controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
