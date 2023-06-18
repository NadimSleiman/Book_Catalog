package Foo_Training.BookCatalog.book.service;

import Foo_Training.BookCatalog.book.dto.BookDto;
import Foo_Training.BookCatalog.book.entity.Book;
import Foo_Training.BookCatalog.book.exceptions.BookNotFoundException;
import Foo_Training.BookCatalog.book.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;

    private final ObjectMapper objectMapper;

    private BookDto convertToDto(Book book) {
        return objectMapper.convertValue(book, BookDto.class);
    }

    public List<BookDto> getAllBooks()
    {
        List<Book> books =  bookRepository.findAll();
        List<BookDto> booksDto = new ArrayList<>();
        for(Book b : books)
        {
            booksDto.add(convertToDto(b));
        }
        return booksDto;
    }

    public BookDto getBook(long id)
    {
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent())
            throw new BookNotFoundException("Book with ID: " + id + " is not found");
        return convertToDto(book.get());
    }

    public BookDto addBook(Book book)
    {
        bookRepository.save(book);
        return convertToDto(book);
    }

    public BookDto removeBook(long id) {
        BookDto book = getBook(id);
        bookRepository.deleteById(id);
        return book;
    }
}
