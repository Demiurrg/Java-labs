package com.java.ExpressionSolver;

public interface AbstractSolver {
    /**
     * Ввод выражения для его последующего вычисления. При некорректном вводе, метод просит ввести заново.
     */
    void inputExpression();

    /**
     * Вычисление заданного ранее выражения.
     *
     * @return результат посчитанного выражения
     */
    double solveExpression();
}
