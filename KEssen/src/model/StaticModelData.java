package model;

public class StaticModelData {
	private LoginModel loginModel;
	
	private static StaticModelData instance;
	
	public StaticModelData() {
		loginModel = new LoginModel();
	}
	public void setLoginModel(LoginModel l) {
		loginModel = l;
	}
	public LoginModel getLoginModel() {
		return loginModel;
	}

	public static StaticModelData getInstance() {
		if (StaticModelData.instance == null) {
			StaticModelData.instance = new StaticModelData();
		}
		
		return StaticModelData.instance;
	}
}
