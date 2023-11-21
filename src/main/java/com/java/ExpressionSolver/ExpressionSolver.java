package com.java.ExpressionSolver;
import java.util.Arrays;
import java.util.Scanner;

public class ExpressionSolver implements AbstractSolver {
    private String expression;
    private final String numbers = "0123456789.";
    private final String operations = "+-*/^";
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final String[] functions = {"sin", "cos", "tan", "cotan", "abs"};
    public double result = 0;

    public ExpressionSolver() {}

    public ExpressionSolver(ExpressionSolver obj) {
        expression = obj.expression;
        result = obj.result;
    }

    private String doubleToString(double num) {
        String result = Double.toString(num);
        if (result.endsWith(".0"))
            result = result.substring(0, result.length()-2);
        return result;
    }

    private double calculateOperation(double term1, double term2, char operation) {
        return switch (operation) {
            case '+' -> term1 + term2;
            case '-' -> term1 - term2;
            case '*' -> term1 * term2;
            case '/' -> term1 / term2;
            case '^' -> Math.pow(term1, term2);
            default -> 0;
        };
    }

    private int searchForFunction(String curr_part) {
        int min_index = curr_part.length(), curr_index;
        for (String function : functions) {
            curr_index = curr_part.indexOf(function);
            if (curr_index != -1 && curr_index < min_index)
                min_index = curr_index;
        }
        if (min_index == curr_part.length())
            return -1;
        else
            return min_index;
    }

    private String calculateFunction(String curr_part, int func_index) {
        StringBuilder builder = new StringBuilder(curr_part);
        int left_bracket_index = curr_part.indexOf('(', func_index);
        String func_name = curr_part.substring(func_index, left_bracket_index);
        int right_bracket_index = correspondingRightBracket(curr_part, left_bracket_index);
        double input_value = calculatePart(left_bracket_index, right_bracket_index, true);
        double result = switch (func_name) {
            case "sin" -> Math.sin(input_value);
            case "cos" -> Math.cos(input_value);
            case "tan" -> Math.tan(input_value);
            case "cotan" -> 1.0 / Math.tan(input_value);
            case "abs" -> Math.abs(input_value);
            default -> 0;
        };
        builder.replace(func_index, right_bracket_index+1, doubleToString(result));
        return builder.toString();
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

    private double calculatePart(int start_index, int end_index, boolean brackets) {
        String curr_part = expression.substring(brackets ? start_index+1 : start_index, end_index);
        StringBuilder builder = new StringBuilder(expression);
        double result = 0;
        String curr_term = "";
        char curr_operation;

        int func_index = searchForFunction(curr_part);
        while (func_index != -1) {
            curr_part = calculateFunction(curr_part, func_index);
            func_index = searchForFunction(curr_part);
        }

        while (curr_part.indexOf('^') != -1)
            curr_part = calculatePriority(curr_part, curr_part.indexOf('^'));
        while (curr_part.indexOf('*') != -1)
            curr_part = calculatePriority(curr_part, curr_part.indexOf('*'));
        while (curr_part.indexOf('/') != -1)
            curr_part = calculatePriority(curr_part, curr_part.indexOf('/'));

        if (curr_part.charAt(0) == '-')
            curr_operation = '-';
        else
            curr_operation = '+';

        for (int i = curr_part.charAt(0) == '-' ? 1 : 0; i < curr_part.length(); i++) {
            if (numbers.indexOf(curr_part.charAt(i)) != -1)
                curr_term += curr_part.charAt(i);
            else if (operations.indexOf(curr_part.charAt(i)) != -1) {
                result = calculateOperation(result, Double.parseDouble(curr_term), curr_operation);
                curr_term = "";
                curr_operation = curr_part.charAt(i);
            }
        }

        result = calculateOperation(result, Double.parseDouble(curr_term), curr_operation);
        builder.replace(start_index, end_index+1, doubleToString(result));
        expression = builder.toString();
        return result;
    }

    private int correspondingLeftBracket(int right_bracket_index) {
        return expression.substring(0, right_bracket_index).lastIndexOf('(', right_bracket_index);
    }

    private int correspondingRightBracket(String curr_part, int left_bracket_index) {
        int brackets_balance = 1, curr_index = left_bracket_index, next_left_bracket, next_right_bracket;
        while (curr_index < curr_part.length()) {
            next_left_bracket = curr_part.indexOf('(', curr_index + 1);
            next_right_bracket = curr_part.indexOf(')', curr_index + 1);
            curr_index = Math.min(next_left_bracket != -1 ? next_left_bracket : curr_part.length(), next_right_bracket != -1 ? next_right_bracket : curr_part.length());
            if (curr_part.charAt(curr_index) == '(')
                brackets_balance++;
            else
                brackets_balance--;
            if (brackets_balance == 0)
                return curr_index;
        }
        return -1;
    }

    private int locateFunction(int right_bracket_index) {
        int left_bracket_index = correspondingLeftBracket(right_bracket_index)-1;
        while (left_bracket_index >= 0 && alphabet.indexOf(expression.charAt(left_bracket_index)) != -1)
            left_bracket_index--;
        if (expression.charAt(left_bracket_index+1) == '(')
            return -1;
        else
            return left_bracket_index + 1;
    }

    private String searchForVariable(String curr_part) {
        String var = "";
        for (int i = 0; i < curr_part.length(); i++)
            if (alphabet.indexOf(curr_part.charAt(i)) != -1)
                var += curr_part.charAt(i);
            else if (!var.equals("") && !Arrays.asList(functions).contains(var))
                return var;
            else
                var = "";
        return var;
    }

    private void defineAllVariables() {
        Scanner scanner = new Scanner(System.in);
        String var = searchForVariable(expression);
        double value;
        while (!var.equals("")) {
            System.out.println("Введите значение переменной " + var + ": ");
            value = scanner.nextDouble();
            expression = expression.replaceAll(var, doubleToString(value));
            var = searchForVariable(expression);
        }
    }

    private void calculateFull() {
        defineAllVariables();
        int right_bracket_index = expression.indexOf(')'), function_index;
        while(right_bracket_index != -1) {
            function_index = locateFunction(right_bracket_index);
            if (function_index != -1)
                expression = calculateFunction(expression, function_index);
            else
                calculatePart(correspondingLeftBracket(right_bracket_index), right_bracket_index, true);
            right_bracket_index = expression.indexOf(')');
        }
        calculatePart(0, expression.length(), false);
        System.out.println(expression);
        result = Double.parseDouble(expression);
    }

    private boolean correctSymbolsNearBracket(int bracket_index) {
        if (bracket_index == -1
                || expression.charAt(bracket_index) == '(' && bracket_index == 0
                || expression.charAt(bracket_index) == ')' && bracket_index == expression.length()-1)
            return true;
        String left_part;
        if (expression.charAt(bracket_index) == '(') {
            left_part = expression.substring(0, bracket_index);
            for (String function : functions)
                if (left_part.endsWith(function))
                    return true;
            return (operations.indexOf(expression.charAt(bracket_index - 1)) != -1 || expression.charAt(bracket_index - 1) == '(');
        }
        else {
            return (operations.indexOf(expression.charAt(bracket_index + 1)) != -1 || expression.charAt(bracket_index + 1) == ')');
        }
    }

    private boolean correctVariable(String var) {
        int curr_index = expression.indexOf(var);
        while (curr_index != -1) {
            if (curr_index > 0
                    && operations.indexOf(expression.charAt(curr_index - 1)) == -1
                    && expression.charAt(curr_index - 1) != '('
                || curr_index+var.length() < expression.length()
                    && operations.indexOf(expression.charAt(curr_index + var.length())) == -1
                    && expression.charAt(curr_index + var.length()) != ')')
                return false;
            curr_index = expression.indexOf(var, curr_index+1);
        }
        return true;
    }

    private boolean correctVariables() {
        String curr_part = expression;
        String var = searchForVariable(curr_part);
        while (!var.equals("")) {
            if (correctVariable(var)) {
                curr_part = curr_part.replaceAll(var, "0");
                var = searchForVariable(curr_part);
            }
            else
                return false;
        }
        return true;
    }

    private boolean correctBrackets() {
        int     next_left_bracket,
                next_right_bracket,
                brackets_balance = 0,
                curr_index = -1;
        boolean correctSymbols;
        while (curr_index < expression.length()-1) {
            next_left_bracket = expression.indexOf('(', curr_index + 1);
            correctSymbols = correctSymbolsNearBracket(next_left_bracket);
            if (!correctSymbols)
                return false;
            next_right_bracket = expression.indexOf(')', curr_index + 1);
            correctSymbols = correctSymbolsNearBracket(next_right_bracket);
            if (!correctSymbols)
                return false;
            curr_index = Math.min(next_left_bracket != -1 ? next_left_bracket : expression.length(), next_right_bracket != -1 ? next_right_bracket : expression.length());
            if (curr_index < expression.length())
                if (expression.charAt(curr_index) == '(')
                    brackets_balance++;
                else
                    brackets_balance--;
            if (brackets_balance < 0)
                return false;
        }
        return brackets_balance == 0;
    }

    private boolean correct() {
        boolean correct = correctBrackets();
        if (!correct)
            return false;
        correct = correctVariables();
        if (!correct)
            return false;
        try {
            ExpressionSolver solver = new ExpressionSolver(this);
            solver.calculateFull();
        }
        catch (Exception e) {
            result = 0;
            return false;
        }
        return true;
    }

    @Override
    public void solveExpression() {
        System.out.println("Доступные специальные символы: ( ) + - * / ^ !");
        System.out.println("Переменные должны состоять из латинских букв");
        System.out.println("Доступные функции: sin, cos, tan, cotan, abs");
        System.out.println("Введите выражение:");
        Scanner scanner = new Scanner(System.in);
        expression = scanner.nextLine();
        expression = expression.replaceAll("\\s", "");

        while (!correct()) {
            System.out.println("В формуле допущена ошибка.");
            System.out.println("Введите выражение:");
            expression = scanner.nextLine();
            expression = expression.replaceAll("\\s", "");
        }
    }

    @Override
    public double getResult() {
        return result;
    }
}