package me.sso.ti.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月17日 下午11:01:41
 */
public interface GenericDAO<T, PK extends Serializable> extends EntityManagerOperations {

	List<T> queryNative(String query);

	List<T> queryNative(String query, Map<String, Object> args);

	List<T> queryNative(String query, Object[] args);

	T queryNativeForObject(String query, Object[] args);

	T queryNativeForObject(String query, Map<String, Object> args);

	List<T> query(String query);

	List<T> query(String query, Map<String, Object> args);

	List<T> query(String query, Object[] args);

	T queryForObject(String query, Object[] args);

	T queryForObject(String query, Map<String, Object> args);

	List<T> queryNamed(String queryName);

	List<T> queryNamed(String queryName, Map<String, Object> args);

	List<T> queryNamed(String queryName, Object[] args);

	T queryNamedForObject(String queryName, Map<String, Object> args);

	T queryNamedForObject(String queryName, Object[] args);

	int queryUpdate(String query, Map<String, Object> args);

	int queryUpdate(String query, Object[] args);

	int queryNamedUpdate(String queryName, Map<String, Object> args);

	int queryNamedUpdate(String queryName, Object[] args);

	int queryNativeUpdate(String query, Object[] args);

	int queryNativeUpdate(String query, Map<String, Object> args);

	T get(PK id);
	
	T load(PK id);

	List<T> findAll();
}