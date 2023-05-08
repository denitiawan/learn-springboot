package com.deni.app.module.user.controller;


import com.deni.app.common.controller.Response;
import com.deni.app.module.user.collection.User;
import com.deni.app.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/add")
    public ResponseEntity<Response> save(@RequestBody User request) {
        return userService.save(request);
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Response> update(@PathVariable Integer id,
                                           @RequestBody User request) {
        return userService.update(id, request);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id) {
        return userService.delete(id);
    }


    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Response> deleteAll() {
        return userService.deleteAll();
    }


    @GetMapping(value = "/list")
    public ResponseEntity<Response> getAll() {
        return userService.getAll();
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Response> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

}
