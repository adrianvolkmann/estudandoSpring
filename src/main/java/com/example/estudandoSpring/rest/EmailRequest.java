package com.example.estudandoSpring.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class EmailRequest {

	@NotNull(message= "campovazio")
	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
