package me.paul.yiblog.service.impl;
import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.IPassageDao;
import me.paul.yiblog.entity.Passage;
import me.paul.yiblog.service.IPassageService;

public class PassageServiceImpl implements IPassageService {
	
	private IPassageDao passDao ;
	
	public void setPassDao(IPassageDao passDao) {
		this.passDao = passDao;
	}

	@Override
	public void save(Passage passage) {
		passDao.save(passage);
	}

	@Override
	public void update(Passage passage) {
		passDao.update(passage);
	}

	@Override
	public Passage get(Serializable id) {
		return passDao.get(id);
	}

	@Override
	public List<Passage> page(int n, int passagePerPage) {
		return passDao.page(n, passagePerPage);
	}

	@Override
	public List<Passage> categoryPage(int n, int passagePerPage, long categoryId) {
		return passDao.categoryPage(n, passagePerPage, categoryId);
	}

	@Override
	public List<Passage> subCategoryPage(int n, int passagePerPage,
			long subCategoryId) {
		return passDao.subCategoryPage(n, passagePerPage, subCategoryId);
	}

	@Override
	public List<Passage> mostRead(int count) {
		return passDao.mostRead(count);
	}
	
	@Override
	public List<Passage> latest(int count) {
		return passDao.latest(count);
	}

	@Override
	public List<Passage> page(int n, int passagePerPage, boolean time) {
		return passDao.page(n, passagePerPage, time);
	}

	@Override
	public List<Passage> categoryPage(int n, int passagePerPage,
			long categoryId, boolean time) {
		return passDao.categoryPage(n, passagePerPage, categoryId, time);
	}

	@Override
	public List<Passage> subCategoryPage(int n, int passagePerPage,
			long subCategoryId, boolean time) {
		return passDao.subCategoryPage(n, passagePerPage, subCategoryId, time);
	}

}
