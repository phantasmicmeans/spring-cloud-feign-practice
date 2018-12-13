package com.feign.practice.FeignClient;

import com.feign.practice.domain.Book;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BookClient{

    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public String getBookSpec() {
        return "bookSpec";
    }

    @Override
    public Book update(Long bookId) {
        return null;
    }
}
