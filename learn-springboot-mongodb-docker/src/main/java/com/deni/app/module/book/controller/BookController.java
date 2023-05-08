package com.deni.app.module.book.controller;


import com.deni.app.common.controller.Response;
import com.deni.app.module.book.collection.Book;
import com.deni.app.module.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping(value = "/add")
    public ResponseEntity<Response> save(@RequestBody Book request) {
        return bookService.add(request);
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody Book request) {
        return bookService.update(id, request);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }


    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Response> deleteAll() {
        return bookService.deleteAll();
    }


    @GetMapping(value = "/list")
    public ResponseEntity<Response> getAll() {
        return bookService.getAll();
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Response> findById(@PathVariable Integer id) {
        return bookService.findById(id);
    }


    @GetMapping(value = "/find/regex/author/{text}")
    public ResponseEntity<Response> delete(@PathVariable String text) {
        return bookService.findByRegexAuthor(text);
    }

}
