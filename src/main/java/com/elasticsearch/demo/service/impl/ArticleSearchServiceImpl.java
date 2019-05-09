package com.elasticsearch.demo.service.impl;
import com.elasticsearch.demo.commonJson.ExtLimit;
import com.elasticsearch.demo.entity.Example;
import com.elasticsearch.demo.service.ArticleSearchService;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleSearchServiceImpl implements ArticleSearchService
{
    private static final String QUERY_FILED = "name";
    private static final String QUERY_TAGS_FILED = "tags.keyword";
    private static final List<String> QUERY_TAGS_LIST = new ArrayList<String>(){
        {
            add("外出");
            add("加班");
        }};
    @Autowired ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public JSONObject findAllExampleByElasticsearch(Map<String, Object> requestMap, ExtLimit limit) throws Exception {
        String queryValue = requestMap.get(QUERY_FILED).toString();
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(new BoolQueryBuilder()
                .must(new TermsQueryBuilder(QUERY_TAGS_FILED, QUERY_TAGS_LIST))
                .must(new TermsQueryBuilder(QUERY_FILED, queryValue)))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .withHighlightFields(new HighlightBuilder.Field(QUERY_FILED))
                .withPageable(PageRequest.of(limit.getPageindex(), limit.getPagesize())).build();
        List<Example> examples = elasticsearchTemplate.queryForList(searchQuery, Example.class);
        System.out.printf("xxxxx");
        return null;
    }
}
