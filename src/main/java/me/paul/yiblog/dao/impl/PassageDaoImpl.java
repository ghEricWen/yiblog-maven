package me.paul.yiblog.dao.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.IPassageDao;
import me.paul.yiblog.entity.Passage;
import me.paul.yiblog.util.HibernateUtil;

public class PassageDaoImpl implements IPassageDao {

	private HibernateUtil util;

	public void setUtil(HibernateUtil util) {
		this.util = util;
	}

	public void save(Passage passage) {
		util.save(passage);
	}

	public void update(Passage passage) {
		util.update(passage);
	}

	public Passage get(Serializable id) {
		return (Passage) util.get(Passage.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> page(int n, int passagePerPage) {
		String hql = "from Passage";
		return (List<Passage>) util.limitQuery(hql, (n-1) * passagePerPage, passagePerPage);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> categoryPage(int n, int passagePerPage, long categoryId) {
		String hql = "from Passage as p where p.category.id = ?";
		return (List<Passage>) util.limitQuery(hql, (n-1) * passagePerPage, passagePerPage, categoryId);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> subCategoryPage(int n, int passagePerPage,
			long subCategoryId) {
		String hql = "from Passage as p where p.subCategory.id = ?";
		return (List<Passage>) util.limitQuery(hql, (n-1) * passagePerPage, passagePerPage, subCategoryId);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> mostRead(int count) {
		String hql = "from Passage as p order by p.readtime desc";
		return (List<Passage>) util.limitQuery(hql, 0, count);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> latest(int count) {
		String hql = "from Passage as p order by p.writetime desc";
		return (List<Passage>) util.limitQuery(hql, 0, count);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> page(int n, int passagePerPage, boolean time) {
		String hql = "from Passage as p order by p.writetime desc";
		return (List<Passage>) util.limitQuery(hql, (n -1 ) * passagePerPage, passagePerPage);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> categoryPage(int n, int passagePerPage,
			long categoryId, boolean time) {
		String hql = "from Passage as p where p.category.id = ? order by p.writetime desc";
		return (List<Passage>) util.limitQuery(hql, (n -1 ) * passagePerPage, passagePerPage,categoryId);
	}

	@SuppressWarnings("unchecked")
	public List<Passage> subCategoryPage(int n, int passagePerPage,
			long subCategoryId, boolean time) {
		String hql = "from Passage as p where p.subCategory.id = ? order by p.writetime desc";
		return (List<Passage>) util.limitQuery(hql, (n -1 ) * passagePerPage, passagePerPage,subCategoryId);
	}
	
}
