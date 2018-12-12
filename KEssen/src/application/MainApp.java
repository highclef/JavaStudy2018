package application;

import model.Member;

import view.MainSceneController;
import view.RootLayoutController;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Logger;

public class MainApp extends Application {
	public static final String SCENE_ROOTLAYOUT = "../view/RootLayout.fxml";
	public static final String MAINSCENE = "../view/MainScene.fxml";
	public static final String HOMESCENE = "../view/HomeScene.fxml";
	public static final String HOMESUBSCENE = "../view/HomeSubScene.fxml";
	public static final String COMMUNITYSCENE = "../view/CommunityScene.fxml";
	public static final String POSTWRITINGSCENE = "../view/PostWritingScene.fxml";
	public static final String REVIEWFIRST = "../view/ReviewFirst.fxml";
	public static final String REVIEWSECOND = "../view/ReviewSecond.fxml";

	Stage primaryStage;
	private AnchorPane rootLayout;

	public MainApp() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Koreanisches Essen");

		initLayout();
		showMainScene();
	}

	public void initLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.SCENE_ROOTLAYOUT));
			rootLayout = (AnchorPane) loader.load();

			Scene scene = new Scene(rootLayout);
			this.primaryStage.setScene(scene);

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			this.primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showMainScene() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.MAINSCENE));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();

			this.rootLayout.setTopAnchor(pane, (double) 0);
			this.rootLayout.setBottomAnchor(pane, (double) 0);
			this.rootLayout.setRightAnchor(pane, (double) 0);
			this.rootLayout.setLeftAnchor(pane, (double) 0);

			this.rootLayout.getChildren().add(pane);

			MainSceneController controller = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Member m = new Member();
		m.setAge(10);
		m.setFirstName("abc");
		m.setLastName("bcdf");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = new Gson();
		String result = gson.toJson(m);
		Logger.log(result);
		Member mm = gson.fromJson(result, Member.class);
		mm.setFirstName("dkvkbj");
		result = gson.toJson(mm);
		Logger.log(result);

		launch(args);
	}
}
