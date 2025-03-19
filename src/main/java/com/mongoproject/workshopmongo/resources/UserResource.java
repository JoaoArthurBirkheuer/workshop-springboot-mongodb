package com.mongoproject.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User u = us.findById(id);
		return ResponseEntity.ok().body(new UserDTO(u));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO uDTO){
		User u = us.fromDTO(uDTO);
		u = us.insert(u);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path
				("/{id}").buildAndExpand(u.getId()).toUri();
		/// RETURNS 201
		return ResponseEntity.created(uri).build();
	}
	
}
