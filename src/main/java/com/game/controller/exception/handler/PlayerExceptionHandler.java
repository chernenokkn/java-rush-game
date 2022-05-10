package com.game.controller.exception.handler;

import com.game.exception.BadRequestException;
import com.game.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(PlayerNotFoundException.class)
  void handleNotFoundException(PlayerNotFoundException ex) {

  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  void handleBadRequestException(BadRequestException ex) {

  }
}
