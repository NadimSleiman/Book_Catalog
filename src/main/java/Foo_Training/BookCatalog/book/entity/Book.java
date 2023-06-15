package Foo_Training.BookCatalog.book.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "Books")
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Year is required")
    @Positive(message = "Publication year must be a positive or zero value")
    @JsonFormat(pattern = "yyyy")
    private String publicationYear;

    public Book(){}


}
