package org._4.gtree.advice;

import org._4.gtree.exception.NoSuchElementException;
import org._4.gtree.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFound(NotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handlerNoSuchElementException(NoSuchElementException e) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

  }

}
