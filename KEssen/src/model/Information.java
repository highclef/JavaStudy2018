package model;

public class Information {

	private String name;
	private int score;
	private String menuItems[];
	private String reviews[];

	public Information() {
		name = "test";
		score = 4;
		menuItems = new String[3];
		menuItems[0] = "item1";
		menuItems[1] = "item2";
		menuItems[2] = "item3";
		reviews = new String[2];
		reviews[0] = "review 1";
		reviews[1] = "review 2";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return this.score;
	}
	public void setMenuItems(String []m) {
		menuItems = new String[m.length];
		System.arraycopy(m, 0, menuItems, 0, m.length);
	}
	public String[] getMenuItems() {
		return menuItems;
	}
	public void setReviews(String []r) {
		reviews = new String[r.length];
		System.arraycopy(r, 0, reviews, 0, r.length);
	}
	public String[] getReviews() {
		return reviews;
	}
}
