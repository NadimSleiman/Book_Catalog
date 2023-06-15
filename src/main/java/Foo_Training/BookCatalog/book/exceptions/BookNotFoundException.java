package Foo_Training.BookCatalog.book.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String msg){
        super(msg);
    }
}
