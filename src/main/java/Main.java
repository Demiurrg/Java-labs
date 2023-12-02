import com.java.Timer.Timer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void add(List list, int quantity) {
        Timer timer = new Timer();
        timer.start();
        for (int i = 0; i < quantity; i++)
            list.add(i);
        System.out.println("add   " + quantity + "   " + timer.endAndGetTime() + "ns");
    }

    public static void get(List list, int quantity) {
        Timer timer = new Timer();
        Object result;
        timer.start();
        for (int i = 0; i < quantity; i++)
            result = list.get(i);
        System.out.println("get   " + quantity + "   " + timer.endAndGetTime() + "ns");
    }

    public static void set(List list, int quantity) {
        Timer timer = new Timer();
        timer.start();
        for (int i = 0; i < quantity; i++)
           list.set(i, i);
        System.out.println("set   " + quantity + "   " + timer.endAndGetTime() + "ns");
    }

    public static void contains(List list, int quantity) {
        Timer timer = new Timer();
        Object result;
        timer.start();
        for (int i = 0; i < quantity; i++)
            result = list.contains(i);
        System.out.println("contains   " + quantity + "   " + timer.endAndGetTime() + "ns");
    }

    public static void indexOf(List list, int quantity) {
        Timer timer = new Timer();
        timer.start();
        for (int i = 0; i < quantity; i++)
            list.indexOf(i);
        System.out.println("indexOf   " + quantity + "   " + timer.endAndGetTime() + "ns");
    }

    public static void remove(List list, int quantity) {
        Timer timer = new Timer();
        timer.start();
        for (int i = 0; i < quantity; i++)
            list.remove(0);
        System.out.println("remove   " + quantity + "   " + timer.endAndGetTime() + "ns");
    }

    public static void main(String[] args) {
        int quantity = 2000;
        ArrayList<Integer> arrayList = new ArrayList<>();
        System.out.println("ArrayList");
        add(arrayList, quantity);
        get(arrayList, quantity);
        set(arrayList, quantity);
        contains(arrayList, quantity);
        indexOf(arrayList, quantity);
        remove(arrayList, quantity);

        LinkedList<Integer> linkedList = new LinkedList<>();
        System.out.println("\nLinkedList");
        add(linkedList, quantity);
        get(linkedList, quantity);
        set(linkedList, quantity);
        contains(linkedList, quantity);
        indexOf(linkedList, quantity);
        remove(linkedList, quantity);
    }
}