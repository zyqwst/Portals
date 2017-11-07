package com.sy.ShowSy.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sy.ShowSy.domain.EntityBase;
import com.sy.ShowSy.utils.TipException;
public interface  CommonDao{
	public<T extends EntityBase> void save(T t) throws TipException;
	
	public<T extends EntityBase>  void update(T t) throws TipException;
	
	public  <T extends EntityBase>  void deleteById(Class<T> clazz,Long id) throws TipException;
	
	public <T extends EntityBase> void delete(T t) throws TipException;
	
	public<T extends EntityBase>  T findEntityById(Class<T> clazz,Long id) throws TipException;
	
	public<T extends EntityBase> T findEntity(Class<T> clazz ,String hql,List<Object> params) throws TipException;
	
	public<T extends EntityBase> List<T> findAll(Class<T> clazz ,String hql,List<Object> params) throws TipException;
	
	public void flush();
	
	public <T extends EntityBase> void update(Class<T> clazz,String hql,List<Object> params) throws TipException;
	
	public <T extends EntityBase> long count(Class<T> clazz,String hql,  List<Object> params) throws TipException;
	public <T extends EntityBase> long countBySql(String sql,  List<Object> params) throws TipException;
	public <T extends EntityBase> Double getSum(Class<T> clazz,String field,String hql, List<Object> params) throws TipException ;
	public <T extends EntityBase> List<T> findAllBySql(Class<T> clazz,String sql,List<Object> params) throws TipException;
	public <T extends EntityBase> T findEntityBySql(Class<T> clazz,String sql,List<Object> params) throws TipException;
	public <T extends EntityBase> Page<T> findPageBySql(Class<T> clazz, String sql, List<Object> params,Pageable pageable) throws TipException ;
	public <T extends EntityBase> Page<T> findPage(Class<T> clazz, String hql, List<Object> params,Pageable pageable) throws TipException ;
}
