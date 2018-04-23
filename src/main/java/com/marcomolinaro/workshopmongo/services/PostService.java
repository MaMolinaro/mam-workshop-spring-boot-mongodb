package com.marcomolinaro.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomolinaro.workshopmongo.domain.Post;
import com.marcomolinaro.workshopmongo.repository.PostRepository;
import com.marcomolinaro.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		//Tenta buscar apenas um item do banco cujo id seja correspondente
	    Optional<Post> post = postRepository.findById(id);
	     
	     if(!post.isPresent()) {
	    	 throw new ObjectNotFoundException("Post [" + id + "] não encontrado");
	     }
	 
	    return post.get();
	}
}