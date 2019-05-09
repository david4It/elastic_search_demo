package com.elasticsearch.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName = "example", type = "example_table")
public class Example {
    @Id
    @Field(store=true, index = false,type = FieldType.Integer)
    private Integer id;
    @Field(analyzer="ik_max_word",store=true,searchAnalyzer="ik_smart",type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private List<String> tags;
}