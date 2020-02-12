package com.example.alpha;

public class string_functions {
    public static String[] split_Score(String data) {
        String[] str = data.split(" v ");
        return str;
    }

    public static int isStarted(String[] teams) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < teams[i].length(); j++) {
                if (teams[i].charAt(j) == '/' || teams[i].charAt(j) == '*')
                    return 1;
            }
        }
        return 0;
    }
}
