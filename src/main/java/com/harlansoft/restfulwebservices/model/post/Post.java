package com.harlansoft.restfulwebservices.model.post;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.harlansoft.restfulwebservices.model.users.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Entity
public class Post {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Integer id;
	
	private String title;
	
	private String message;
	
	@ManyToOne
	private User author;

}
