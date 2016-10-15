package me.paul.yiblog.entity;

import java.util.Date;

public class Passage {

	private long id;

	private String title;

	private Category category;
	
	private SubCategory subCategory;

	private User author;

	private int readtime;

	private Date writetime;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getReadtime() {
		return readtime;
	}

	public void setReadtime(int readtime) {
		this.readtime = readtime;
	}

	public Date getWritetime() {
		return writetime;
	}

	public void setWritetime(Date writetime) {
		this.writetime = writetime;
	}
	
	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}


	@Override
	public String toString() {

		return title + " ; author : " + author;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Passage) {
			Passage p = (Passage) obj;
			if (p.getId() == id)
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) id;
	}
}
