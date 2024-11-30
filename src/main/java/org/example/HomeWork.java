package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/problemset/problem/1324/C
     */
    @SneakyThrows
    public void frogSteps(InputStream in, OutputStream out) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))){
            List<String> params = reader.lines().collect(Collectors.toList());
            List<String> input =reader.lines().collect(Collectors.toList());
            int t = Integer.parseInt(params.get(0));
            for (int i = 1; i <= t; i++) {
                char[] line = params.get(i).toCharArray();
                boolean containL = params.get(i).contains("L");
                // находим максимальную непрерывную последовательность L чтобы лягушка могла ее перепрыгнуть
                int maxL = countL(line, containL);
                writer.write(maxL + 1 + "\n");
            }
            writer.flush();
        }
    }
    private int countL(char[] line, boolean containL){
        int max = 0;
        int count = containL ? 1 : 0;
        int len = line.length;
        for (int i = 1; i < len; i++) {
            if (line[i -1] == 'L'){
                if (line[i - 1] == line[i]){
                    count++;
                }
                else {
                    max = Math.max(max, count);
                    count = containL ? 1 : 0;
                }
            }
        }
        return Math.max(max, count);
    }
}
