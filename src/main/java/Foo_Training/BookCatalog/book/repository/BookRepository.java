package Foo_Training.BookCatalog.book.repository;

import Foo_Training.BookCatalog.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
