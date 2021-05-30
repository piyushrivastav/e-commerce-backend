package com.tom.tom.services;

import java.util.Optional;

import com.tom.tom.entities.Client;
import com.tom.tom.exceptions.ECommerceServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.tom.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	public Client find(Integer id) throws ECommerceServiceException {
		try {
			Optional<Client> obj = repo.findById(id);
			return obj.get();
		}catch (Exception exception){
			throw new ECommerceServiceException("Exception while finding client in repo",exception);
		}


	}
}