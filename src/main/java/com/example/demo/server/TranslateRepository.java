package com.example.demo.server;

import com.example.demo.entity.TranslateEntity;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TranslateRepository extends ElasticsearchRepository<TranslateEntity, Long>  {
}
