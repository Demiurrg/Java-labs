import com.java.ArrayList.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListTest {

    ArrayList<Integer> list1;
    ArrayList<Integer> list2;
    final int SIZE = 10;
    int randomNum;
    int randomIndex;

    @Before
    public void before() {
        list1 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE; i++)
            list1.add(i, i);
        randomIndex = ThreadLocalRandom.current().nextInt(0, SIZE);
        randomNum = ThreadLocalRandom.current().nextInt(0, SIZE);
    }

    @Test
    public void add() {
        Assert.assertEquals(list1.getValue(randomNum), Integer.valueOf(randomNum));
    }

    @Test
    public void pushBack() {
        list2 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE; i++) {
            list2.pushBack(i);
        }
        Assert.assertEquals(list1.getValue(randomNum), list2.getValue(randomNum));
    }

    @Test
    public void replace() {
        list1.replace(randomNum, randomIndex);
        Assert.assertEquals(list1.getValue(randomIndex), Integer.valueOf(randomNum));
    }

    @Test
    public void pop() {
        Integer OldLastElement = list1.getValue(list1.getSize()-1);
        list1.pop();
        Integer NewLastElement = list1.getValue(list1.getSize()-1);
        assertThat(OldLastElement, not(NewLastElement));
    }

    @Test
    public void remove() {
        int OldValue = list1.getValue(randomIndex);
        list1.remove(randomIndex);
        int NewValue = list1.getValue(randomIndex);
        assertThat(OldValue, not(NewValue));
    }

    @Test
    public void find() {
        list1.add(randomNum, randomIndex);
        Assert.assertEquals(list1.find(randomNum), randomIndex);
    }

    @Test
    public void getValue() {
        list1.add(randomNum, randomIndex);
        Assert.assertEquals(list1.getValue(randomIndex), Integer.valueOf(randomNum));
    }

    @Test
    public void getSize() {
        Assert.assertEquals(list1.getSize(), SIZE);
    }

    @Test
    public void clear() {
        list1.clear();
        Assert.assertEquals(list1.getSize(), 0);
    }
}
