package me.paul.yiblog.entity;

public class SubCategory {

	private long id;

	private Category category;

	private String name;

	private int passageCount;

	public int getPassageCount() {
		return passageCount;
	}

	public void setPassageCount(int passageCount) {
		this.passageCount = passageCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
