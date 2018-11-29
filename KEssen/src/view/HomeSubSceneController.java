package view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import util.Logger;

public class HomeSubSceneController extends SceneTemplateController{	
	@FXML
	private void initialize() {
		Logger.log("");
	}
	
	@FXML
	private void onBackBtn() {
		hide();
		Logger.log("");
	}
}
