package view;

import java.io.IOException;


import application.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import util.Logger;

public class SceneTemplateController {
	private Tab myTab;
	private Node myNode;
	
	public void onShow() {
		Logger.log("");
	}
	public void onHide() {
		Logger.log("");
	}
	public void onResume() {
		Logger.log("");
	}
	public void onSuspend() {
		Logger.log("");
	}
	public void hide() {
		SceneController.getInstance().hideRequest(this);
	}
	public void setMyTab(Tab myTab) {
		this.myTab = myTab;
	}
	
	public Tab getMyTab() {
		return myTab;
	}
	
	public void setMyNode(Node myNode) {
		this.myNode = myNode;
	}
	public Node getMyNode() {
		return myNode;
	}
	
	public void close() {
		this.myTab = null;
		this.myNode = null;
	}
}
