package view;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PostingModel;

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

	@FXML
	private void onModifyBtn() {
		csc.modifyItem(modelId);
	}
}
