package com.harlansoft.restfulwebservices.model.users;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "About the User...")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotBlank
	@Size(min = 2, message = "Name should have at least 2 characters")
	@ApiModelProperty(notes = "At least 2 chars")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "Birthdate should be past")
	private Date birthDate;
	
}
