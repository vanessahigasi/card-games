package tech.bts.cardgames.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="game not found")
public class GameNotExistException extends RuntimeException {
}
