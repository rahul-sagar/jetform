package io.jetform.core.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.jetform.core.repository.JetFormRepository;

@Repository
public class JetFormRepositoryImpl implements JetFormRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	@Transactional
	public List getAll(Class<?> clazz) {
		
		TypedQuery<?> query=entityManager.createQuery("select e from "+clazz.getName()+" e ",clazz);
		return query.getResultList();
	}
	
	@Transactional
	public Object getEntity(Long id,Class<?> clazz) {
		TypedQuery<?> query=entityManager.createQuery("select e from "+clazz.getName()+" e where id="+id, clazz);
		Object entity = query.getSingleResult();
		
		return entity;
	}

	@Override
	@Transactional
	public Object save(Object object) {
		Object saveEntity = entityManager.merge(object);
		return saveEntity;
	}

	@Override
	@Transactional
	public void delete(Long id,Class<?> clazz) {
		Object entity = getEntity(id, clazz);
		entityManager.remove(entity);
//		entityManager.remo
	}
	
	public boolean deleteMultiple(Long[]id,Class<?> clazz) {
		entityManager.createQuery("delete from "+clazz.getName()+" where IN "+"(8,9,11)",clazz);
		return true;
	}
	
}