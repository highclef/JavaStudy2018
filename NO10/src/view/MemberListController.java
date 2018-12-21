package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
//import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import javafx.scene.control.ListView;
import model.MemberModel;
import model.TableRowModel;
import network.MessageIDs;
import network.NetworkData;
import network.NetworkManager;

public class MemberListController implements Initializable {

	// field
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField ageField;
	@FXML
	private TextField emailField;

	// Table & Column
	@FXML
	private TableView<TableRowModel> table;
	@FXML
	private TableColumn<TableRowModel, String> username;
	@FXML
	private TableColumn<TableRowModel, String> password;
	@FXML
	private TableColumn<TableRowModel, String> name;
	@FXML
	private TableColumn<TableRowModel, String> age; // ȭ�� ��µǴ� �κ� string ������ ǥ���ص� �������
	@FXML
	private TableColumn<TableRowModel, String> gender;
	@FXML
	private TableColumn<TableRowModel, String> email;

	// label

	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	@FXML
	private Button waehlen;

	ObservableList<TableRowModel> list = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		System.out.println("FXML LOAD COMPLETE!");

		NetworkManager.getInstance().setListSetController(this);
		
		list = FXCollections.observableArrayList();

		// list.add(new TableRowModel("a ", " b", "b ", "d ", "s", "e"));

		username.setCellValueFactory(cellData -> cellData.getValue().getUsername());
		password.setCellValueFactory(cellData -> cellData.getValue().getPassword());
		name.setCellValueFactory(cellData -> cellData.getValue().getName());
		age.setCellValueFactory(cellData -> cellData.getValue().getAge());
		gender.setCellValueFactory(cellData -> cellData.getValue().getGender());
		email.setCellValueFactory(cellData -> cellData.getValue().getEmail());

		NetworkData data = new NetworkData(MessageIDs.MEMBERLIST_REQ);
		data.pack();
		NetworkManager.getInstance().send(data.getByteBuffer());
		
		// table.setItems(list);
	}
	// table.setItems(list);
	/*
	 * public void tableCall() { username.setCellValueFactory(cellData ->
	 * cellData.getValue().getUsername()); password.setCellValueFactory(cellData ->
	 * cellData.getValue().getPassword()); name.setCellValueFactory(cellData ->
	 * cellData.getValue().getName()); age.setCellValueFactory(cellData ->
	 * cellData.getValue().getAge()); gender.setCellValueFactory(cellData ->
	 * cellData.getValue().getGender()); email.setCellValueFactory(cellData->
	 * cellData.getValue().getEmail()); // table.setItems(list); }
	 */

	// radioButton

	public String waehlen()

	{
		String ret_sex = null;
		if (male.isSelected()) {
			ret_sex = "Male";
			// sexselect.setText(male.getText());
		} else if (female.isSelected()) // else if???
		{
			ret_sex = "Female";
		} else {
			return null;
			// error â ()
		}
		return ret_sex;
	}

	public String listChecker() {

		String retStr = "";

		if (usernameField.getText() == "") {

		}

		if (usernameField.getText() == "") {

		}
		if (passField.getText() == "") {

		}
		if (nameField.getText() == "") {

		}
		if (ageField.getText() == "") {

		}
		if (emailField.getText() == "") {

		}
		if (waehlen() == "") {

		}

		return retStr;
	}
	
	public void addMember(MemberModel mm) {
		TableRowModel trm = new TableRowModel(mm.getUsername(),
											  mm.getPassword(),
											  mm.getName(),
											  mm.getBirthday(),
											  "None",
											  mm.getCity());
		list.add(trm);
		table.setItems(list);
	}

	public void regist() {
		System.out.println(listChecker());

		list.add(new TableRowModel(usernameField.getText(), passField.getText(), nameField.getText(),
				ageField.getText(), emailField.getText(), waehlen()));

		table.setItems(list);

		usernameField.clear();
		passField.clear();
		nameField.clear();
		ageField.clear();
		emailField.clear();

		/*
		 * usernameField.setText(""); passField.setText(""); nameField.setText("");
		 * ageField.setText(""); emailField.setText("");
		 * 
		 * 
		 * for(TableRowModel model:list) { System.out.println( model.getUsername().get()
		 * + "|" + model.getPassword().get() + "|" + model.getName().get() + "|" +
		 * model.getAge().get() + "|" + model.getEmail().get()
		 * 
		 * ); }
		 */
		System.out.println();

	}

	// ������ư
	@FXML
	public void Sakze() {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		table.getItems().remove(selectedIndex);

		/*
		 * ObservableList<TableRowModel> items =
		 * table.getSelectionModel().getSelectedItems();
		 * 
		 * for(TableRowModel item:items) { table.setItems(list); list.clear(); }
		 */

		/*
		 * list.remove(
		 * 
		 * new TableRowModel(usernameField.getText(), passField.getText(),
		 * nameField.getText(), ageField.getText(), emailField.getText(), waehlen()));
		 * 
		 * table.setItems(list); //list.clear();
		 */

	}

	// �ڷΰ��� ��ư
	@FXML
	private Button backBack;

	public void BackgoAction() {
		Stage stage = (Stage) backBack.getScene().getWindow(); // ��������2

		try {

			Stage newStage = new Stage(); // ��������1
			// scene �� ���̾ƿ� �߰��� ���
			Parent second = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

			Scene sc = new Scene(second);

			// Scene�� ������������ ���ϰ�
			newStage.setScene(sc);
			newStage.show();

			// �⺻ ������ ������
			stage.hide();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
