package org.vicrul.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "All submitted data must be verified")
public class EmptyRequestParamsException extends RuntimeException {

}
