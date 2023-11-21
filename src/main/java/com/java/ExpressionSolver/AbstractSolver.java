package com.java.ExpressionSolver;

public interface AbstractSolver {
    /**
     * Решение выражения. Ввод и вывод в консоль осуществляются внутри метода.
     */
    void solveExpression();

    /**
     * Геттер результата после вычисления выражения (по умолчанию или в случае ошибки равен 0).
     *
     * @return результат вычисленного выражения
     */
    double getResult();
}
