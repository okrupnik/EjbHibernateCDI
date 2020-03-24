package com.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;

@Stateless
public class ExampleEJB {

	@PersistenceContext(unitName = "examplePU")
	private EntityManager entityManager;

	public boolean checkUser(String login, String password) {

		if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
			return false;
		}

		UserEntity userEntity = entityManager.find(UserEntity.class, login);
		if (null == userEntity) {
			return false;
		} else {
			if (password.equals(userEntity.getPassword())) {
				return true;
			}
		}

		return false;
	}
	
	public boolean createUser(String login, String password) {

		if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
			return false;
		}

		UserEntity userEntity = entityManager.find(UserEntity.class, login);
		if (null != userEntity) {
			return false;
		} 
		
		userEntity = new UserEntity();
		userEntity.setLogin(login);
		userEntity.setPassword(password);

		entityManager.persist(userEntity);
		
		return true;
	}
	
	public List<UserEntity> getAllUser() {
		return entityManager.createQuery("select entity from UserEntity entity").getResultList();
	}
}
