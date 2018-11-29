package view;

import java.util.Stack;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import util.Logger;

class TabScene {
	private Tab tab;
	private Node tabScene;
	
	public TabScene(Tab tab, Node tabScene) {
		// TODO Auto-generated constructor stub
		this.tab = tab;
		this.tabScene = tabScene;
	}
	public void setTab(Tab tab) {
		this.tab = tab;
	}
	public Tab getTab() {
		return tab;
	}
	
	public void setTabScene(Node tabScene) {
		this.tabScene = tabScene;
	}
	public Node getTabScene() {
		return tabScene;
	}
	
	public void close() {
		this.tab = null;
		this.tabScene = null;
	}
}
public class SceneController {
	public static SceneController instance;
	private Stack<SceneTemplateController> sceneStack;
	
	public SceneController() {
		sceneStack = new Stack<SceneTemplateController>();
	}
	
	public void addScene(SceneTemplateController c) {
		if (sceneStack.contains(c) == false) {
			sceneStack.add(c);
		}
		
		Logger.log("size = " + sceneStack.size());
//		showScene();
	}
	
	public void removeScene(SceneTemplateController c) {
		for (int i=0; i<sceneStack.size(); i++) {
			if (sceneStack.get(i).getMyTab() == c.getMyTab() &&
				sceneStack.get(i).getMyNode() == c.getMyNode()) {
				
				sceneStack.get(i).close();
				sceneStack.remove(i);
				Logger.log("found and removed scene");
			}
		}
//		sceneStack.remove(tabScene);
		
//		showScene();
	}
	
	public void removeAll() {
		Logger.log("" + sceneStack.size());
		sceneStack.removeAllElements();
	}
	
	public void showScene() {
		int lastIndex = sceneStack.size()-1;
		Tab tab = sceneStack.get(lastIndex).getMyTab();
		Node tabScene = sceneStack.get(lastIndex).getMyNode();
		
		tab.setContent(tabScene);
		sceneStack.get(lastIndex).onShow();
	}
	
	public void resumeScene() {
		int lastlastIndex = sceneStack.size() - 2;
		if (lastlastIndex > -1 ) {
			Tab tab = sceneStack.get(lastlastIndex).getMyTab();
			Node tabScene = sceneStack.get(lastlastIndex).getMyNode();
			
			tab.setContent(tabScene);
			sceneStack.get(lastlastIndex).onResume();
		}
	}
	
	public void suspendScene() {
		int lastlastIndex = sceneStack.size() - 2;
		if (lastlastIndex > -1 ) {
			sceneStack.get(lastlastIndex).onSuspend();
		}
		Logger.log("lastlastIndex = " + lastlastIndex);
	}
	public void showRequest(SceneTemplateController c) {
		addScene(c);
		showScene();
		suspendScene();
	}
	
	public void showAndAllHideRequest(SceneTemplateController c) {
		removeAll();
		addScene(c);
		showScene();
	}
	
	public void hideRequest(SceneTemplateController c) {
		resumeScene();
		removeScene(c);
		c.onHide();
//		showScene();
	}
	
	public static SceneController getInstance() {
		if (SceneController.instance == null) {
			SceneController.instance = new SceneController();
		}
		
		return SceneController.instance;
	}
}
