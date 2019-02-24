package com.harlansoft.restfulwebservices.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A simple bean for handling exception messages
 * @author it42874
 *
 */
@Getter
@Setter
@Builder
public class ExceptionResponse {
//	NB a structure like this should be consistent in the whole application
//	or even better on the whole organization
//	it should be language independent (should be able to use it even from Ruby apps)
	private Date timestamp;
	private String message;
	private String details;

}
