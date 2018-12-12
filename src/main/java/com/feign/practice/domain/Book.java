package com.feign.practice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor //all parameter constructor
public class Book {
/*In fact, our REST provider is a hypermedia-driven API,
so we’ll need a simple wrapper class
*/
    @Id @GeneratedValue //generatedValue default = AUTO
    private Long bookId; // will be set when persisting
    private String isbn;
    private String author;
    private String title;

}
