package com.example.demo.server;

import com.example.demo.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, Long> {
}
