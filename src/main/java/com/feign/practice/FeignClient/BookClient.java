package com.feign.practice.FeignClient;

import com.feign.practice.domain.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//A central concept in Spring Cloud’s Feign support is that of the named client.
@FeignClient(value = "books", configuration = BookConfiguration.class,
        fallbackFactory = BookHystrixFallback.class, primary = false) //annotation the String value ("stores" above) is an arbitrary client name,
public interface BookClient {

    @GetMapping(value =  "/books")
    List<Book> getBooks();

    @GetMapping(value = "/books/spec")
    String getBookSpec();

    @PostMapping(value = "/books/{bookId}",
            consumes = "application/json")
    Book update(@PathVariable("bookId") Long bookId);

    /*
    Spring Cloud lets you take full control of the feign client by declaring additional configuration (on top of the FeignClientsConfiguration)
    using @FeignClient.

    Example:
    @FeignClient(name = "stores", configuration = FooConfiguration.class)
    public interface StoreClient {
    //..
    }
    */
}
