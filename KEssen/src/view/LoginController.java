package view;

import java.io.IOException;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.LoginModel;
import model.StaticModelData;
import network.MessageIDs;
import network.NetworkData;
import network.NetworkManager;

public class LoginController extends SceneTemplateController {
	private Alert loginingAlert;
	@FXML
	private Text lblStatus;

	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtPassword;

	@FXML
	private void initialize() {
		NetworkManager.getInstance().setLoginController(this);
	}

	public void loginSuccess() {
		if (loginingAlert.isShowing()) {
			loginingAlert.hide();
		}
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(MainApp.LOGINFERTIGSCENE));
			Node pane = (Node) loader.load();

			LoginFertigController controller = loader.getController();
			controller.setMyTab(this.getMyTab());
			controller.setMyNode(pane);
			
			SceneController.getInstance().showAndAllHideRequest(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
//
//		Stage primaryStage = new Stage();
//		Parent root = null;
//		try {
//			root = FXMLLoader.load(getClass().getResource("Loginfertig.fxml"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}

	public void loginFailure() {
		if (loginingAlert.isShowing()) {
			loginingAlert.hide();
		}
		lblStatus.setText("Login Fail!");
	}

	@FXML
	private void onLogin(ActionEvent event) throws Exception {
		LoginModel l = new LoginModel();
		l.setUserId(txtUserName.getText());
		l.setPassword(txtPassword.getText());
		StaticModelData.getInstance().setLoginModel(l);
		NetworkData data = new NetworkData(MessageIDs.LOGIN_REQ, l);
		data.pack();
		NetworkManager.getInstance().send(data.getByteBuffer());
		
		//login alert
		loginingAlert = new Alert(AlertType.INFORMATION);
		loginingAlert.setHeaderText("Logining!!");
		loginingAlert.setContentText("Trying to login");
		loginingAlert.initOwner(getMyNode().getScene().getWindow());
		loginingAlert.initModality(Modality.WINDOW_MODAL);
		loginingAlert.showAndWait();
	}

	@FXML
	private void SignIn(ActionEvent event) throws Exception {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Sign in");
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(this.getMyNode().getScene().getWindow());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
