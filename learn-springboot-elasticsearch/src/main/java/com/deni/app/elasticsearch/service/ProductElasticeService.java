package com.deni.app.elasticsearch.service;


import com.deni.app.elasticsearch.dto.ProductElasticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ProductElasticeService {

    public ResponseEntity saveOrUpdate(ProductElasticDTO requestDTO) throws IOException;


    public ResponseEntity deleteById(String id) throws IOException;

    public ResponseEntity searchById(String id) throws IOException;

    public ResponseEntity searchAll() throws IOException;

}
