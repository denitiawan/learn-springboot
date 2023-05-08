package com.deni.app.module.user.service;

import com.deni.app.module.user.collection.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    public ResponseEntity save(User requestDTO);
    public ResponseEntity update(Integer id, User requestDTO);
    public ResponseEntity delete(Integer id);
    public ResponseEntity getAll();
    public ResponseEntity findById(Integer id);
    public ResponseEntity deleteAll();
}
