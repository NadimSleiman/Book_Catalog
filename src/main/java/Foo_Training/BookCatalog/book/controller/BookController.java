package Foo_Training.BookCatalog.book.controller;

import Foo_Training.BookCatalog.FooResponse;
import Foo_Training.BookCatalog.book.dto.BookDto;
import Foo_Training.BookCatalog.book.entity.Book;
import Foo_Training.BookCatalog.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Books")
@Tag(name = "Book Controller", description = "API endpoints for Books")
public class BookController {

    private final BookService bookService;
    @GetMapping()
    @Operation(summary = "Get all Books")
    public ResponseEntity<FooResponse> getAllBooks()
    {
        List<BookDto> books = bookService.getAllBooks();
        FooResponse response = FooResponse.builder().data(books).message("Getting Books from Database").status(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary ="Get Book by Id")
    public ResponseEntity<FooResponse> getBook(@PathVariable long id)
    {
        BookDto book = bookService.getBook(id);
        FooResponse response = FooResponse.builder().data(book).message("Getting Book " + id + " from Database").status(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/Create")
    @Operation(summary ="Creating a Book")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Book Created Successfully"),
            @ApiResponse(responseCode = "404", description = "Book Invalid")

    })
    public ResponseEntity<FooResponse> addBook(@RequestBody @Valid Book book)
    {
        BookDto bookDto = bookService.addBook(book);
        FooResponse response = FooResponse.builder().data(book).message("Book Created Successfully").status(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{id}")
    @Operation(summary ="Get Product by Id")
    public ResponseEntity<FooResponse> removeBook(@PathVariable long id)
    {
        BookDto bookDto = bookService.removeBook(id);
        FooResponse response = FooResponse.builder().data(bookDto).message("Book " + id + " Deleted Successfully").status(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
