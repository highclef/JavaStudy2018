package model;

public class LoginModel {
	private String userId;
	private String pw;
	private boolean isLogin;
	
	public LoginModel() {
		userId = "";
		pw = "";
		isLogin = false;
	}
	public void setUserId(String id) {
		userId = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setPassword(String pw) {
		this.pw = pw;
	}
	public String getPassword() {
		return pw;
	}
	public void setLogined(boolean is) {
		isLogin = is; 
	}
	public boolean logined() {
		return isLogin;
	}
}
