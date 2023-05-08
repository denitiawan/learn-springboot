package com.deni.app.module.book.service;

import com.deni.app.module.book.collection.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface BookService {


    public ResponseEntity add(Book requestDTO);

    public ResponseEntity update(Integer id,Book requestDTO);
    public ResponseEntity delete(Integer id);


    public ResponseEntity getAll();


    public ResponseEntity findById(Integer id);


    public ResponseEntity deleteAll();



    public ResponseEntity findByRegexAuthor(String author);

}
