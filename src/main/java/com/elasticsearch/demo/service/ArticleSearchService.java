package com.elasticsearch.demo.service;

import com.elasticsearch.demo.commonJson.ExtLimit;
import org.json.JSONObject;

import java.util.Map;

public interface ArticleSearchService {
    JSONObject findAllExampleByElasticsearch(Map<String,Object> requestMap, ExtLimit limit) throws Exception;
}
