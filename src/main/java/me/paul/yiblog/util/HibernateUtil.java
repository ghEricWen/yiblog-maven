package me.paul.yiblog.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public void save(Object obj) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void update(Object obj) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void delete(Object obj) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public Object get(Class<?> c, Serializable id) {
		Session session = null;
		Object obj = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			obj = session.get(c, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public List<?> query(String hql, Object... params) {
		List<?> list = Collections.EMPTY_LIST;
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery(hql);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
			list = q.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<?> limitQuery(String hql, int startResult, int maxResult,
			Object... params) {
		List<?> list = Collections.EMPTY_LIST;
		Session session = null;

		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery(hql);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
			q.setFirstResult(startResult);
			q.setMaxResults(maxResult);
			list = q.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<?> nativeQuery(String sql, Class<?> c, Object... params) {
		List<?> list = Collections.EMPTY_LIST;
		Session session = null;

		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query q = session.createSQLQuery(sql).addEntity(c);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
			list = q.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getCount(String hql,Object... params){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		int count = 0;
		
		Query q = session.createQuery(hql);
		for(int i=0;i<params.length;i++){
			q.setParameter(i, params[i]);
		}
		@SuppressWarnings("rawtypes")
		Iterator iter = q.list().iterator();
		if(iter.hasNext()){
			Long longCount = (Long) iter.next();
			count = longCount.intValue();
		}
		session.getTransaction().commit();
		return count;
	}

}
