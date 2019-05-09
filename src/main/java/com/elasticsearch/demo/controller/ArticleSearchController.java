package com.elasticsearch.demo.controller;

import com.elasticsearch.demo.commonJson.ComJsonUtil;
import com.elasticsearch.demo.commonJson.ExtLimit;
import com.elasticsearch.demo.commonJson.FinalJson;
import com.elasticsearch.demo.service.ArticleSearchService;
import com.elasticsearch.demo.util.JsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ArticleSearchController {
    @Autowired ArticleSearchService service;

    @PostMapping("/example")
    public ComJsonUtil searchExample(@RequestBody ComJsonUtil comJsonUtil) throws Exception{
        ComJsonUtil jUBean = JsonMapper.string2Obj(JsonMapper.obj2String(comJsonUtil),new TypeReference<ComJsonUtil>() {});
        Map<String,Object> requestMap = (Map)jUBean.getData();
        ExtLimit limit = jUBean.getExtlimit();
        if (requestMap == null || requestMap.size() == 0) {
            comJsonUtil.getInfo().setMessage("查询数据为空");
            comJsonUtil.getInfo().setStatus(FinalJson.STATUS_NOTACCEPTABLE);
        } else {
            JSONObject jsonObject = service.findAllExampleByElasticsearch(requestMap, limit);
        }
        return comJsonUtil;
    }
}
