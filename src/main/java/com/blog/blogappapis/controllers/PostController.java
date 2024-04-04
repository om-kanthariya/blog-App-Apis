package com.blog.blogappapis.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogappapis.payloads.ApiResponse;
import com.blog.blogappapis.payloads.PostDto;
import com.blog.blogappapis.services.FileService;
import com.blog.blogappapis.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired				
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	// get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> postDtos = this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	// get by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//get all post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		
		List<PostDto> allPosts = this.postService.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
	}
	
	// get single post
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto post = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	//delete Post
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deleteCategory( @PathVariable Integer postId) {
		
		this.postService.deletePost(postId);
		
		return new ApiResponse("Post successfully deleted" , true);
		
	}
	
	
	// Update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		
		PostDto postupdateDto =  this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(postupdateDto,HttpStatus.OK);
		
	}
	
	//search post
	@GetMapping("/posts/search/{keyword}")
public ResponseEntity<List<PostDto>> searchPost( @PathVariable("keyword") String keywords) {
		
		List<PostDto> result = this.postService.searchPost(keywords);
		
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
		
	}
	
	
	//upload post image
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image , @PathVariable Integer postId) throws IOException{
		
		PostDto postDto =  this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatepost =  this.postService.updatePost(postDto, postId);
//		System.out.print(fileName);
		
		return new ResponseEntity<PostDto>(updatepost, HttpStatus.OK);
	}
	
	
	//method to serve the files
	
	@GetMapping(value = "post/image/{imageName}" ,  produces = MediaType.IMAGE_JPEG_VALUE)
    public void serveFile(@PathVariable String imageName, HttpServletResponse response) {
        try {
           
        	InputStream resource = this.fileService.getResource(path, imageName);
        	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        	StreamUtils.copy(resource, response.getOutputStream());
       
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
}
