package com.shpp.p2p.cs.vkharchenko.assignment11;

import java.util.ArrayDeque;
import java.util.Stack;

import static com.shpp.p2p.cs.vkharchenko.assignment11.SymbolsPriority.*;

public class PolandNotation {
    /**
     * using reverse polish notation we transform our formula
     *
     * @param expression -our formula that need to transform
     * @return array with parsed expression
     */
  /* ArrayDeque is a class in Java that implements the Deque and Queue interface.
    It is a special class that implements a two-way queue data structure,
    where it can insert and remove items from both ends.
    It supports the implementation of an auto-growing resizable array. */
    public ArrayDeque<String> polandNotation(String expression ) {
        ArrayDeque<String> numbersStack = new ArrayDeque<>();
        Stack<String> symbolsStack = new Stack<>();
        char symbol;
        String func = "";
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < expression .length(); i++) {
            symbol = expression .charAt(i);
            // If the first character is a sign, then add this sign to the number
            if (isDigit(symbol) || isSmallestPriority(String.valueOf(symbol)) && i == 0 && isDigit(expression .charAt(i + 1))) {
                temp.append(symbol);

                /* check next character for number or end of line */
                if (i == expression .length() - 1 || !isDigit(expression .charAt(i + 1))) {
                    numbersStack.add(temp.toString());
                    /* clean from values */
                    temp = new StringBuilder();
                }
               /*if we find two sign we put second sign to temp*/
            } else if (isSmallestPriority(String.valueOf(symbol)) && isUnarySymbol(String.valueOf(expression .charAt(i - 1)))
                    && isDigit(expression .charAt(i + 1))) {
                temp.append(symbol);

                /*if we find two sign and a bracket we put second sign to temp*/
            } else if (isSmallestPriority(String.valueOf(symbol)) && isBracket(expression .charAt(i - 1))
                    && isDigit(expression .charAt(i + 1)) && isUnarySymbol(String.valueOf(expression .charAt(i - 2)))) {
                temp.append(symbol);
                /*if we find  sign and a bracket we put second sign to temp*/
            } else if (isSmallestPriority(String.valueOf(symbol)) && expression .charAt(i+1)=='('
                    && isDigit(expression.charAt(i + 1))) {
                temp.append(symbol);

            } else if (isSmallestPriority(String.valueOf(symbol)) && expression .charAt(i-1)=='('
                    && Character.isLetter(expression .charAt(i - 2))) {
                temp.append(symbol);

                /*if we find the function*/
            } else if (Character.isLetter(symbol)) {
                while (expression .charAt(i) != '(') {
                    func += expression .charAt(i);
                    i++;
                }
                if (isFunction(func)) {
                    symbolsStack.push(func);
                    symbolsStack.push("(");
                    func = "";
                }

                /* look if the symbol is a matamatic sign, immediately look at the importance
            and do it according to the Polish notation algorithm */
            } else if (isSymbol(symbol)) {
                if (priority(String.valueOf(symbol)) == 1) {
                    symbolsStack.push(String.valueOf(symbol));
                } else if (priority(String.valueOf(symbol)) > 1) {
                    while (symbolsStack.size() != 0) {
                        if (priority(String.valueOf(symbolsStack.peek().charAt(0))) >= priority(String.valueOf(symbol))) {
                            numbersStack.add(symbolsStack.pop());
                        } else
                            break;
                    }
                    symbolsStack.push(String.valueOf(symbol));

                    /*if we find closed bracket*/
                } else if (priority(String.valueOf(symbol)) == -1) {
                    while (priority(String.valueOf(symbolsStack.peek().charAt(0))) != 1) {
                        numbersStack.add(symbolsStack.pop());
                    }
                    symbolsStack.pop();
                    if (symbolsStack.size() > 0 && isFunction(symbolsStack.peek())) {
                        numbersStack.add(symbolsStack.pop());
                    }
                }
            }
        }
        // If we have reached the end and we still have operands on the stack
        while (!symbolsStack.isEmpty()) {
            numbersStack.add(symbolsStack.pop());
        }
        return numbersStack;
    }

}