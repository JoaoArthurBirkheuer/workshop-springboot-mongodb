package com.mongoproject.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoproject.workshopmongo.domain.User;
import com.mongoproject.workshopmongo.dto.UserDTO;
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
	
	public User insert(User u) {
		return ur.insert(u);
	}
	
	public User fromDTO(UserDTO uDTO) {
		return new User(uDTO.getId(),uDTO.getName(),uDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		ur.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return ur.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		// TODO Auto-generated method stub
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}
	
	

}
