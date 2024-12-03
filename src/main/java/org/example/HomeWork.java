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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {

            List<String> params = reader.lines().collect(Collectors.toList());
            int t = Integer.parseInt(params.get(0));

            for (int i = 1; i <= t; i++) {
                String line = params.get(i);
                int maxL = countMaxConsecutiveL(line.toCharArray());
                writer.write((maxL + 1) + System.lineSeparator());
            }

            writer.flush();
        }
    }

    private int countMaxConsecutiveL(char[] line) {
        int max = 0, currentCount = 0;

        for (char c : line) {
            if (c == 'L') {
                currentCount++;
                max = Math.max(max, currentCount);
            } else {
                currentCount = 0;
            }
        }

        return max;
    }
}