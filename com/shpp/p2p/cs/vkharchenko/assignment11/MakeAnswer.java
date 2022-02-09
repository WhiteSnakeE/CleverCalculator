package com.shpp.p2p.cs.vkharchenko.assignment11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class MakeAnswer {
    public static CreateFormula rightFormula = new CreateFormula();
    public static PolandNotation toPolandNotation = new PolandNotation();
    public static HashMap<String, Double> variables = new HashMap<>();

    /**
     * This method need to call our classes and calculate the formula
     * @param formula - expressiond that we need to calculate
     */
    public static void findVarAndMakeAnswer(StringBuilder formula) {
        System.out.println(formula);
        rightFormula.findVariables(formula.toString());
        formula = new StringBuilder(rightFormula.substituteTheValues(formula.toString(), rightFormula.extraSymbols));
        String[] makeAnswer = toPolandNotation.polandNotation(formula.toString()).toArray(new String[0]);

        /*now calculating the answer */
        System.out.println("Answer is: " + answer(makeAnswer));
    }
    /**
     * We make calculations and display the answer
     *
     * @param formula this method takes our formula and calculate this
     * @return the answer of our formula
     */
    private static String answer(String[] formula) {
        Stack<Double> stack = new Stack<>();
        System.out.println(Arrays.toString(formula));
        double value;
        for (String s : formula) {
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    value = stack.pop();
                    stack.push(stack.pop() - value);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    value = stack.pop();
                    stack.push(stack.pop() / value);
                    break;
                case "^":
                    value = stack.pop();
                    stack.push(Math.pow(stack.pop(), value));
                    break;
                case "sin":
                    stack.push(Math.sin(stack.pop()));
                    break;
                case "cos":
                    stack.push(Math.cos(stack.pop()));
                    break;
                case "tan":
                    stack.push(Math.tan(stack.pop()));
                    break;
                case "atan":
                    stack.push(Math.atan(stack.pop()));
                    break;
                case "log10":
                    stack.push(Math.log10(stack.pop()));
                    break;
                case "log2":
                    stack.push(Math.log(stack.pop()) / Math.log(2));
                    break;
                case "sqrt":
                    stack.push(Math.sqrt(stack.pop()));
                    break;
                default:
                    stack.push(Double.parseDouble(s));
            }
        }
        return String.valueOf(stack);
    }
}
