package com.india.railway.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Product_Elastic;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product_Elastic, Long> {
    // Custom query methods
    List<Product_Elastic> findByName(String name);
}
