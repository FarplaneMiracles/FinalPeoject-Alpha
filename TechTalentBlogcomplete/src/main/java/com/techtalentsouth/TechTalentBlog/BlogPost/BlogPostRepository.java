package com.techtalentsouth.TechTalentBlog.BlogPost;


import com.techtalentsouth.TechTalentBlog.BlogPost.BlogPost;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{
	List<BlogPost> findAll(); 
	List<BlogPost> findByAuthorIgnoreCase(String author); 
	
	BlogPost deleteById(long id); 
	BlogPost findById(long id);
}
