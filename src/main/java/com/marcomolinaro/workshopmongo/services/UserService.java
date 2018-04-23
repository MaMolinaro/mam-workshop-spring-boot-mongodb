package com.marcomolinaro.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomolinaro.workshopmongo.domain.User;
import com.marcomolinaro.workshopmongo.dto.UserDTO;
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
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		this.findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = userRepository.findById(user.getId()).get();
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}
