package me.paul.yiblog.dao;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.entity.Passage;

public interface IPassageDao {
	void save(Passage passage);

	void update(Passage passage);

	Passage get(Serializable id);

	List<Passage> page(int n, int passagePerPage);

	List<Passage> page(int n, int passagePerPage, boolean time);

	List<Passage> categoryPage(int n, int passagePerPage, long categoryId);

	List<Passage> categoryPage(int n, int passagePerPage, long categoryId,
			boolean time);

	List<Passage> subCategoryPage(int n, int passagePerPage, long subCategoryId);

	List<Passage> subCategoryPage(int n, int passagePerPage,
			long subCategoryId, boolean time);

	List<Passage> mostRead(int count);

	List<Passage> latest(int count);
}
