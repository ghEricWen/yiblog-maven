package me.paul.yiblog.entity;

public class Category {

	private long id;

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

	@Override
	public int hashCode() {
		return (int) id;
	}
}
