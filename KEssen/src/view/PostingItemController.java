package view;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PostingItemController {
	private CommunitySceneControlloer csc;
	private int modelId;
	@FXML
	Label id;
	@FXML
	TextArea ta;
	@FXML
	ButtonBar buttonBar;

	@FXML
	private void initialize() {

	}

	public void setCommunitySceneController(CommunitySceneControlloer c) {
		csc = c;
	}
	
	public void setModelId(int id) {
		modelId = id;
	}
	public int getModelId() {
		return modelId;
	}
	public void setID(String id) {
		this.id.setText(id);
	}

	public void setTextArea(String text) {
		this.ta.setText(text);
	}
	
	public String getID() {
		return id.getText();
	}
	public String getTextArea() {
		return ta.getText();
	}

	public void setButtonBarVisible(boolean visible) {
		buttonBar.setVisible(visible);
	}
	
	@FXML
	private void onDeleteBtn() {
		csc.deleteItem(modelId);
	}
}
