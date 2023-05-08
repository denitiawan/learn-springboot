package com.deni.app.module.user.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String username;
    String password;
    List<Role> roles;
    Integer blocked;
    Integer active;

    String createdBy;

    // field value on mongo must : ISODate("2022-12-23T16:46:07.812Z")
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;

    String updateBy;

    // field value on mongo must : ISODate("2022-12-23T16:46:07.812Z")
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;


}
