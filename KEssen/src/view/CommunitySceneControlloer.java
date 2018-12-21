package view;

import java.io.IOException;
import java.util.List;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PostingModel;
import model.StaticModelData;
import network.MessageIDs;
import network.NetworkData;
import network.NetworkManager;
import util.Logger;

public class CommunitySceneControlloer extends SceneTemplateController {
	private String loginId = "";
	private static int count = 0;

	private ObservableList<PostingModel> postingModelList;
	@FXML
	VBox postingList;

	public CommunitySceneControlloer() {
		// TODO Auto-generated constructor stub
		NetworkManager.getInstance().setCommunitySceneControlloer(this);
	}

	@FXML
	private void initialize() {
//		Logger.log("");
		if (StaticModelData.getInstance().getLoginModel().logined()) {
			loginId = StaticModelData.getInstance().getLoginModel().getUserId();
		} else {
			loginId = "";
		}
		postingModelList = FXCollections.observableArrayList();
		postingModelList.addListener(new ListChangeListener<PostingModel>() {
			@Override
			public void onChanged(Change<? extends PostingModel> c) {
				// TODO Auto-generated method stub
				while (c.next()) {
					if (c.wasAdded()) {
						Logger.log("was Addead");
						addItem();
					} else if (c.wasRemoved()) {
						List<? extends PostingModel> l = c.getRemoved();
						Logger.log("list size = " + l.size());
						for (int i = 0; i < l.size(); i++) {
							Logger.log("deleted id : " + l.get(i).getId());
						}
					} else if (c.wasUpdated()) {
						Logger.log("was updated");
					}
				}
			}
		});
		initData();
	}

	public void initData() {
		NetworkData data = new NetworkData(MessageIDs.POSTINGDATALIST_REQ);
		data.pack();
		NetworkManager.getInstance().send(data.getByteBuffer());
	}

	public void addItem() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/PostingItem.fxml"));
			AnchorPane node = (AnchorPane) loader.load();

			int lastIndex = postingModelList.size() - 1;

			PostingItemController controller = loader.getController();
			controller.setCommunitySceneController(this);
			controller.setId(postingModelList.get(lastIndex).getId());
			controller.setUsername(postingModelList.get(lastIndex).getUsername());
			controller.setTextArea(postingModelList.get(lastIndex).getMsg());
			if (loginId.equals(controller.getUsername())) {
				controller.setButtonBarVisible(true);
			} else {
				controller.setButtonBarVisible(false);
			}

			node.setUserData(controller);
			postingList.getChildren().add(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteItem(int id) {
		int foundIndex = findID(id);
		if (foundIndex < 0) {
			Logger.log("not found");
			return;
		}
		postingModelList.remove(foundIndex);
		postingList.getChildren().remove(foundIndex);
	}

	public void modifyItem(PostingModel data) {
		int foundIndex = findID(data.getId());

		postingModelList.get(foundIndex).setMsg(data.getMsg());
		PostingItemController c = (PostingItemController) postingList.getChildren().get(foundIndex).getUserData();
		c.setTextArea(data.getMsg());
	}

	public void modifyItemDialog(int id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.POSTWRITINGSCENE));
			AnchorPane pane = (AnchorPane) loader.load();

			Stage writeStage = new Stage();
			writeStage.setTitle("Write Post");
			writeStage.initModality(Modality.WINDOW_MODAL);
			writeStage.initOwner(this.getMyNode().getScene().getWindow());
			Scene scene = new Scene(pane);
			writeStage.setScene(scene);

			PostWritingSceneController controller = loader.getController();
			controller.setId(loginId);
			int foundIndex = findID(id);
			if (foundIndex < 0) {
				Logger.log("not found");
				return;
			}

			controller.setTextPost(postingModelList.get(foundIndex).getMsg());
			controller.setStage(writeStage);

			writeStage.showAndWait();

			if (controller.isCanceled()) {

			} else {
				PostingModel pm = postingModelList.get(foundIndex);
				pm.setMsg(controller.getTextPost());
				sendUpdateItem(pm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int findID(int id) {
		for (int i = 0; i < postingModelList.size(); i++) {
			if (postingModelList.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	@FXML
	private void onWriteText() {
		if (StaticModelData.getInstance().getLoginModel().logined() == false) {
			Alert loginAlert = new Alert(AlertType.ERROR);
			loginAlert.initModality(Modality.WINDOW_MODAL);
			loginAlert.initOwner(getMyNode().getScene().getWindow());
			loginAlert.setHeaderText("Write Text Fail!");
			loginAlert.setContentText("You need to login.");
			loginAlert.showAndWait();
		} else {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource(MainApp.POSTWRITINGSCENE));
				AnchorPane pane = (AnchorPane) loader.load();

				Stage writeStage = new Stage();
				writeStage.setTitle("Write Post");
				writeStage.initModality(Modality.WINDOW_MODAL);
				writeStage.initOwner(this.getMyNode().getScene().getWindow());
				Scene scene = new Scene(pane);
				writeStage.setScene(scene);

				PostWritingSceneController controller = loader.getController();
				controller.setId(loginId);
				controller.setStage(writeStage);

				writeStage.showAndWait();

				if (controller.isCanceled()) {

				} else {
					PostingModel pm = new PostingModel();
					pm.setUsername(loginId);
					pm.setMsg(controller.getTextPost());

					sendAddItem(pm);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addPostingModel(PostingModel pm) {
		postingModelList.add(pm);
	}

	public void sendAddItem(PostingModel data) {
		NetworkData d = new NetworkData(MessageIDs.ADDPOSTINGDATA_REQ, data);
		d.pack();
//		d.printData();
		NetworkManager.getInstance().send(d.getByteBuffer());
	}

	public void sendDeleteItem(int id) {
		NetworkData d = new NetworkData(MessageIDs.DELPOSTINGDATA_REQ, id);
		d.pack();
		NetworkManager.getInstance().send(d.getByteBuffer());
	}

	public void sendUpdateItem(PostingModel data) {
		NetworkData d = new NetworkData(MessageIDs.UPDATEPOSTINGDATA_REQ, data);
		d.pack();
		NetworkManager.getInstance().send(d.getByteBuffer());
	}
}
