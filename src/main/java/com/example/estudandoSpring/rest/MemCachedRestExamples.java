package com.example.estudandoSpring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.estudandoSpring.rest.model.CreateCacheModel;

import net.spy.memcached.MemcachedClient;

@RestController
public class MemCachedRestExamples {

	private static final int EXPIRATION = 60; // 1 min

	@Autowired
	MemcachedClient memcached;

	@PostMapping(value = "/createCache")
	public void createCache(@RequestBody CreateCacheModel createCache) {
		memcached.set(createCache.getKey(), EXPIRATION, createCache.getValue());
	}

	@GetMapping(value = "/readCache")
	public Integer readCache(@RequestParam String key) {

		 String readed = (String) memcached.get(key);

		 
		 if(readed!=null) {
			 return  Integer.valueOf(readed) ;
		 }
		 
		return null;
		//if (value == null) {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND, "key not found");
		//} else {
			//return value;
		//}
	}

}
