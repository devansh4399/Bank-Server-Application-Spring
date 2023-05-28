package com.cg.bankapplication.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.bankapplication.exceptions.InsufficientBalanceException;
import com.cg.bankapplication.exceptions.InvalidAccountNumberException;
import com.cg.bankapplication.exceptions.NegativeAmountException;
import com.cg.bankapplication.exceptions.SameAccountNumberException;
@ControllerAdvice
public class CustomExceptionHandler {
@ExceptionHandler(InvalidAccountNumberException.class)
public ResponseEntity<String> handleInvalidAccountNumberException(InvalidAccountNumberException e)
{
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
}
@ExceptionHandler(InsufficientBalanceException.class)
public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException e)
{
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
}
@ExceptionHandler(SameAccountNumberException.class)
public ResponseEntity<String> handleSameAccountNumberException(SameAccountNumberException e)
{
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
}
@ExceptionHandler(NegativeAmountException.class)
public ResponseEntity<String> handleNegativeAmountException(NegativeAmountException e)
{
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
}

}
