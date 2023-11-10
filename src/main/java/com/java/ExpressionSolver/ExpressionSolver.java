package com.java.ExpressionSolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpressionSolver implements AbstractSolver {

    private String expression;
    private final String numbers = "0123456789.";
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final String operations = "+-*/^";
    Map<String, Double> variables;

    public ExpressionSolver() {
        variables = new HashMap<String, Double>();
    }

    private String doubleToString(double num) {
        String result = Double.toString(num);
        if (result.endsWith(".0"))
            result = result.substring(0, result.length()-2);
        return result;
    }

    private double calculateOperation(double term1, double term2, char operation) {
        switch (operation) {
            case '+':
                return term1 + term2;
            case '-':
                return term1 - term2;
            case '*':
                return term1 * term2;
            case '/':
                return term1 / term2;
            case '^':
                return Math.pow(term1, term2);
        }
        return 0;
    }

    private String calculatePriority(String curr_expression, int operation_index) {
        StringBuilder builder = new StringBuilder(curr_expression);
        double result;
        String term1 = "", term2 = "";
        int i = operation_index - 1, start = 0, end = 0;
        while(i >= 0 && numbers.indexOf(curr_expression.charAt(i)) != -1) {
            term1 = curr_expression.charAt(i) + term1;
            start = i;
            i--;
        }
        i = operation_index + 1;
        while(i < curr_expression.length() && numbers.indexOf(curr_expression.charAt(i)) != -1 || i == operation_index + 1 && curr_expression.charAt(i) == '-') {
            term2 += curr_expression.charAt(i);
            i++;
            end = i;
        }
        result = calculateOperation(Double.parseDouble(term1), Double.parseDouble(term2), curr_expression.charAt(operation_index));
        builder.replace(start, end, doubleToString(result));
        return builder.toString();
    }

    private void calculatePart(int start_index, int end_index, boolean brackets) {
        String curr_part = expression.substring(brackets ? start_index+1 : start_index, end_index);
        StringBuilder builder = new StringBuilder(expression);
        while (curr_part.indexOf('^') != -1)
            curr_part = calculatePriority(curr_part, curr_part.indexOf('^'));
        while (curr_part.indexOf('*') != -1)
            curr_part = calculatePriority(curr_part, curr_part.indexOf('*'));
        while (curr_part.indexOf('/') != -1)
            curr_part = calculatePriority(curr_part, curr_part.indexOf('/'));
        double result = 0;
        String curr_term = "";
        char curr_operation = '+';
        for (int i = 0; i < curr_part.length(); i++) {
            if (numbers.indexOf(curr_part.charAt(i)) != -1) {
                curr_term += curr_part.charAt(i);
            }
            else if (operations.indexOf(curr_part.charAt(i)) != -1) {
                result = calculateOperation(result, Double.parseDouble(curr_term), curr_operation);
                curr_term = "";
                curr_operation = curr_part.charAt(i);
            }
        }
        result = calculateOperation(result, Double.parseDouble(curr_term), curr_operation);
        builder.replace(start_index, end_index+1, doubleToString(result));
        expression = builder.toString();
    }

    private int correspondingLeftBracket(int right_bracket_index) {
        return expression.substring(0, right_bracket_index).lastIndexOf('(', right_bracket_index);
    }

    @Override
    public void solveExpression() {
         System.out.println("Доступные специальные символы: ( ) + - * / ^ !");
         //System.out.println("Переменные должны состоять из латинских букв");
         //System.out.println("Доступные функции: sin, cos, tan, cotan, abs");
         System.out.println("Введите выражение:");
         Scanner scanner = new Scanner(System.in);
         expression = scanner.nextLine();
         expression = expression.replaceAll("\\s", "");
         int right_bracket_index = expression.indexOf(')');
         while(right_bracket_index != -1) {
             calculatePart(correspondingLeftBracket(right_bracket_index), right_bracket_index, true);
             right_bracket_index = expression.indexOf(')', right_bracket_index);
         }
        calculatePart(0, expression.length(), false);
         System.out.println(expression);
    }

}