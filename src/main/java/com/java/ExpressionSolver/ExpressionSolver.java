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

    private void calculatePriority(int operation_index) {
        StringBuilder builder = new StringBuilder(expression);
        double result;
        String term1 = "", term2 = "";
        int i = operation_index - 1, start = 0, end = 0;
        while(i >= 0 && numbers.indexOf(expression.charAt(i)) != -1) {
            term1 = expression.charAt(i) + term1;
            start = i;
            i--;
        }
        i = operation_index + 1;
        while(i < expression.length() && numbers.indexOf(expression.charAt(i)) != -1) {
            term2 += expression.charAt(i);
            i++;
            end = i;
        }
        result = calculateOperation(Double.parseDouble(term1), Double.parseDouble(term2), expression.charAt(operation_index));
        builder.replace(start, end, Double.toString(result));
        expression = builder.toString();
    }

    private double calculatePart(int start_index, int end_index) {
        while (expression.indexOf('*') != -1)
            calculatePriority(expression.indexOf('*'));
        while (expression.indexOf('/') != -1)
            calculatePriority(expression.indexOf('/'));
        while (expression.indexOf('^') != -1)
            calculatePriority(expression.indexOf('^'));
        double result = 0;
        String curr_term = "";
        char curr_operation = '+';
        end_index = expression.length();
        for (int i = start_index; i < end_index; i++) {
            if (numbers.indexOf(expression.charAt(i)) != -1) {
                curr_term += expression.charAt(i);
            }
            else if (operations.indexOf(expression.charAt(i)) != -1) {
                result = calculateOperation(result, Double.parseDouble(curr_term), curr_operation);
                curr_term = "";
                curr_operation = expression.charAt(i);
            }
        }
        return calculateOperation(result, Double.parseDouble(curr_term), curr_operation);
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
         System.out.println(calculatePart(0, expression.length()));
    }

}