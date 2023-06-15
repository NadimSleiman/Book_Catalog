package Foo_Training.BookCatalog.book.service;

import Foo_Training.BookCatalog.book.dto.BookDto;
import Foo_Training.BookCatalog.book.entity.Book;
import Foo_Training.BookCatalog.book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository);
    }


    @Test
    void getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"Harry Potter","Nadim","2002"));
        books.add(new Book(2L,"Harry Potter Goblet of Fire","Samer","2012"));
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDto> books2 = bookService.getAllBooks();
        assertEquals(books2.size(),2);
    }

    @Test
    void getBook() {
        Long bookId = 1L;
        Book book = new Book(bookId,"Harry Potter","Nadim","2002");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        BookDto pDto = new BookDto(book.getId(),book.getTitle(),book.getAuthor(),book.getPublicationYear());
        assertEquals(pDto, bookService.getBook(bookId));
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void addBook() {
        Book book = new Book(1L,"Harry Potter","Nadim","2002");
        bookService.addBook(book);
        verify(bookRepository,times(1)).save(book);
    }

    @Test
    void removeBook() {
        Long bookId = 1L;

        bookService.removeBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}