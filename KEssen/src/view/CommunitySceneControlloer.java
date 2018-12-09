package view;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.PostingModel;
import util.Logger;

public class CommunitySceneControlloer extends SceneTemplateController {
	private String loginId = "ID7";
	private static int count = 0;
	
	private ObservableList<PostingModel> postingModelList;
	@FXML
	VBox postingList;

	@FXML
	private void initialize() {
		Logger.log("");
		postingModelList = FXCollections.observableArrayList();
		postingModelList.addListener(new ListChangeListener<PostingModel>() {
			@Override
			public void onChanged(Change<? extends PostingModel> c) {
				// TODO Auto-generated method stub
				while (c.next()) {
					if (c.wasAdded()) {
						addItem();
					} else if (c.wasRemoved()) {
						List<? extends PostingModel> l = c.getRemoved();
						Logger.log("list size = " + l.size());
						for (int i=0; i<l.size(); i++) {
							Logger.log("deleted id : " + l.get(i).getId());
						}
					}
				}
			}
		});
		initData();
	}

	public void initData() {
		PostingModel posting1 = new PostingModel();
		posting1.setId("abc");
		posting1.setMsg("Test1111");

		PostingModel posting2 = new PostingModel();
		posting2.setId("ID2");
		posting2.setMsg("Test2222");

		PostingModel posting3 = new PostingModel();
		posting3.setId("ID3");
		posting3.setMsg("Test3");

		PostingModel posting4 = new PostingModel();
		posting4.setId("ID4");
		posting4.setMsg("Test4");

		PostingModel posting5 = new PostingModel();
		posting5.setId("ID5");
		posting5.setMsg("Test5");

		PostingModel posting6 = new PostingModel();
		posting6.setId("ID6");
		posting6.setMsg("Test6");

		PostingModel posting7 = new PostingModel();
		posting7.setId("ID7");
		posting7.setMsg("Test7");

		posting1.setModelId(count++);
		postingModelList.add(posting1);
		posting2.setModelId(count++);
		postingModelList.add(posting2);
		posting3.setModelId(count++);
		postingModelList.add(posting3);
		posting4.setModelId(count++);
		postingModelList.add(posting4);
		posting5.setModelId(count++);
		postingModelList.add(posting5);
		posting6.setModelId(count++);
		postingModelList.add(posting6);
		posting7.setModelId(count++);
		postingModelList.add(posting7);

//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.setPrettyPrinting();
//		Gson gson = new Gson();
//				String result = gson.toJson(modelList, type);
//		String result = gson.toJson(modelList);
//		Logger.log(result);

		// insert posting datas
//		PostingModel[] modelList2 = gson.fromJson(result, PostingModel[].class);

//		Logger.log("modelist size : " + modelList2.length);
//		for (int i = 0; i < postingModelList.size(); i++) {
//			Logger.log(i + "=>>" + modelList2[i].getId());
//			Logger.log(i + "=>>" + postingModelList.get(i).getId());
//			try {
//				FXMLLoader loader = new FXMLLoader();
//				loader.setLocation(MainApp.class.getResource("../view/PostingItem.fxml"));
//				AnchorPane node = (AnchorPane) loader.load();
//
//				PostingItemController controller = loader.getController();
//				controller.setID(postingModelList.get(i).getId());
//				controller.setTextArea(postingModelList.get(i).getMsg());
//
//				postingList.getChildren().add(node);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

	}

	public void addItem() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/PostingItem.fxml"));
			AnchorPane node = (AnchorPane) loader.load();

			int lastIndex = postingModelList.size() - 1;

			PostingItemController controller = loader.getController();
			controller.setCommunitySceneController(this);
			controller.setModelId(postingModelList.get(lastIndex).getModelId());
			controller.setID(postingModelList.get(lastIndex).getId());
			controller.setTextArea(postingModelList.get(lastIndex).getMsg());
			if (loginId.equals(controller.getID())) {
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
	
	public void deleteItem(int modelId) {
		for (int i=0; i<postingModelList.size(); i++) {
			if (postingModelList.get(i).getModelId() == modelId) {
				Logger.log("found index : " + i);
				postingModelList.remove(i);
			}
		}
		
		ObservableList<Node> l = postingList.getChildren();
		for (int i=0; i<l.size(); i++) {
			PostingItemController c = (PostingItemController)l.get(i).getUserData();
			if (c.getModelId() == modelId) {
				Logger.log("modelId : " + modelId);
				postingList.getChildren().remove(i);
			}
		}
	}

	@FXML
	private void onWriteText() {
		Logger.log("");
		
		//test code!!
		PostingModel posting8 = new PostingModel();
		posting8.setId("ID7");
		posting8.setMsg("Test7");

		posting8.setModelId(count++);
		postingModelList.add(posting8);
	}
}
