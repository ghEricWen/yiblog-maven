package me.paul.yiblog.dao;

import java.util.List;

import me.paul.yiblog.entity.Feedback;


public interface IFeedbackDao {
	
	public void save(Feedback fb);
	
	public List<Feedback> getAll(); 
	
	
}
