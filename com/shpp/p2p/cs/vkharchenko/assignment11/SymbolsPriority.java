package com.shpp.p2p.cs.vkharchenko.assignment11;

public class SymbolsPriority {
    /**
     * All our valid characters
     * @param symbol math symbol
     * @return true or false
     */
    public static boolean isSymbol(char symbol) {
        return symbol == '(' || symbol == ')' || symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '^';
    }
    /**
     * All our valid characters
     * @param symbol math bracket
     * @return true or false
     */
    public static boolean isBracket(char symbol) {
        return symbol == '(' || symbol == ')';
    }
    /**
     * All our valid characters
     * @param func math function
     * @return true or false
     */
    public static boolean isFunction(String func) {
        return func.equals("sin") || func.equals("cos") || func.equals("tan") || func.equals("atan")
                || func.equals("log10") || func.equals("log2") || func.equals("sqrt");
    }
    /**
     * All our valid characters
     * @param symbol math unary symbol
     * @return true or false
     */
    public static boolean isUnarySymbol(String symbol) {
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^");
    }

    /**
     * This is where we write the priority of characters
     * @param symbol - math symbol
     * @return priority of symbol
     */
    public static int priority(String symbol) {
        return switch (symbol) {
            case "^", "sin", "cos", "tan", "atan", "log10", "log2", "sqrt" -> 4;
            /* higher priority */
            case "*", "/" -> 3;
            /* lower priority */
            case "+", "-" -> 2;
            case "(" -> 1;
            case ")" -> -1;
            default -> 0;
        };

    }

    /**
     * lowest priority characters
     *
     * @param c-math symbol
     * @return
     */
    public static boolean isSmallestPriority(String c) {
        return c.equals("+") || c.equals("-");

    }

    /**
     * All our numbers and a point
     *
     * @param c-accepts numbers and a point
     * @return
     */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9' || c == '.' || c == ',';
    }
}

