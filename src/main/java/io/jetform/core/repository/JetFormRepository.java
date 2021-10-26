package io.jetform.core.repository;

import java.util.List;

public interface JetFormRepository {

	List getAll(Class<?> clazz);
	Object getEntity(Long id,Class<?> clazz);
	Object save(Object object);
	void delete(Long id,Class<?> clazz);
	boolean deleteMultiple(Long[]id,Class<?> clazz);
}
