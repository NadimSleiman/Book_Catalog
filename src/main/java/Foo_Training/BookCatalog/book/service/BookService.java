package Foo_Training.BookCatalog.book.service;

import Foo_Training.BookCatalog.book.dto.BookDto;
import Foo_Training.BookCatalog.book.entity.Book;
import Foo_Training.BookCatalog.book.exceptions.BookNotFoundException;
import Foo_Training.BookCatalog.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;
    public List<BookDto> getAllBooks()
    {
        List<Book> books =  bookRepository.findAll();
        List<BookDto> booksDto = new ArrayList<>();
        for(Book b : books)
        {
            booksDto.add(new BookDto(b.getId(),b.getTitle(),b.getAuthor(),b.getPublicationYear()));
        }
        return booksDto;
    }

    public BookDto getBook(long id)
    {
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent())
            throw new BookNotFoundException("Book with ID: " + id + " is not found");
        return new BookDto(book.get().getId(),book.get().getTitle(),book.get().getAuthor(),book.get().getPublicationYear());
    }

    public BookDto addBook(Book book)
    {
        bookRepository.save(book);
        return new BookDto(book.getId(),book.getTitle(),book.getAuthor(),book.getPublicationYear());
    }

    public BookDto removeBook(long id) {
        BookDto book = getBook(id);
        bookRepository.deleteById(id);
        return new BookDto(book.getId(),book.getTitle(),book.getAuthor(),book.getPublicationYear());
    }
}
