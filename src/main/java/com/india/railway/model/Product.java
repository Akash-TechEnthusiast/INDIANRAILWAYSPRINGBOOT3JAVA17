package com.india.railway.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.suggest.Completion;

import lombok.Data;
import java.util.Date;

// @Setting(shards = 3, replicas = 2)

@Document(indexName = "newproduct")
public class Product {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Boolean)
    private Boolean available;

    @Field(type = FieldType.Date)
    private Date createdAt;

    @CompletionField
    private List<String> suggestions; // This field will store autocomplete suggestions

    // Constructors
    public Product() {
    }

    public Product(String id, String name, String description, Double price, Boolean available, Date createdAt,
            List<String> suggestions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.createdAt = createdAt;
        this.suggestions = suggestions;
    }

    // Getters and Setters
    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}