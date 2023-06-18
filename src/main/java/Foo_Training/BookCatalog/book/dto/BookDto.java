package Foo_Training.BookCatalog.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookDto {
    private long id;

    private String title;

    private String author;

    private String publicationYear;

    public BookDto(){}
}
