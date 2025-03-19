package com.mongoproject.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mongoproject.workshopmongo.domain.Post;
import com.mongoproject.workshopmongo.domain.User;
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
		
		Post p1 = new Post(null,sdf.parse("21/08/2024"),
				"Partiu Viagem","Vou viajar pra SP", maria);
		Post p2 = new Post(null,sdf.parse("22/08/2024"),
				"Cheguei","Cheguei em SP", maria);
		
		ur.saveAll(Arrays.asList(maria,alex,bob));
		
		// WHAT GOES IN THE MONGO COMPASS IS A COPY, NOT A REFERENCE
		pr.saveAll(Arrays.asList(p1,p2));
	}
	

}
