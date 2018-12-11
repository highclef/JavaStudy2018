package view;

import java.net.URL;


import java.util.ResourceBundle;

import view.ReviewFirstController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ReviewFirstController implements Initializable{

	@FXML
	private static Label lbl1;
	@FXML
	private static Label lbl2;
	@FXML
	private static TextArea txt1;
	@FXML
	private static Button btn1;
	@FXML
	private static Button btn2;
	@FXML
	private static Button btn3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

	@FXML
	private void btn1Clicked(ActionEvent event){
		System.out.println("Button1 Clicked!!");
	}

	@FXML
	private void btn2Clicked(ActionEvent event){
		System.out.println("Button2 Clicked!!");
		txt1.setText(ReviewSecondController.txt2.getText());

	}

	@FXML
	private void btn3Clicked(ActionEvent event){
		System.out.println("Button3 Clicked!!");

	}

}
