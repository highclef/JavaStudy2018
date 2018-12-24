package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import util.Logger;

public class ReviewPostingErrorViewController extends SceneTemplateController {
	
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void closeReviewPostingErrorView(Event event) {
		
		// Close current window
		((Node)(event.getSource())).getScene().getWindow().hide();    
		Logger.log("");
	}

}
