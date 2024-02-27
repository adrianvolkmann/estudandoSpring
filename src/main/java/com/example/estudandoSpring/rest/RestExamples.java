package com.example.estudandoSpring.rest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
public class RestExamples {

	@Autowired
	ResourceBundleMessageSource messageSource;

	//basta enviar "Accept-Language" com "en-US" que ja faz a traducao 
	@PostMapping(value = "/hello")
	public String helloWorld(@RequestBody @Valid EmailRequest email, BindingResult bindingResult, Locale locale)
			throws Exception {

		if (bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na validacao no email");
		}
		System.out.println("Default Locale= " + Locale.getDefault());
		System.out.println("endpoint locale= " + locale);

		return messageSource.getMessage("helloworld", null, locale);
	}

}
