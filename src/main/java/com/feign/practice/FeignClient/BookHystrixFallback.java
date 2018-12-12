package com.feign.practice.FeignClient;

import com.feign.practice.domain.Book;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookHystrixFallback implements FallbackFactory<BookClient> {

    static Long index = Long.valueOf("1");
    @Override
    public BookClient create(Throwable throwable) {
        return new BookClient() {
            //fallbackMethod for BookClient
            @Override
            public List<Book> getBooks() {
                return null;
            }

            @Override
            public String getBookSpec() {
                return "Hystrix FallbackMethod";
            }

            @Override
            public Book update(Long bookId) {
                return new Book(index++,"isbn","author","title");
            }
        };
    }
}
