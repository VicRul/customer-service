package org.vicrul.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Field cannot be empty")
public class AddressFieldException extends RuntimeException {

}
