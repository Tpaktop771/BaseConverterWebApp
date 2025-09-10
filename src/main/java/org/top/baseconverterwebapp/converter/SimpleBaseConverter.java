package org.top.baseconverterwebapp.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleBaseConverter implements BaseConverter {

    private final List<Integer> bases;

    public SimpleBaseConverter() {
        this.bases = new ArrayList<>(Arrays.asList(2, 8, 10, 16));
    }

    @Override
    public List<Integer> supportedBases() {
        return bases;
    }

    //подходит ли символ под с/с
    private boolean isDigitForBase(char ch, int base) {
        ch = Character.toUpperCase(ch); // a и A

        if (base <= 10) { // если до 10
            return ch >= '0' && ch < ('0' + base);
        } else { // если больше 10
            if (ch >= '0' && ch <= '9') {
                return (ch - '0') < base; // цифра входит в с/с
            } else if (ch >= 'A' && ch <= 'F') {
                return (10 + (ch - 'A')) < base; // буквы A-F
            } else {
                return false; // если не цифра и не буква
            }
        }
    }

    // проверка, что строка value подходит под основание
    private void validateValue(String value, int base) {
        if (value == null || value.isBlank()) {// если пусто или null, то это ошибка
            throw new InvalidNumberException(String.valueOf(value), base);
        }

        // прохожу по каждому символу строки
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);// текущий символ
            if (!isDigitForBase(ch, base)) { // если символ не подходит — ошибка
                throw new InvalidNumberException(value, base);
            }
        }
    }

    // переводим в десятичную
    private long toDecimal(String value, int fromBase) {
        try {
            return Long.parseLong(value, fromBase); // парсер
        } catch (NumberFormatException ex) {
            throw new InvalidNumberException(value, fromBase);
        }
    }

    // переводим из десятичной в любую
    private String fromDecimal(long value, int toBase) {
        if (value == 0) {
            return "0";
        }

        String digits = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder();
        long n = value;

        while (n > 0) {
            int r = (int) (n % toBase); // остаток — это цифра
            sb.append(digits.charAt(r)); // добавляем символ в строку
            n = n / toBase; // уменьшаем число
        }

        return sb.reverse().toString();
    }

    @Override
    public String convert(int fromBase, int toBase, String value) {
        if (!bases.contains(fromBase)) {
            throw new InvalidBaseException(fromBase);
        }

        if (!bases.contains(toBase)) {
            throw new InvalidBaseException(toBase);
        }

        // проверка, что строка правильная
        validateValue(value, fromBase);

        // переводим в десятичную
        long dec = toDecimal(value, fromBase);

        // переводим из десятичной в целевую систему
        return fromDecimal(dec, toBase);
    }
}
