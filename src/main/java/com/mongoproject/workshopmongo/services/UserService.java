package com.mongoproject.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoproject.workshopmongo.domain.User;
import com.mongoproject.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository ur;
	
	public List<User> findAll(){
		return ur.findAll();
	}
}
