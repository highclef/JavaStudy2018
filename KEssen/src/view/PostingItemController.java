package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class PostingItemController {
	@FXML
	Label id;
	@FXML
	TextArea ta;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setID(String id) {
		this.id.setText(id);
	}
	
	public void setTextArea(String text) {
		this.ta.setText(text);
	}
}
