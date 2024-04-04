package com.blog.blogappapis.services;

import java.util.List;

import com.blog.blogappapis.entities.Post;
import com.blog.blogappapis.payloads.PostDto;

public interface PostService {

	//create 
	PostDto createPost(PostDto postDto, Integer userId ,  Integer categoryId);
	
	//update 
	
	PostDto updatePost (PostDto postDto , Integer postID);
	
	//delete
	
	void deletePost(Integer postId);
	
	//get all Post
	
	List<PostDto> getAllPost();
	
	//get single post
	
	PostDto getPostById(Integer postId);
	
	// get all post by category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	
	List<PostDto> getPostByUser(Integer UserId);
	
	//search post
	
	List<PostDto> searchPost(String keyword);
	
	
}
