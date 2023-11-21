import com.java.ExpressionSolver.ExpressionSolver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolverTest {
    ExpressionSolver solver;

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @After
    public void after() {
        System.setIn(System.in);
    }

    @Test
    public void calculatorTest1() {
        provideInput("12 + 32*(2-65) + 2048/64 - 2^4");
        solver = new ExpressionSolver();
        solver.inputExpression();
        Assert.assertEquals(solver.solveExpression(), -1988.0, 0);
    }

    @Test
    public void calculatorTest2() {
        provideInput("(((sin(-3) + tan(123) * a - b + c)))\n10\n4\n12");
        solver = new ExpressionSolver();
        solver.inputExpression();
        Assert.assertEquals(solver.solveExpression(), 14, 1);
    }

    @Test
    public void calculatorTest3() {
        provideInput("(1+5)-45*54++33\n10");
        solver = new ExpressionSolver();
        solver.inputExpression();
        Assert.assertEquals(solver.solveExpression(), 10, 0);
    }
}
