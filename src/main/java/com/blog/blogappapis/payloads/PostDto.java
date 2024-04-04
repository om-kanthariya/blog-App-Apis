package com.blog.blogappapis.payloads;

import java.sql.Date;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer PostId;
	private String title;
	private String content;
	private String imageName;
	private Date addeDate;
	
	private CategoryDto category;
	private UserDto user;
}
