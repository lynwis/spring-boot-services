package com.harlansoft.restfulwebservices.resources.users.exception;

// in this way the http status sent back is changed to 404 instead of the generic 500
//@ResponseStatus(HttpStatus.NOT_FOUND) -> moved in global exception handler
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4569296629054335769L;
	
	public UserNotFoundException(String message) {
		super(message);
	}

}

// NB it's best to choose and adhere to a common structure for error messages
// in the whole rest api. Every service should signal errors in a consistent way
