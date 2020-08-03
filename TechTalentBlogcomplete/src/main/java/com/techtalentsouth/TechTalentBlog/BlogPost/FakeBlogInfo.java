package com.techtalentsouth.TechTalentBlog.BlogPost;
import java.util.ArrayList;
import java.util.List;


public class FakeBlogInfo {
	
private static List<BlogPost> blogPosts;

public static List<BlogPost> FakeBlogInfo() {
	blogPosts = new ArrayList<BlogPost>();

blogPosts.add(new BlogPost("First Blog", "Leslie", "Eggnog tree cookie wreath give give, jingle bells wreath icicle Christmas nutcracker bells. Frosty santa goodwill reindeer love fireplace stocking goodwill, give noel guest reindeer. Noel holiday chestnuts carols, cookie goodwill frosty love eggnog goodwill guest. Elves chestnuts jingle bells goodwill angel stocking icicle partridge, love gold scarf holiday decorate.", 1)); 
	
	blogPosts.add(new BlogPost("Second", "Not Leslie", "Bells frosty chestnuts scarf lights chimney, gift december ivy celebrate cookie. Angel give elves scarf partridge celebrate santa. Elves wreath partridge gift candy cane, frosty jingle bells holiday reindeer love lights noel santa elves. Yule fireplace chestnuts ornament holiday scarf, eggnog gift love gold calendar. ", 2));
	
	blogPosts.add(new BlogPost("Third", "Still Not Leslie", "Frosty goodwill tree december fireplace, cookie give noel partridge ivy fireplace chimney tree. Love noel cookie cookie singing fireplace scarf. Wreath toys toys santa tree reindeer elves eggnog gold, Christmas love eggnog chimney jingle bells. ", 3));
	
	blogPosts.add(new BlogPost("Fourth", "Might be Leslie", "December tree candy cane ornament jingle bells eggnog frosty icicle holiday. Guest candy cane yule celebrate tree wreath holiday give holiday, calendar cookie wreath santa.", 4 ));
	
	return blogPosts;
	
}

//function that returns the blog posts
public static List<BlogPost> allBlogs(){
   return blogPosts; 
}

//Returns blog post by id
public static BlogPost getBlogById(long id) {
    for(BlogPost post : blogPosts) {
        if(post.getId() == id) {
            return post;
        }
    }
    System.out.println("Id not found");
    return null;
}

//RETURNS BLOG BY AUTHOR
public static List<BlogPost> getBlogByAuthor(String author) {
	List<BlogPost> authorList = new ArrayList<>();
	System.out.printf("GEt BLOG BY AuTHOR: AUTHOR: %s", author);
  for(BlogPost post : blogPosts) {
      if(post.getAuthor().equalsIgnoreCase(author)) {
    	  authorList.add(post);
      }
  }
  if (authorList.size() > 0) {
	  System.out.println(authorList);
	  return authorList;
  }
  else return null;
} 

	
	private static FakeBlogInfo fakeBlogPosts = null;
	
//	Creates an instances of a blog
	public static FakeBlogInfo getInstance() {
		if(fakeBlogPosts == null) {
			fakeBlogPosts = new FakeBlogInfo(); 
			
		}
		return fakeBlogPosts;
	}
	
//	Creates a new blog post
	public static BlogPost createBlogPost(String title, String author, String blogEntry) {
		long id = createId();
        BlogPost newBlog = new BlogPost(title, author, blogEntry, id);
        return newBlog;
    }
	
//	add new blog post
	public static void addNewPost(BlogPost newBlog ) {
		blogPosts.add(newBlog);
	}
	
//	creates unique id
	public static long createId() {
		System.out.println("createID started");
		int length = blogPosts.size();
		System.out.println(length);
		BlogPost lastPost = blogPosts.get(length-1);
		long myID = lastPost.getId()+1;
		return myID;
	}
	
//	Updates the blog
	public static BlogPost updateBlogPost(long id, String title, String author, String blogEntry) {
        for(BlogPost post : blogPosts) {
            if(post.getId() == id) {
                int postIndex = blogPosts.indexOf(post);
                post.setTitle(title);
                post.setBlogEntry(blogEntry);
                blogPosts.set(postIndex, post);
                return post;
            }

        }

        return null;
    }
	
//	method to delete blogs
	public static boolean deletePost(long id){
		
		System.out.println("FAKL BLOGS DELETE");
		
        int postIndex = -1;
        for(BlogPost post : blogPosts) {
            if(post.getId() == id) {
                postIndex = blogPosts.indexOf(post);
                continue;
            }
        }
        if(postIndex > -1){
            blogPosts.remove(postIndex);
        }
        return true;
    }


}


