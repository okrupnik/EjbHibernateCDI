package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ExampleCDI implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;

	private String logged;
	private String created;

	@EJB
	private ExampleEJB exampleEJB;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogged() {
		return logged;
	}

	public void setLogged(String logged) {
		this.logged = logged;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String checkUser() {
		if (exampleEJB.checkUser(login, password)) {
			logged = "Hello " + login + ", you logged sucessfully";
		} else {
			logged = "Wrong login or password";
		}
		return "hello";
	}

	public String createUser() {
		if (exampleEJB.createUser(login, password)) {
			created = "User with login " + login + " created sucessfully";
		} else {
			created = "User with login " + login + " don't created";
		}
		return "create";
	}

	public List<UserEntity> getListOfUser() {
		return exampleEJB.getAllUser();
	}
}
