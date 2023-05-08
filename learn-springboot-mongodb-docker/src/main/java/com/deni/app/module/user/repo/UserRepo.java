package com.deni.app.module.user.repo;

import com.deni.app.module.user.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends MongoRepository<User, Integer> {
    @Query("{username: ?0}")
    User findByUsername(String username);

}
