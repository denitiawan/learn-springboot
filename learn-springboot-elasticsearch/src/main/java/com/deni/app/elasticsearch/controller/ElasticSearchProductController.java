package com.deni.app.elasticsearch.controller;


import com.deni.app.common.controller.Response;
import com.deni.app.elasticsearch.dto.ProductElasticDTO;
import com.deni.app.elasticsearch.service.ProductElasticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/elastic/product")
public class ElasticSearchProductController {


    @Autowired
    ProductElasticeService productElasticeService;


    @PostMapping(value = "/saveupdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> saveOrUpdate(@RequestBody ProductElasticDTO request) throws IOException {
        return productElasticeService.saveOrUpdate(request);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable String id) throws IOException {
        return productElasticeService.deleteById(id);
    }


    @GetMapping(value = "/search/{id}")
    public ResponseEntity<Response> searchById(@PathVariable String id) throws IOException {
        return productElasticeService.searchById(id);

    }

    @GetMapping(value = "/list")
    public ResponseEntity<Response> searchAll() throws IOException {
        return productElasticeService.searchAll();

    }
}
