package com.marcomolinaro.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomolinaro.workshopmongo.domain.User;
import com.marcomolinaro.workshopmongo.repository.UserRepository;
import com.marcomolinaro.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		//Tenta buscar apenas um item do banco cujo id seja correspondente
	    Optional<User> user = userRepository.findById(id);
	     
	     if(!user.isPresent()) {
	    	 throw new ObjectNotFoundException("Usuário [" + id + "] não encontrado");
	     }
	 
	    return user.get();
	}
}
