package br.com.my.seguranca;

import java.util.Set;

public class UsuarioLogadoDetalhes {

	private String id;
	
	private String email;

	private String firstName;

	private String lastName;

	private Set<String> roles;
	
	public UsuarioLogadoDetalhes(String id, String firstName, String lastName, String email, Set<String> roles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public String getId() {
		return id;
	}
}
