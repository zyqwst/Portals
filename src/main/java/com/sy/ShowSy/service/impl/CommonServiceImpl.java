package com.sy.ShowSy.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sy.ShowSy.domain.EntityBase;
import com.sy.ShowSy.reponsitory.CommonDao;
import com.sy.ShowSy.service.CommonService;
import com.sy.ShowSy.utils.TipException;

@Service
@Transactional
public class CommonServiceImpl implements CommonService{
	@Resource
	private  CommonDao  commonDao;

	@Override
	public <T extends EntityBase> void save(T t) throws TipException {
		commonDao.save(t);
	}

	@Override
	public <T extends EntityBase> void update(T t) throws TipException {
		commonDao.update(t);
	}

	@Override
	public <T extends EntityBase>  void delete(Class<T> clazz,Long id) throws TipException {
		commonDao.deleteById(clazz, id);
	}

	@Override
	public <T extends EntityBase> T findEntityById(Class<T> clazz, Long id) throws TipException {
		return commonDao.findEntityById(clazz, id);
	}

	@Override
	public <T extends EntityBase> T findEntity(Class<T> clazz ,String hql, List<Object> params) throws TipException {
		return commonDao.findEntity(clazz, hql, params);
	}

	@Override
	public <T extends EntityBase> List<T> findAll(Class<T> clazz, String hql, List<Object> params)
			throws TipException {
		return commonDao.findAll(clazz, hql, params);
	}

	@Override
	public <T extends EntityBase>void updateByHql(Class<T> clazz, String hql, List<Object> params)
			throws TipException {
		commonDao.update(clazz, hql, params);
	}

	@Override
	public <T extends EntityBase> Page<T> findPage(Class<T> clazz, String hql, List<Object> params, Pageable pageable)
			throws TipException {
		return commonDao.findPage(clazz, hql, params, pageable);
	}

	@Override
	public <T extends EntityBase> Double getSum(Class<T> clazz,  String field,String hql,List<Object> params) throws TipException {
		return commonDao.getSum(clazz, field, hql, params);
	}

	@Override
	public <T extends EntityBase> List<T> findAllBySql(Class<T> clazz, String sql,List<Object> params) throws TipException {
		return commonDao.findAllBySql(clazz, sql,params);
	}

	@Override
	public <T extends EntityBase> T findEntityBySql(Class<T> clazz, String sql, List<Object> params)
			throws TipException {
		return commonDao.findEntityBySql(clazz, sql, params);
	}

	@Override
	public <T extends EntityBase> Page<T> findPageBySql(Class<T> clazz, String sql, List<Object> params,
			Pageable pageable) throws TipException {
		return commonDao.findPageBySql(clazz, sql, params, pageable);
	}

	@Override
	public <T extends EntityBase> long count(Class<T> clazz, String hql, List<Object> params) throws TipException {
		return commonDao.count(clazz, hql, params);
	}

	@Override
	public <T extends EntityBase> long countBySql(String sql, List<Object> params) throws TipException {
		return commonDao.countBySql(sql, params);
	}

}
