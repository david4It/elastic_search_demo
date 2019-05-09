package com.elasticsearch.demo.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "zwy_article",type = "zwy_article_table")
public class Article {
    private Integer itemid;
    private String content;
}
