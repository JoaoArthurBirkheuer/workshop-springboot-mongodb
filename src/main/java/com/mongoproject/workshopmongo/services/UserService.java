package com.mongoproject.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoproject.workshopmongo.domain.User;
import com.mongoproject.workshopmongo.repositories.UserRepository;
import com.mongoproject.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository ur;
	
	public List<User> findAll(){
		return ur.findAll();
	}
	
	public User findById(String id) {
	    Optional<User> obj = ur.findById(id);
	    return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

}
