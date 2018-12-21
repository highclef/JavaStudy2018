package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TableView;

public class TableRowModel {
	
	private StringProperty username;
	private StringProperty password;
	private StringProperty name;
	private StringProperty age;
	//private IntegerProperty age;
	private StringProperty email;
	private StringProperty gender;
	
	public TableRowModel(String username, String password, String name, String age, String email, String gender)
	{
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleStringProperty(age);
		//this.age = new SimpleIntegerProperty(age);
		this.email = new SimpleStringProperty(email);
		this.gender = new SimpleStringProperty(gender);
	}
	
	// ���� name
	public StringProperty getUsername()
	{
		return username;
	}
	
	public void setUsername(StringProperty username)
	{
		this.username = username;
	}
	
	//��й�ȣ
	public StringProperty getPassword()
	{
		return password;
	}
	public void setPassword(StringProperty password)
	{
		this.password = password;
	}
	
	// �̸�
	public StringProperty getName()
	{
		return name;
	}
	public void setName(StringProperty name)
	{
		this.name = name;
	}
	
	// ����
	public StringProperty getAge()
	{
		return age;
	}
	public void setAge(StringProperty age)
	{
		this.age = age;
	}
	/*
	// ����
	public IntegerProperty getAge()
	{
		return age;
	}
	public void setAge(IntegerProperty age)
	{
		this.age = age;
	}
	*/
	//email
	public StringProperty getEmail()
	{
		return email;
	}
	public void setEmail(StringProperty email)
	{
		this.email = email;
	}
	//����
	public StringProperty getGender()
	{
		return gender;
	}
	public void setGender(StringProperty gender)
	{
		this.gender = gender;
	}
	

}
