package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
public class BlogPostController {
	
		@Autowired
		private BlogPostRepository blogPostRepository; 
		//will have to at some point use sql to read from our database
		

		//private static List<BlogPost> posts = FakeBlogs.makeFakeBlogs(); 
		//posts is just as empty as FakeBlogs.blogPosts 
		List<BlogPost> posts = new ArrayList<>();
		
		@GetMapping(value="/")
		public String index(BlogPost blogPost, Model model) {
	//get all blog posts
		posts = blogPostRepository.findAll();
	//add posts to model
		model.addAttribute("posts", posts);
			return "blogpost/index"; 
		}
		
		@GetMapping(value="/blogpost/new")
		public String newBlog(BlogPost blogPost) {
			return "blogpost/new"; 
		}
		
		@PostMapping(value="/blogpost/new")
		public String addNewBlogPost(BlogPost blogPost, Model model) {
			//saves the new blogPost item to the database
			blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry(), 0));
			
			
			//populating result.html with blogpost info
			model.addAttribute("title", blogPost.getTitle());
			model.addAttribute("author", blogPost.getAuthor());
			model.addAttribute("blogEntry", blogPost.getBlogEntry());
			model.addAttribute("id", blogPost.getId());
			return "blogpost/result"; 
		}
		
		@PostMapping(value="/blogpost/author")
		public String searchByAuthor(BlogPost blogPost, String author, Model model){
			System.out.println(author); 
	//return all blog posts by an author
//			posts = FakeBlogs.searchByAuthor(author); 
			posts = blogPostRepository.findByAuthorIgnoreCase(author);

			//add posts to model
			model.addAttribute("posts", posts);
			
			return "blogpost/index"; 
		}
		
		@RequestMapping(value="/blogpost/{id}", method = RequestMethod.POST)
		public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
			System.out.println(id);
			
			//FakeBlogs.deletePost(id); 
			blogPostRepository.deleteById(id); 
	//get all blog posts
			posts = blogPostRepository.findAll();
	//add posts to model		
			model.addAttribute("posts", posts);
			return "blogpost/index"; 
			
		}
		

			
		@RequestMapping(value="/blogpost/edit/{id}", method = RequestMethod.POST)
		public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
			System.out.printf("Edit Route: ID: %s\n", id);
			
			//BlogPost post = FakeBlogs.getBlogById(id); 
			BlogPost post = blogPostRepository.findById(id).orElse(null); 
			if(post != null) {
				System.out.println(post.getId());
				model.addAttribute(post);
			} else {
				System.out.printf("Could not find post at %s/n", id);
			}
			
			return "blogpost/edit"; 
		}
		
		@RequestMapping(value="/blogpost/edit", method = RequestMethod.POST)
		public String updatePost(Long id, BlogPost blog, Model model) {
			System.out.println(id);
			System.out.println( blog.getTitle());
			System.out.println(blog.getAuthor());
			System.out.println(blog.getBlogEntry());
			
			//FakeBlogs.updateBlogPost(id, blog.getTitle(), blog.getAuthor(), blog.getBlogEntry());
			BlogPost update = blogPostRepository.findById(id).orElse(null);
			if(update != null) {
				update.setAuthor(blog.getAuthor());
				update.setTitle(blog.getTitle());
				update.setBlogEntry(blog.getBlogEntry());
				blogPostRepository.save(update); 
			} else {
				System.out.printf("Could not find post at %s/n", id);
			}
			
				
			model.addAttribute("title", update.getTitle());
			model.addAttribute("author", update.getAuthor());
			model.addAttribute("blogEntry", update.getBlogEntry());
			model.addAttribute("id", update.getId());
			return "blogpost/result"; 
			
		}


	

}
