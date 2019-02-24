package com.harlansoft.restfulwebservices.model.users;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@EqualsAndHashCode.Include
	private Integer id;
	
	@Size(min = 2, message = "Name should have at least 2 characters")
	private String name;
	
	@Past
	private Date birthDate;
	
}
