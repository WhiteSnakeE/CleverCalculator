package com.shpp.p2p.cs.vkharchenko.assignment11;
import static com.shpp.p2p.cs.vkharchenko.assignment11.MakeAnswer.findVarAndMakeAnswer;

public class Assignment11Part1 {
    public static StringBuilder formula = new StringBuilder();
    /**
     * This method takes a formula, removes all spaces and replaces all commas with periods
     * * Then the findVariables method is called
     */
    public static void main(String[] args) {
        for (String arg : args) {
            formula.append(arg);
        }
        if (formula.toString().equals("")) {
            System.out.println("Error.Empty formula");
            System.exit(1);
        }
        formula = new StringBuilder(formula.toString().replaceAll("\\s+", ""));
        formula = new StringBuilder(formula.toString().replaceAll(",", "."));
        findVarAndMakeAnswer(formula);
    }



}