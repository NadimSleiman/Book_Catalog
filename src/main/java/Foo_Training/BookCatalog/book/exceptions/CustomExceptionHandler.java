package Foo_Training.BookCatalog.book.exceptions;

import Foo_Training.BookCatalog.FooResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<FooResponse> handleBookNotFoundException(BookNotFoundException ex)
    {
        FooResponse response = FooResponse.builder().message(ex.getMessage()).status(false).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FooResponse> handleException(Exception ex)
    {
        FooResponse response = FooResponse.builder().message("Error Occurred").status(false).build();
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
