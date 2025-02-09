package com.india.railway.elasticrepo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Product;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    // Custom query methods
    List<Product> findByName(String name);
}
