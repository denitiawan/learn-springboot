package com.deni.app.module.user.controller;


import com.deni.app.common.controller.Response;
import com.deni.app.module.user.dto.UserDTO;
import com.deni.app.module.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Api save data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @PostMapping(value = "/save")
    public ResponseEntity<Response> save(@RequestBody UserDTO request) {
        return userService.save(request);
    }


    @Operation(summary = "Api update data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id,
                                           @RequestBody UserDTO request) {
        return userService.update(id, request);
    }

    @Operation(summary = "Api delete data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @Operation(summary = "Api delete all data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<Response> deleteAll() {
        return userService.deleteAll();
    }

    @Operation(summary = "Api get all data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @GetMapping(value = "/list")
    public ResponseEntity<Response> getAll() {
        return userService.getAll();
    }


        @Operation(summary = "Api find data by id")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
                @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
        })
        @GetMapping("/find/{id}")
        public ResponseEntity<Response> findById(@PathVariable Long id) {
            return userService.findById(id);
        }

}
