package com.epam.engx.cleancode.naming.task6;

public class Formatter {

    private static final String PLUS = "+";
    private static final String PIPE = "|";
    private static final String MINUS = "-";
    private static final String UNDERSCORE = " _ ";


    public String formatKeyValue(String key, String value) {
        String keyValue = key + UNDERSCORE + value;
        String minuses = repeatSymbol(MINUS, keyValue.length());
        return PLUS +  minuses + PLUS + "\n"
                + PIPE + keyValue + PIPE + "\n"
                + PLUS + minuses + PLUS + "\n";
    }

    private String repeatSymbol(String symbol, int times) {
        String result = "";
        for (int i = 0; i < times; i++)
            result += symbol;
        return result;
    }
}
