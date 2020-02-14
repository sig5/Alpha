package com.example.alpha;

public class string_functions {
    public static String[] split_Score(String data) {
        String[] str = data.split("-");
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
    public static String search(String txt, String[] pat) {
        for (String x : pat) {
            int M = x.length();
            int N = txt.length();
            for (int i = 0; i <= N - M; i++) {
                int j;
                for (j = 0; j < M; j++)
                    if (txt.charAt(i + j) != x.charAt(j))
                        break;

                if (j == M)
                    return x;
            }
        }
        return null;
    }
}
