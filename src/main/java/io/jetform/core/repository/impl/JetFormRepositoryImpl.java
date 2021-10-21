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

	public Object getEntity(Long id,Class<?> clazz) {
		TypedQuery<?> query=entityManager.createQuery("select e from "+clazz.getName()+" e where id="+id, clazz);
		Object entity = query.getSingleResult();
		
		return entity;
	}
	
}