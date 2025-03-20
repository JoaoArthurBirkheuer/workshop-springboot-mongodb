package com.mongoproject.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongoproject.workshopmongo.domain.Post;
import com.mongoproject.workshopmongo.resources.util.URL;
import com.mongoproject.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService ps;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post p = ps.findById(id);
		return ResponseEntity.ok().body(p);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle
	(@RequestParam(value = "text", defaultValue = "")
	String txt){
		
		txt = URL.decodeParameter(txt);
		List<Post> list = ps.findByTitle(txt);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch
	(@RequestParam(value = "text", defaultValue = "")
	String txt
	,@RequestParam(defaultValue = "")
	String minDate
	,@RequestParam(defaultValue= "")
	String maxDate){
		
		txt = URL.decodeParameter(txt);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = ps.fullSearch(txt,min,max);
		return ResponseEntity.ok().body(list);
	}
	
}
