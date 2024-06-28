package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Builder
public class Book {
    private String title;
    private String author;
    private int year;
    private String isbn;
}
