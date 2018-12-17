package view;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PostingItemController {
	private CommunitySceneControlloer csc;
	private int id;
	@FXML
	Label username;
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

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username.setText(username);
	}

	public void setTextArea(String text) {
		this.ta.setText(text);
	}

	public String getUsername() {
		return username.getText();
	}

	public String getTextArea() {
		return ta.getText();
	}

	public void setButtonBarVisible(boolean visible) {
		buttonBar.setVisible(visible);
	}

	@FXML
	private void onDeleteBtn() {
//		csc.deleteItem(id);
		csc.sendDeleteItem(id);
	}

	@FXML
	private void onModifyBtn() {
		csc.modifyItemDialog(id);
	}
}
