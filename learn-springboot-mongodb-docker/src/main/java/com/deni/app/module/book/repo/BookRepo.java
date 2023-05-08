package com.deni.app.module.book.repo;


import com.deni.app.module.book.collection.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends MongoRepository<Book, Integer> {
    //--------------------------------custom query methods------------------------

    //SQL Equivalent : SELECT * FROM Book WHERE ID=?
    @Query("{ id :?0 }")
    Optional<Book> getBookById(Integer id);

    // SQL Equivalent : SELECT * FROM Book where pages<?
    //@Query("{ pages : { $gte: ?0 } }")       // SQL Equivalent : SELECT * FROM Book where pages>=?
    //@Query("{ pages : ?0 }")                 // SQL Equivalent : SELECT * FROM Book where pages=?
    @Query("{pages : {$lt: ?0}}")
    List<Book> getBooksByPages(Integer pages);


    // SQL Equivalent : SELECT * FROM Book where name = ?
    @Query("{name : ?0, author: ?1}")
    Book getBooksByNameAndAuthor(String name,String author);

    // SQL Equivalent : SELECT * FROM Book where author = ?
    @Query("{author : ?0}")
    List<Book> getBooksByAuthor(String author);


    // SQL Equivalent : SELECT * FROM Book where author = ? and cost=?
    //@Query("{$and :[{author: ?0},{cost: ?1}] }")
    @Query("{author: ?0, cost: ?1}")
    List<Book> getBooksByAuthorAndCost(String author, Double cost);


    //SQL Equivalent : select count(*) from Book where author=? or name=?
    @Query("{$or :[{author: ?0},{name: ?1}]}")
    List<Book> getBooksByAuthorOrName(String author, String name);

    //SQL Equivalent : select count(*) from Book where author=?
    @Query(value = "{author: ?0}", count = true)
    Integer getBooksCountByAuthor(String author);

    //Sorting
    //ASC
    //@Query(value = "{author=?0}", sort= "{name:-1}") //DESC
    @Query(value = "{author:?0}", sort = "{name:1}")
    List<Book> getBooksByAuthorSortByName(String author);

    //------------------- @Query with Projection ---------------------------------------
    // only data of name & author properties will be displayed
    //@Query(value= "{pages: ?0}", fields="{name:1, author:1, cost:1, pages:1}") // will display all properties data
    @Query(value = "{pages: ?0}", fields = "{name:1, author:1}")
    List<Book> getBookNameAndAuthorByPages(Integer pages);


    // SQL Equivalent : SELECT * FROM Book select * from Books where author=?
    @Query(value = "{author : ?0}")
    List<Book> getAllBooksByAuthor(String author);

    //------------------MongoDB Regular Expressions--------------------------------------
    @Query("{ author : { $regex : ?0 } }")
    List<Book> getBooksByAuthorRegEx(String author);

}
