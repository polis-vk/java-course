package ru.mail.polis.classes.livecoding;

public class StringTasks {

    /**
     * Убрать все лишние символы из строки и вернуть получившееся число.
     * Разрешенные символы: цифры, '-', '.', 'e'
     * Если '.' и 'e' больше чем 1, возвращаем null
     * Правила на '-' является валидность числа. --3 не валидно. -3e-1 валдино
     * Любой класс-обертка над примитивами наследуется от Number
     * Можно использовать функции Double.valueOf() и другие такие же.
     *
     * Работайте со строкой, НЕ надо ее переводить в массив байт (это можно использовать только для цикла)
     * У класса Character есть полезные методы, например Character.isDigit()
     */
    public static Number valueOf(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        int dotCount = str.length() - str.replace(".", "").length();
        int eCount = str.length() - str.replace("e", "").length();
        int minusCount = str.length() - str.replace("-", "").length();
        if (dotCount > 1 || eCount > 1 || minusCount > 2) {
            return null;
        }

        String numberString = optimizationString(str);

        if (str.contains("--") || str.contains("-e") || str.contains(".e") || str.contains("e.")
                || str.endsWith("-") || str.endsWith("e")) {
            return null;
        }

        if (dotCount + eCount > 0) {
            return Double.parseDouble(numberString);
        }
        long longNumber = Long.parseLong(numberString);
        if (longNumber > Integer.MAX_VALUE || longNumber < Integer.MIN_VALUE) {
            return longNumber;
        }
        return (int) longNumber;
    }

    private static String optimizationString(String str) {
        StringBuilder numberStringBuilder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch == '.' || ch == 'e' || ch == '-' || Character.isDigit(ch)) {
                numberStringBuilder.append(ch);
            }
        }
        return numberStringBuilder.toString();
    }

}
