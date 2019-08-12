package com.example.demo;

import com.example.demo.entity.language;

import java.util.Random;

public class testdb {
    public static language getStringRandom() {

        Random random = new Random();
        String[] words = {"he", "she", "you", "it"};
        String[] celanguage ={"中文","英文"};
        String[] industry ={"金融","医疗","电子","服务"};
        String[] words2 = {"他", "她", "你", "它"};
        String[] w = {"is", "are"};
        String date="";
        int randomnum = 0;
        String[] things = {"pen", "pencil", "pencil-case", "ruler", "book", "schoolbag", "comic book ", "newspaper", "eraser", "crayon", "foot", "head", "face", "hair", "nose", "mouth", "eye", "ear", "arm", "hand", "leg", "cat", "dog", "pig", "elephant", "fish", "bird", "mouse", "bear", "kangaroo", "monkey", "red", "blue", "yellow", "boy", "girl", "mother", "father", "sister"};
        String[] things2 = {"钢笔", "铅笔", "铅笔盒", "尺子", "书", "书包", "漫画书 ", "报纸", "橡皮", "蜡笔", "脚", "头", "脸", "头发", "鼻子", "嘴巴", "眼睛", "耳朵", "手臂", "手", "腿", "猫", "狗", "猪", "大象", "鱼", "鸟", "老鼠", "熊", "袋鼠", "猴子", "红色的", "蓝色的", "黄色的", "男孩", "女孩", "母亲", "父亲", "妹妹"};
        int len1 = words.length;
        int len2 = things2.length;
        int num = random.nextInt(4);
        String chinese = words2[num] + "是";
        String english = words[num] +" "+ w[random.nextInt(2)]+" ";
        //参数length，表示生成几位随机数
        for (int i = 0; i < random.nextInt(5)+5; i++) {
            english+= things[random.nextInt(len2)]+" ";
            chinese+= things2[random.nextInt(len2)];
        }

        date = String.valueOf(random.nextInt(1000)+1000)+"-";
        randomnum =(random.nextInt(12)+1);
        if(randomnum /10 == 0 ){
            date += "0" +String.valueOf(randomnum)+"-" ;
        }else {
            date += String.valueOf(randomnum)+"-" ;
        }

        randomnum =(random.nextInt(27)+1);
        if(randomnum /10 == 0 ){
            date += "0" +String.valueOf(randomnum)+"T" ;
        }else {
            date += String.valueOf(randomnum)+"T" ;
        }
        randomnum =(random.nextInt(23)+1);
        if(randomnum /10 == 0 ){
            date += "0" +String.valueOf(randomnum)+":" ;
        }else {
            date += String.valueOf(randomnum)+":" ;
        }
        randomnum =(random.nextInt(59)+1);
        if(randomnum /10 == 0 ){
            date += "0" +String.valueOf(randomnum)+":" ;
        }else {
            date += String.valueOf(randomnum)+":" ;
        }
        randomnum =(random.nextInt(59)+1);
        if(randomnum /10 == 0 ){
            date += "0" +String.valueOf(randomnum);
        }else {
            date += String.valueOf(randomnum) ;
        }

        language language1 = new language(date,english,chinese,industry[random.nextInt(4)],celanguage[random.nextInt(2)]);
        return language1;
    }
}
