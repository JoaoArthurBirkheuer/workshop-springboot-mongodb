package com.mongoproject.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoproject.workshopmongo.domain.Post;
import com.mongoproject.workshopmongo.repositories.PostRepository;
import com.mongoproject.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository pr;
	
	public Post findById(String id) {
	    Optional<Post> obj = pr.findById(id);
	    return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	/// ENCODE VALUE IN THE URL
	/// CREATING UTILITARY CLASS
	public List<Post> findByTitle(String title){
		//return pr.findByTitleContaining(title); 
		return pr.searchByTitle(title);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return pr.fullSearch(text, minDate, maxDate);
	}
}
