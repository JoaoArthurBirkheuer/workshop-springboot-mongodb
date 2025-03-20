package com.mongoproject.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mongoproject.workshopmongo.domain.Post;
import com.mongoproject.workshopmongo.domain.User;
import com.mongoproject.workshopmongo.dto.AuthorDTO;
import com.mongoproject.workshopmongo.dto.CommentDTO;
import com.mongoproject.workshopmongo.repositories.PostRepository;
import com.mongoproject.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private PostRepository pr;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		ur.deleteAll();
		pr.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// MUST SAME HERE BEFORE COPYING TO THE AGGREGATE
		ur.saveAll(Arrays.asList(maria,alex,bob));
		
		Post p1 = new Post(null,sdf.parse("21/08/2024"),
				"Partiu Viagem","Vou viajar pra SP", new AuthorDTO(maria));
		Post p2 = new Post(null,sdf.parse("22/08/2024"),
				"Cheguei","Cheguei em SP", new AuthorDTO(maria));
		
		// ur.saveAll(Arrays.asList(maria,alex,bob));
		
		// ADDING COMMENTS BEFORE SAVING POSTS
		CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("20/03/2025"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("20/03/2025"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Bom dia pra vc", sdf.parse("20/03/2025"), new AuthorDTO(alex));
		
		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().addAll(Arrays.asList(c3));
		
		// WHAT GOES IN THE MONGO COMPASS IS A COPY, NOT A REFERENCE
		pr.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		
		ur.save(maria);
	}
	

}
