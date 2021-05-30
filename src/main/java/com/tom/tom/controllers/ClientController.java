package com.tom.tom.controllers;

import com.tom.tom.entities.Client;
import com.tom.tom.exceptions.ECommerceServiceException;
import com.tom.tom.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/client")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>find(@PathVariable Integer id) throws ECommerceServiceException {
		Client obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
		
		
	}

}
