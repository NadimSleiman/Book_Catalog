package Foo_Training.BookCatalog.book.service;
import Foo_Training.BookCatalog.book.dto.BookDto;
import Foo_Training.BookCatalog.book.entity.Book;
import Foo_Training.BookCatalog.book.exceptions.BookNotFoundException;
import Foo_Training.BookCatalog.book.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository,objectMapper);
    }

    @Test
    void findBookById_ValidId_ReturnsBookDTO() {

        long bookId = 1;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setPublicationYear("2023");
        Optional<Book> optionalBook = Optional.of(book);

        when(objectMapper.convertValue(book, BookDto.class)).thenReturn(new BookDto(bookId, book.getTitle(), book.getAuthor(), book.getPublicationYear()));
        when(bookRepository.findById(bookId)).thenReturn(optionalBook);


        BookDto bookDTO = bookService.getBook(bookId);


        assertNotNull(bookDTO);
        assertEquals(bookId, bookDTO.getId());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getAuthor(), bookDTO.getAuthor());
        assertEquals(book.getPublicationYear(), bookDTO.getPublicationYear());

        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void findBookById_InvalidId_ThrowsBookNotFoundException() {

        long bookId = 1;
        Optional<Book> optionalBook = Optional.empty();



        when(bookRepository.findById(bookId)).thenReturn(optionalBook);


        assertThrows(BookNotFoundException.class, () -> bookService.getBook(bookId));

        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void findAllBooks_ReturnsListOfBookDTO() {

        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");
        book1.setPublicationYear("2023");
        books.add(book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");
        book2.setPublicationYear("2022");
        books.add(book2);

        when(objectMapper.convertValue(eq(book1), eq(BookDto.class))).thenReturn(new BookDto(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getPublicationYear()));
        when(objectMapper.convertValue(eq(book2), eq(BookDto.class))).thenReturn(new BookDto(book2.getId(), book2.getTitle(), book2.getAuthor(), book2.getPublicationYear()));
        when(bookRepository.findAll()).thenReturn(books);


        List<BookDto> bookDTOs = bookService.getAllBooks();


        assertNotNull(bookDTOs);
        assertEquals(2, bookDTOs.size());
        assertEquals(book1.getId(), bookDTOs.get(0).getId());
        assertEquals(book1.getTitle(), bookDTOs.get(0).getTitle());
        assertEquals(book1.getAuthor(), bookDTOs.get(0).getAuthor());
        assertEquals(book1.getPublicationYear(), bookDTOs.get(0).getPublicationYear());
        assertEquals(book2.getId(), bookDTOs.get(1).getId());
        assertEquals(book2.getTitle(), bookDTOs.get(1).getTitle());
        assertEquals(book2.getAuthor(), bookDTOs.get(1).getAuthor());
        assertEquals(book2.getPublicationYear(), bookDTOs.get(1).getPublicationYear());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void save_ValidBook_InvokesBookRepositorySave() {
        // Arrange
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setPublicationYear("2023");

        // Act
        bookService.addBook(book);

        // Assert
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void delete_ExistingBookId_InvokesBookRepositoryDeleteById() {
        // Arrange
        long bookId = 1;
        Optional<Book> optionalBook = Optional.of(new Book());

        when(bookRepository.findById(bookId)).thenReturn(optionalBook);

        // Act
        bookService.removeBook(bookId);

        // Assert
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void delete_NonExistingBookId_ThrowsBookNotFoundException() {
        // Arrange
        long bookId = 1;
        Optional<Book> optionalBook = Optional.empty();

        when(bookRepository.findById(bookId)).thenReturn(optionalBook);

        // Act & Assert
        assertThrows(BookNotFoundException.class, () -> bookService.removeBook(bookId));

        verify(bookRepository, never()).deleteById(bookId);
    }
}