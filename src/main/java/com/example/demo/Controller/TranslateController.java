package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.*;
import com.example.demo.entity.TranslateEntity;
import com.example.demo.server.TranslateRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class TranslateController {
    @Autowired
    TranslateRepository translateRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //http://127.0.0.1:8080/create?sourcelanguage=sssss&destinationlanguage=lolo&creator=creeper&notes=1&industry=%E9%87%91%E8%9E%8D&language=%E4%B8%AD%E6%96%87
    @GetMapping("/create")
    public String create(String sourcelanguage, String destinationlanguage, String creator, String notes, String industry, String language) {
        Random random = new Random();

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
        String date = dateFormat.format(new Date());
        String createtime = timeFormat.format(new Date());

        TranslateEntity translateEntity = new TranslateEntity(random.nextLong(), sourcelanguage, destinationlanguage, creator, "", notes, industry, date + "T" + createtime, "", "", "", language);
        translateRepository.save(translateEntity);
        return "success";
    }


    @GetMapping("/sreach")
    public List<TranslateEntity> sreach(String input, int page, int size, String language) {

        CompareChinese chinese = new CompareChinese();
        CompareEnglish english = new CompareEnglish();


        Page<TranslateEntity> pages = translateRepository.search(TranslateSearch.search(input, language).withPageable(PageRequest.of(page, size)).build());
        ArrayList<TranslateEntity> list = new ArrayList<>();


        for (TranslateEntity pagel : pages) {
            float rangeSource = english.levenshtein(input, pagel.getSourcelanguage());
            float rangeDestination = chinese.levenshtein(input, pagel.getDestinationlanguage());
            if (rangeSource >= 0.5) {

                pagel.setOther(String.valueOf(rangeSource));
                list.add(pagel);

            } else if (rangeDestination >= 0.5) {

                pagel.setOther(String.valueOf(rangeDestination));
                list.add(pagel);

            }
        }
        ListSort.sort(list);
        return list;
    }


    @RequestMapping(value = "/getfile", method = RequestMethod.POST)

    public JSONObject file(String language, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        System.out.println(file);
        String pathString = null;
        String path="UploadFilePathConfig";
        if (file != null) {
            pathString = path + "lll.txt";
        }

        try {
            File files = new File(pathString);
            //打印查看上传路径
            System.out.println(pathString);
            if (!files.getParentFile().exists()) {
                files.getParentFile().mkdirs();
            }
            file.transferTo(files);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<String> input = FilePoccess.toArrayByFileReader1(path + "lll.txt");
        List<String> out = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            SearchQuery searchQuery = TranslateSearch.search(input.get(i), "chinese").build();
            List<TranslateEntity> list1 = elasticsearchTemplate.queryForList(searchQuery, TranslateEntity.class);
            ArrayList<TranslateEntity> list = new ArrayList<>(list1);
            ArrayList<TranslateEntity> newlist = new ArrayList<>();


            if (list1.size() != 0) {
                for (int j = 0; j < list.size(); j++) {
                    TranslateEntity translateEntity = list1.get(j);
                    if (language.equals("chinese")) {
                        translateEntity.setOther(String.valueOf(CompareEnglish.levenshtein(input.get(i), list.get(j).getSourcelanguage())));
                    } else if (language.equals("english")) {
                        translateEntity.setOther(String.valueOf(CompareChinese.levenshtein(input.get(i), list.get(j).getDestinationlanguage())));
                    }

                    newlist.add(translateEntity);

                }

                ListSort.sort(newlist);
                System.out.println(newlist.toString());
                if (language.equals("chinese")) {
                    out.add(newlist.get(0).getDestinationlanguage() + "(" + newlist.get(0).getOther() + ")");
                } else if (language.equals("english")) {
                    out.add(newlist.get(0).getSourcelanguage() + "(" + newlist.get(0).getOther() + ")");
                }
            } else {
                out.add("无匹配内容");
            }
        }

        File fout = new File(path+"out.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (int i1 = 0; i1 < out.size(); i1++) {
            bw.write(out.get(i1));
            bw.newLine();
        }
        bw.close();
        JSONObject json =new JSONObject();
        json.put("code",0);
        json.put("msg","");
        return json;
    }



    @GetMapping("/advancedquery")
    public List<TranslateEntity> advancedquery(String date,String language,String industry) {
        System.out.println(date);
        String[] datearray = date.split(" - ");

        String start = datearray[0].replace(" ","T");
        String end = datearray[1].replace(" ","T");
        System.out.println(start);
        System.out.println(end);
        SearchQuery searchQuery = TranslateSearch.advancedquery(language,industry,start, end).build();
        List<TranslateEntity> list = elasticsearchTemplate.queryForList(searchQuery, TranslateEntity.class);
        return list;
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        translateRepository.deleteById(id);
        return "success";
    }
}