package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/problemset/problem/1324/C
     */
    @SneakyThrows
    public void frogSteps(InputStream in, OutputStream out) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter(out);

        int t = Integer.parseInt(reader.readLine());

        for (int i = 0; i < t; i++) {
            String s = reader.readLine();
            int n = s.length();
            writer.println(minJumpDistance(s, n));
        }

        writer.flush();
    }

    private int minJumpDistance(String s, int n) {
        int left = 1, right = n;
        int answer = n + 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canReachEnd(s, n, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean canReachEnd(String s, int n, int d) {
        boolean[] reachable = new boolean[n + 2];
        reachable[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == n + 1) {
                return true;
            }

            if (current < n) {
                char direction = s.charAt(current);
                if (direction == 'R') {
                    for (int jump = 1; jump <= d; jump++) {
                        int next = current + jump;
                        if (next <= n + 1 && !reachable[next]) {
                            reachable[next] = true;
                            queue.add(next);
                        }
                    }
                } else if (direction == 'L') {
                    for (int jump = 1; jump <= d; jump++) {
                        int next = current - jump;
                        if (next >= 0 && !reachable[next]) {
                            reachable[next] = true;
                            queue.add(next);
                        }
                    }
                }
            }
        }

        return false;
    }



}
