package com.deni.app.module.book.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer pages;
    private String author;
    private Double cost;
    String createdBy;

    // field value on mongo must : ISODate("2022-12-23T16:46:07.812Z")
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;

    String updateBy;

    // field value on mongo must : ISODate("2022-12-23T16:46:07.812Z")
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;


}
