package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PostWritingSceneController {
	private boolean isCancel;
	private Stage stage;
	@FXML
	Label id;
	@FXML
	TextArea postText;
	
	public PostWritingSceneController() {
		// TODO Auto-generated constructor stub
		isCancel = false;
	}
	@FXML
	private void initialize() {

	}
	
	@FXML
	private void onOkBtn() {
		isCancel = false;
		stage.close();
	}
	
	@FXML
	private void onCancelBtn() {
		isCancel = true;
		stage.close();
	}

	boolean isCanceled() {
		return isCancel;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Stage getStage() {
		return stage;
	}
	public void setId(String id) {
		this.id.setText(id);
	}
	public String getId() {
		return id.getText();
	}
	public void setTextPost(String text) {
		this.postText.setText(text);
	}
	public String getTextPost() {
		return postText.getText();
	}
}
