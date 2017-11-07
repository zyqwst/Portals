package com.sy.ShowSy.reponsitory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.sy.ShowSy.domain.EntityBase;
import com.sy.ShowSy.utils.TipException;
@Repository
public  class  CommonDaoImpl  implements CommonDao{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public <T extends EntityBase> void save(T t) throws TipException {
		em.persist(t);
	}

	@Override
	public <T extends EntityBase> void update(T t) throws TipException {
		em.merge(t);
		em.flush();
	}

	@Override
	public  <T extends EntityBase>  void deleteById(Class<T> clazz,Long id) throws TipException {
		em.remove(findEntityById(clazz, id));
	}

	@Override
	public <T extends EntityBase> T findEntityById(Class<T> clazz, Long id) throws TipException {
		return (T) em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityBase> T findEntity(Class<T> clazz ,String hql, List<Object> params) throws TipException {
		try {
			Query  query = em.createQuery(" FROM " + clazz.getName() + hql );
			if(params!=null && params.size()>0){
				for(int i = 1;i<=params.size();i++){
					query.setParameter(i, params.get(i-1));
				}
			}
			return  (T) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TipException(e.getMessage());
		} 
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityBase> List<T> findAll(Class<T> clazz ,String hql, List<Object> params) throws TipException {
		Query  query = em.createQuery(" FROM " + clazz.getName() + hql );
		if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
			}
		}
		return  (List<T>) query.getResultList();  
	}
	@Override
	public <T extends EntityBase> void delete(T t) throws TipException {
		em.remove(t);
	}

	@Override
	public void flush() {
		em.flush();
	}

	@Override
	public <T extends EntityBase> void update(Class<T> clazz, String hql, List<Object> params) throws TipException {
		Query  query = em.createQuery(" update " + clazz.getName() + hql );
		if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
				if(i%30==0){
					em.flush();em.clear();
				}
			}
		}
		query.executeUpdate();
	}

	@Override
	public <T extends EntityBase> long count(Class<T> clazz,String hql,  List<Object> params) throws TipException {
        Query query = em.createQuery("select count(*) from "+clazz.getName()+hql);
        if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
				if(i%30==0){
					em.flush();em.clear();
				}
			}
		}
        return (Long) query.getSingleResult();
    }
	@Override
	public <T extends EntityBase> long countBySql(String sql,  List<Object> params) throws TipException {
        Query query = em.createNativeQuery(sql);
        if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
				if(i%30==0){
					em.flush();em.clear();
				}
			}
		}
        return (Long) query.getSingleResult();
    }
	@Override
	public <T extends EntityBase> Double getSum(Class<T> clazz,String field,String hql, List<Object> params) throws TipException {
		Query  query = em.createQuery("select sum("+field+") FROM " + clazz.getName() + hql );
		if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
			}
		}
		return  (Double) query.getSingleResult();         
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityBase> List<T> findAllBySql(Class<T> clazz,String sql,List<Object> params) throws TipException{
		Query query = em.createNativeQuery(sql, clazz);
		if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
				if(i%30==0){
					em.flush();em.clear();
				}
			}
		}
		return query.getResultList();
	}

	@Override
	public <T extends EntityBase> T findEntityBySql(Class<T> clazz, String sql, List<Object> params)
			throws TipException {
		return findAllBySql(clazz, sql, params).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityBase> Page<T> findPageBySql(Class<T> clazz, String sql, List<Object> params,
			Pageable pageable) throws TipException {
		Query query = em.createNativeQuery(sql, clazz);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
				if(i%30==0){
					em.flush();em.clear();
				}
			}
		}
		List<T> contents = query.getResultList();
		if (pageable == null || pageable.getOffset() == 0) {

			if (pageable == null || pageable.getPageSize() > contents.size()) {
				return new PageImpl<T>(contents, pageable, contents.size());
			}

			return new PageImpl<T>(contents, pageable,countBySql(sql, params));
		}

		if (contents.size() != 0 && pageable.getPageSize() > contents.size()) {
			return new PageImpl<T>(contents, pageable, pageable.getOffset() + contents.size());
		}
		return new PageImpl<>(contents, pageable, contents.size());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityBase> Page<T> findPage(Class<T> clazz, String hql, List<Object> params, Pageable pageable)
			throws TipException {
		Query  query = em.createQuery(" FROM " + clazz.getName() + hql );
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		if(params!=null && params.size()>0){
			for(int i = 1;i<=params.size();i++){
				query.setParameter(i, params.get(i-1));
				if(i%30==0){
					em.flush();em.clear();
				}
			}
		}
		List<T> contents = query.getResultList();
		if (pageable == null || pageable.getOffset() == 0) {

			if (pageable == null || pageable.getPageSize() > contents.size()) {
				return new PageImpl<T>(contents, pageable, contents.size());
			}

			return new PageImpl<T>(contents, pageable,count(clazz, hql, params));
		}

		if (contents.size() != 0 && pageable.getPageSize() > contents.size()) {
			return new PageImpl<T>(contents, pageable, pageable.getOffset() + contents.size());
		}
		return new PageImpl<>(contents, pageable, contents.size());
	}

}
