package view;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import model.StaticModelData;

public class LoginFertigController extends SceneTemplateController{
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void onAusloggenBtn() {
		StaticModelData.getInstance().getLoginModel().setLogined(false);
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.LOGINSCENE));
			Node pane = (Node) loader.load();

			LoginController controller = loader.getController();
			controller.setMyTab(getMyTab());
			controller.setMyNode(pane);

			SceneController.getInstance().showAndAllHideRequest(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
