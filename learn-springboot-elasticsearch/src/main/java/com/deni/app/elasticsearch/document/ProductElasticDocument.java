package com.deni.app.elasticsearch.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import static com.deni.app.elasticsearch.ElasticConstant.ELASTIC_INDEX_PRODUCT;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = ELASTIC_INDEX_PRODUCT)
public class ProductElasticDocument {
    @Id
    String id;

    @Field(type = FieldType.Text, name = "name")
    String name;

    @Field(type = FieldType.Text, name = "description")
    String description;

    @Field(type = FieldType.Double, name = "price")
    double price;

}
