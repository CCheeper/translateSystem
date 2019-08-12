package com.example.demo;

import com.example.demo.entity.TranslateEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSort {
    public static void sort(List<TranslateEntity> list) {


        int listsize = list.size();
        TranslateEntity temp;
        for (int i = 0; i < listsize - 1; i++) {
            for (int j = 0; j < listsize - i - 1; j++) {
                if (Float.valueOf(list.get(i).getOther()) < Float.valueOf(list.get(i + 1).getOther())) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);

                }
            }
        }

    }
}
