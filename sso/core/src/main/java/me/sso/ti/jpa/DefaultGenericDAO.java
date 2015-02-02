package me.sso.ti.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月17日 下午11:11:22
 */
@SuppressWarnings("unchecked")
public class DefaultGenericDAO<T, PK extends Serializable> extends EntityManagerAccessor implements GenericDAO<T, PK> {

	@PersistenceContext(name = "scape")
	protected EntityManager entityManager;

	protected final Class<T> entityType;

	public DefaultGenericDAO() {
		entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public List<T> queryNative(String query, Map<String, Object> args) {
		return createNativeQuery(query, getEntityType(), args).getResultList();
	}

	@Override
	public List<T> queryNative(String query, Object[] args) {
		return createNativeQuery(query, getEntityType(), args).getResultList();
	}

	@Override
	public T queryNativeForObject(String query, Object[] args) {
		return (T) createNativeQuery(query, getEntityType(), args).getSingleResult();
	}

	@Override
	public T queryNativeForObject(String query, Map<String, Object> args) {
		return (T) createNativeQuery(query, getEntityType(), args).getSingleResult();
	}

	@Override
	public List<T> queryNative(String query) {
		return createNativeQuery(query, getEntityType()).getResultList();
	}

	@Override
	public List<T> query(String query) {
		return createQuery(query, getEntityType()).getResultList();
	}

	@Override
	public List<T> query(String query, Map<String, Object> args) {
		return createQuery(query, getEntityType(), args).getResultList();
	}

	@Override
	public List<T> query(String query, Object[] args) {
		return createQuery(query, getEntityType(), args).getResultList();
	}

	@Override
	public T queryForObject(String query, Object[] args) {
		return createQuery(query, getEntityType(), args).getSingleResult();
	}

	@Override
	public T queryForObject(String query, Map<String, Object> args) {
		return createQuery(query, getEntityType(), args).getSingleResult();
	}

	@Override
	public List<T> queryNamed(String queryName) {
		return createNamedQuery(queryName, getEntityType()).getResultList();
	}

	@Override
	public List<T> queryNamed(String queryName, Map<String, Object> args) {
		return createNamedQuery(queryName, getEntityType(), args).getResultList();
	}

	@Override
	public List<T> queryNamed(String queryName, Object[] args) {
		return createNamedQuery(queryName, getEntityType(), args).getResultList();
	}

	@Override
	public T queryNamedForObject(String queryName, Map<String, Object> args) {
		return createNamedQuery(queryName, getEntityType(), args).getSingleResult();
	}

	@Override
	public T queryNamedForObject(String queryName, Object[] args) {
		return createNamedQuery(queryName, getEntityType(), args).getSingleResult();
	}

	@Override
	public T get(PK id) {
		return getEntityManager().find(getEntityType(), id);
	}

	@Override
	public T load(PK id) {
		return getEntityManager().getReference(getEntityType(), id);
	}

	@Override
	public List<T> findAll() {
		return query("FROM " + entityType.getName());
	}

	@Override
	public int queryUpdate(String query, Map<String, Object> args) {
		return createQuery(query, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryUpdate(String query, Object[] args) {
		return createQuery(query, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNamedUpdate(String queryName, Map<String, Object> args) {
		return createNamedQuery(queryName, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNamedUpdate(String queryName, Object[] args) {
		return createNamedQuery(queryName, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNativeUpdate(String query, Object[] args) {
		return createNativeQuery(query, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNativeUpdate(String query, Map<String, Object> args) {
		return createNativeQuery(query, getEntityType(), args).executeUpdate();
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected Class<T> getEntityType() {
		return entityType;
	}
}