package com.example.demo;


import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

public class TranslateSearch {
    public static NativeSearchQueryBuilder search(String input, String language) {
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        if (language.equals("chinese")) {
            searchQuery.withQuery(boolQuery()
                    .should(QueryBuilders.matchQuery("sourcelanguage", input)))
                    //               .withQuery(QueryBuilders.matchPhraseQuery("destinationlanguage",input).slop(2))
                    .withSort(SortBuilders.scoreSort().order(SortOrder.DESC));

        } else if (language.equals("english")) {
            searchQuery.withQuery(boolQuery()
                    .should(QueryBuilders.matchQuery("destinationlanguage", input)))
                    .withSort(SortBuilders.scoreSort().order(SortOrder.DESC));

        }
        return searchQuery;
    }


    //多匹配高级查询
    public static NativeSearchQueryBuilder advancedquery(String language, String industry,String start ,String end) {
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();

        industry.replace(","," ");
        searchQuery.withQuery(
                boolQuery()
                        .must(QueryBuilders.rangeQuery("createtime").from(start).to(end))
                        .must(QueryBuilders.matchQuery("industry", industry))
                        .must(QueryBuilders.matchQuery("language", language))
        );
        return searchQuery;
    }
}
