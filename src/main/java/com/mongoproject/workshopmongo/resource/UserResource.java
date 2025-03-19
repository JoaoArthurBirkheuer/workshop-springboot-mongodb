package com.mongoproject.workshopmongo.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongoproject.workshopmongo.domain.User;
import com.mongoproject.workshopmongo.dto.UserDTO;
import com.mongoproject.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService us;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> li = us.findAll();
		List<UserDTO> liDTO = li.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(liDTO);
	}
	
}
