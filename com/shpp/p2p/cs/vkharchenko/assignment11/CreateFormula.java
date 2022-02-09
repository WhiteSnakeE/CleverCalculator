package com.shpp.p2p.cs.vkharchenko.assignment11;

import static com.shpp.p2p.cs.vkharchenko.assignment11.MakeAnswer.*;
import static com.shpp.p2p.cs.vkharchenko.assignment11.SymbolsPriority.*;

class CreateFormula {
    public int extraSymbols = 0;

    /**
     * Takes our formulas, searches for all unknown parameters and transposes them to HashMap <String, Double> variables
     *
     * @param expression -our formula
     */
    public void findVariables(String expression) {
        char key = '0';
        String value = "";
        /* We saves the characters here that will need to be removed later, for example b = 3   */
        boolean isVariable = true;
        char currentValue;
        for (int i = 0; i < expression.length(); i++) {
            currentValue = expression.charAt(i);

            //If equals is the last character, or there is no plus or minus or any number after equals,
            // then an error pops up
            if (expression.charAt(i) == '=' && (i == expression.length() - 1 ||
                    (!isDigit(expression.charAt(i + 1)) && !isSmallestPriority(String.valueOf(expression.charAt(i + 1)))))) {
                System.out.println("Error.Wrong variable value");
                System.exit(1);
            }

            //if we find a parameter, save its name and enable the checkbox so that the program would save its value
            if (currentValue == '=' && Character.isAlphabetic(expression.charAt(i - 1))) {
                isVariable = false;
                value = " ";
                key = expression.charAt(i - 1);
                extraSymbols += 2;
            }
            //if there are several characters after, then an error pops up
            if (expression.charAt(i) == '=' && isSymbol(expression.charAt(i + 1)) && !isDigit(expression.charAt(i + 2))) {
                System.out.println("Error.Wrong variable value");
                System.exit(1);
            }

            // if isVariable = false, then we write down its value until the line ends,
            // or we do not bury ourselves in a number
            else if (isDigit(currentValue) && !isVariable || (isSmallestPriority(String.valueOf(currentValue)) && !isVariable)) {
                value += currentValue;
                extraSymbols += 1;
                if (i == expression.length() - 1 || !isDigit(expression.charAt(i + 1))) {
                    variables.put("" + key, Double.valueOf(value));
                }
            }
        }
        /* call the method that substitutes our values into the formula */
    }

    /**
     * A method that substitutes values in a formula
     *
     * @param expression-our   formula
     * @param extraSymbols-symbols that need to delete
     * @return our formula but with values instead of variables
     */
    public String substituteTheValues(String expression, int extraSymbols) {
        StringBuilder formula = new StringBuilder();
        char currentValue;
        String variableValue;
        for (int i = 0; i < expression.length() - extraSymbols; i++) {
            currentValue = expression.charAt(i);

            /* if we find a parameter, then we replace the parameter with its value */
            if (variables.containsKey(String.valueOf(currentValue))) {
                variableValue = String.valueOf(variables.get(String.valueOf(currentValue)));
                formula.append(variableValue);
            } else {
                formula.append(currentValue);
            }
        }
        return formula.toString();
    }
}
