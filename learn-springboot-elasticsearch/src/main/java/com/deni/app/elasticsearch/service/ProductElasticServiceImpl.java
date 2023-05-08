package com.deni.app.elasticsearch.service;

import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.deni.app.common.constants.Messages;
import com.deni.app.common.controller.ResponseHandler;
import com.deni.app.common.validator.Validator;
import com.deni.app.elasticsearch.document.ProductElasticDocument;
import com.deni.app.elasticsearch.dto.ProductElasticDTO;
import com.deni.app.elasticsearch.repo.ProductElasticRepo;
import com.deni.app.elasticsearch.service.validator.ProductElasticValidator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.deni.app.elasticsearch.ElasticConstant.*;


@Service
@NoArgsConstructor
public class ProductElasticServiceImpl implements ProductElasticeService {

    @Autowired
    ProductElasticRepo repo;

    @Autowired
    ProductElasticValidator elasticValidator;

    @Override
    public ResponseEntity saveOrUpdate(ProductElasticDTO requestDTO) throws IOException {
        Validator validator = elasticValidator.requestValidator(requestDTO);
        if (validator.isSuccess()) {
            ProductElasticDocument entity = ProductElasticDocument.builder()
                    .id(requestDTO.getId())
                    .name(requestDTO.getName())
                    .description(requestDTO.getDescription())
                    .price(requestDTO.getPrice())
                    .build();

            IndexResponse response = repo.createOrUpdateDocument(entity);

            String message = "";
            if (response.result().name().equals(ELASTIC_DOCUMENT_CREATED)) {
                message = Messages.MSG_SAVE_SUCCESS;
            } else if (response.result().name().equals(ELASTIC_DOCUMENT_UPDATED)) {
                message = Messages.MSG_UPDATE_SUCCESS;
            }
            return ResponseHandler.createHttpResponse(
                    message,
                    entity,
                    HttpStatus.OK);
        } else {
            return ResponseHandler.createHttpResponse(
                    validator.getMessage(),
                    requestDTO,
                    HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public ResponseEntity deleteById(String id) throws IOException {
        DeleteResponse deleteResponse = repo.deleteDocumentById(id);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals(ELASTIC_DOCUMENT_NOTFOUND)) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DELETE_SUCCESS,
                    deleteResponse.id(),
                    HttpStatus.OK);
        } else {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_NOT_FOUND,
                    id,
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity searchById(String id) throws IOException {
        ProductElasticDocument document = repo.searchDocumentById(id);
        if (document != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    document,
                    HttpStatus.OK);

        } else {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_NOT_FOUND,
                    "",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity searchAll() throws IOException {
        List<ProductElasticDocument> documents = repo.searchAllDocuments();
        if (documents != null && documents.size() > 0) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    documents,
                    HttpStatus.OK);
        } else {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_NOT_FOUND,
                    "",
                    HttpStatus.NOT_FOUND);
        }
    }
}
