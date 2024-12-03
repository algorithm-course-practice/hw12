package org.example;


import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

public class HomeWork {

    private static final boolean DIRTY_ALGORITHM = false;

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/problemset/problem/1324/C
     */
    @SneakyThrows
    public void frogSteps(InputStream in, OutputStream out) {
        Scanner scanner = new Scanner(in);
        StringBuilder result = new StringBuilder();

        int t = scanner.nextInt(); // Количество тестов
        scanner.nextLine(); // Пропускаем остаток строки после числа

        while (t-- > 0) {
            String s = scanner.nextLine();
            if (DIRTY_ALGORITHM) {
                result.append(minimumJump(s)).append("\n");
            }
            result.append(minimumJumpUsingTree(s)).append("\n");
        }

        out.write(result.toString().getBytes());
        out.flush();
    }

    private int minimumJump(String s) {
        int maxJump = 0; // Максимальное расстояние между безопасными платформами
        int lastSafe = -1; // Последняя безопасная позиция (начинаем перед строкой)

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                // Вычисляем расстояние до текущей платформы
                maxJump = Math.max(maxJump, i - lastSafe);
                lastSafe = i; // Обновляем последнюю безопасную позицию
            }
        }

        // Учёт расстояния от последней платформы до конца строки
        maxJump = Math.max(maxJump, s.length() - lastSafe);

        return maxJump;
    }

    private int minimumJumpUsingTree(String s) {
        Tree23<Integer> tree = new Tree23<>();

        // Добавляем фиктивные позиции
        tree.add(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                tree.add(i);
            }
        }
        tree.add(s.length());

        // Найти максимальный интервал
        return findMaxGap(tree);
    }

    private int findMaxGap(Tree23<Integer> tree) {
        List<Integer> keys = tree.getSortedKeys(); // Получаем все ключи в отсортированном порядке
        int maxGap = 0;

        for (int i = 1; i < keys.size(); i++) {
            maxGap = Math.max(maxGap, keys.get(i) - keys.get(i - 1));
        }

        return maxGap;
    }

}
