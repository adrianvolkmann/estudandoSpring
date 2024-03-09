package com.example.estudandoSpring.config;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import net.spy.memcached.MemcachedClient;

@Configuration
public class ApplicationConfiguration extends AcceptHeaderLocaleResolver {

	Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	@Value("${memcached.address}")
	private String memcachedAddress;

	@Value("${memcached.port}")
	private int memcachedPort;

	@Bean
	MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("translations/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public MemcachedClient memcachedClient() {
		//docker container run --name memcached -d -p 11211:11211 memcached
		MemcachedClient memcachet = null;
		try {
			memcachet = new MemcachedClient(new InetSocketAddress(memcachedAddress, memcachedPort));
		} catch (IOException e) {
			LOGGER.error("Error to connect memchaced!!!");
		}
		return memcachet;
	}

}