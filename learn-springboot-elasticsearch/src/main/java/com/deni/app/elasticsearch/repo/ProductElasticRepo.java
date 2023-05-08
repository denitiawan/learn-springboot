package com.deni.app.elasticsearch.repo;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.deni.app.elasticsearch.document.ProductElasticDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.deni.app.elasticsearch.ElasticConstant.ELASTIC_INDEX_PRODUCT;


@Repository
public class ProductElasticRepo {


    @Autowired
    private ElasticsearchClient elasticsearchClient;


    /**
     * CRAETE OR UPDATE Document
     */
    public IndexResponse createOrUpdateDocument(ProductElasticDocument product) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(ELASTIC_INDEX_PRODUCT)
                .id(product.getId())
                .document(product)
        );
        return response;
    }

    /**
     * Get Document By Id
     */
    public ProductElasticDocument searchDocumentById(String productId) throws IOException {
        GetResponse<ProductElasticDocument> response = elasticsearchClient.get(g -> g
                        .index(ELASTIC_INDEX_PRODUCT)
                        .id(productId),
                ProductElasticDocument.class
        );

        if (response.found()) {
            return response.source();
        }

        return null;
    }

    /**
     * Delete Document by Id
     */
    public DeleteResponse deleteDocumentById(String productId) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(ELASTIC_INDEX_PRODUCT).id(productId));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        return deleteResponse;

    }

    /**
     * Search All docuements
     */
    public List<ProductElasticDocument> searchAllDocuments() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s.index(ELASTIC_INDEX_PRODUCT));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, ProductElasticDocument.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ProductElasticDocument> products = new ArrayList<>();
        for (Hit object : hits) {
            products.add((ProductElasticDocument) object.source());
        }
        return products;
    }
}
