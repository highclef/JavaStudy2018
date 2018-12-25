package view;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.StaticModelData;
import util.Logger;

public class ReviewPostingViewController extends SceneTemplateController {
	
	@FXML
	Label nameLabel;
	@FXML
	Label userName;
	@FXML
	TextArea textArea;
	@FXML
	TextField titleTextField;

	
	@FXML
	private void initialize() {
		
		nameLabel.setText(RestaurantListViewController.RESTAURANTNAME);
		Logger.log("Review Posting - Restaurant Name: " + RestaurantListViewController.RESTAURANTNAME);
		userName.setText(StaticModelData.getInstance().getLoginModel().getUserId());
		Logger.log("Review Posting - User Name: " + userName.getText());
	}	
	
	@FXML
	private void postingReview() throws IOException {
		
		if(textArea.getText().isEmpty() || titleTextField.getText().isEmpty()) {
			Logger.log("No Text in Textarea");
			
			// Open New Window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("ReviewPostingErrorView.fxml"));
			Scene scene = new Scene(root);		
			
			primaryStage.setTitle("Error");
			primaryStage.initModality(Modality.WINDOW_MODAL);
//			primaryStage.initOwner(this.getMyNode().getScene().getWindow());	// NullPointerException
			primaryStage.setScene(scene);
			primaryStage.show();
			
		
		} else {
			Logger.log("");
			// TODO: 리뷰 내용 저장하고  (+ 아이디), 리뷰 게시판에 내용 등재
		
		}
	}
	
	@FXML
	private void cancelReviewPosting(Event event) {
		
		// Close current window
		((Node)(event.getSource())).getScene().getWindow().hide();    
		Logger.log("");
	}
}
