package view;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Member;
import model.PostingModel;
import util.Logger;

public class CommunitySceneControlloer extends SceneTemplateController {
	@FXML
	VBox postingList;

	@FXML
	private void initialize() {
		Logger.log("");
		
		// Test code!!
		ArrayList<PostingModel> modelList = new ArrayList<PostingModel>();
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
		
		modelList.add(posting1);
		modelList.add(posting2);
		modelList.add(posting3);
		modelList.add(posting4);
		modelList.add(posting5);
		modelList.add(posting6);
		modelList.add(posting7);
		
//		Type type = new TypeToken<ArrayList<PostingModel>>() {}.getType();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = new Gson();
//		String result = gson.toJson(modelList, type);
		String result = gson.toJson(modelList);
		Logger.log(result);
		
		//insert posting datas
		PostingModel[] modelList2 = gson.fromJson(result, PostingModel[].class);
		
		Logger.log("modelist size : " + modelList2.length);
		for (int i=0; i<modelList2.length; i++) {
			Logger.log(i + "=>>" + modelList2[i].getId());
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("../view/PostingItem.fxml"));
				AnchorPane node = (AnchorPane) loader.load();

				PostingItemController controller = loader.getController();
				controller.setID(modelList2[i].getId());
				controller.setTextArea(modelList2[i].getMsg());
				
				postingList.getChildren().add(node);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	private void onWriteText() {
		Logger.log("");
	}
}
