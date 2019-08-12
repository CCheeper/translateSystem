package com.example.demo;

import java.util.regex.Pattern;

public class CompareEnglish {
    public static float levenshtein(String str1, String str2) {

        String[] spString1 = str1.split(" ");
        String[] spString2 = str2.split(" ");
        int len1 = spString1.length;
        int len2 = spString2.length;
        int[][] dif = new int[len1 + 1][len2 + 1];

        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }


        String content = str2;

        String pattern = ".*"+str1+".*";

        boolean isMatch = Pattern.matches(pattern, content);
        if(isMatch){
            return 1;
        }

        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (spString1[i - 1].equals(spString2[j - 1])) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }

        float similarity = 1 - (float) dif[len1][len2] / Math.max(len1, len2);
        return similarity;
    }


    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }
}


