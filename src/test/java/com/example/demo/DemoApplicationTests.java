package com.example.demo;

import com.example.demo.entity.TranslateEntity;
import com.example.demo.server.TranslateRepository;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    TranslateRepository translateRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    List<TranslateEntity> list = new ArrayList<>();
    boolean flag = true;

    @Test
    public void contextLoads() {
        int count = 0;
        while (flag) {
            MockConfig mockConfig = new MockConfig()

                    .subConfig(TranslateEntity.class, "id")
                    .longRange(0, Long.MAX_VALUE)
                    .subConfig(TranslateEntity.class, "language")
                    .numberRegex("")
                    .subConfig(TranslateEntity.class, "sourcelanguage")
                    .numberRegex("").sizeRange(10, 20)
                    .subConfig(TranslateEntity.class, "destinationlanguage")
                    .numberRegex("").sizeRange(10, 20)
                    .subConfig(TranslateEntity.class, "creator")
                    .numberRegex("^[A-Za-z]").sizeRange(4, 5)
                    .subConfig(TranslateEntity.class, "other")
                    .stringRegex("")
                    .subConfig(TranslateEntity.class, "notes")
                    .numberRegex("^[A-Za-z]+$").sizeRange(10, 15)
                    .subConfig(TranslateEntity.class, "industry")
                    .numberRegex("")
                    .subConfig(TranslateEntity.class, "createtime")
                    .numberRegex("")
                    .subConfig(TranslateEntity.class, "edittime")
                    .numberRegex("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}")
                    .subConfig(TranslateEntity.class, "editor")
                    .numberRegex("^[A-Za-z]+$").sizeRange(4, 5)
                    .subConfig(TranslateEntity.class, "checker")
                    .numberRegex("^[A-Za-z]+$").sizeRange(4, 5)
                    .globalConfig();
            Random random = new Random();
            TranslateEntity translateEntity = JMockData.mock(TranslateEntity.class, mockConfig);
            translateEntity.setSourcelanguage(testdb.getStringRandom().getEnglish());
            translateEntity.setDestinationlanguage(testdb.getStringRandom().getChinese());
            translateEntity.setIndustry(testdb.getStringRandom().getIndustry());
            translateEntity.setLanguage(testdb.getStringRandom().getCelanguage());
            translateEntity.setCreatetime(testdb.getStringRandom().getDate());
            list.add(translateEntity);
            //  System.out.println(translateEntity.toString());
            count++;
            if (count % 1000 == 0) {
                System.out.println(count);
            }
            if (count == 10000) {
                flag = false;
                translateRepository.saveAll(list);

            }
        }


    }

    @Test
    public void lll() {

        elasticsearchTemplate.deleteIndex(TranslateEntity.class);

    }
    @Test
    public void search() {
//        ArrayList<TranslateEntity> list = new ArrayList<>();
//    //    Page<TranslateEntity> pages = translateRepository.search(TranslateSearch.advancedquery("asd", "asd").withPageable(PageRequest.of(0, 20)).build());
//        for (TranslateEntity pagel : pages) {
//            list.add(pagel);
//
//        }
//        System.out.println(list.toString());
   }
}
