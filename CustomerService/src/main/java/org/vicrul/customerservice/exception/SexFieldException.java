package org.vicrul.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Field 'sex' must be filled in and have values only 'male' or 'female'")
public class SexFieldException extends RuntimeException{
	
}
