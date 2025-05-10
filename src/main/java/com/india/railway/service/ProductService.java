package com.india.railway.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import com.india.railway.model.Product_Elastic;
import com.india.railway.repository.elastic.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    // Save a new product
    public Product_Elastic saveProduct(Product_Elastic product) {

        return productRepository.save(product);
    }

    public Iterable<Product_Elastic> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product_Elastic> searchProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public List<String> getAutoSuggestionsOnName(String prefix) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .matchPhrasePrefix(m -> m
                                .field("name") // ✅ Changed from "suggestions" to "name"
                                .query(prefix)))
                .build();

        SearchHits<Product_Elastic> searchHits = elasticsearchOperations.search(query, Product_Elastic.class);

        List<String> suggestions = new ArrayList<>();
        searchHits.forEach(hit -> suggestions.add(hit.getContent().getName())); // ✅ Extract name field

        return suggestions;
    }

    public List<String> getAutoSuggestions(String prefix) {

        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .matchPhrasePrefix(m -> m
                                .field("suggestions")
                                .query(prefix)))
                .build();

        SearchHits<Product_Elastic> searchHits = elasticsearchOperations.search(query, Product_Elastic.class);

        List<String> suggestions = new ArrayList<>();
        searchHits.forEach(hit -> suggestions.addAll(hit.getContent().getSuggestions()));

        return suggestions;

    }

    /*
     * Explanation
     * 
     * NativeQuery → Used to build Elasticsearch queries in Spring Data
     * Elasticsearch.
     * Query → Represents the search query.
     * SearchHits → Stores the search results.
     * QueryBuilders → Helps in constructing Elasticsearch queries.
     * 
     * NativeQuery.builder() → Starts building a native Elasticsearch query.
     * .withQuery(q -> q.matchPhrasePrefix(...))
     * matchPhrasePrefix → Searches for words starting with the given prefix.
     * field("suggestions") → Searches in the suggestions field.
     * query(prefix) → Uses the user's input to find matching suggestions.
     * .build() → Finalizes and creates the query.
     * 
     * 
     * 
     * 
     * 
     * Creates a list to store suggestions.
     * Iterates through search results (searchHits).
     * Extracts the suggestions field from each product.
     * Adds them to the list.
     */

}
