import com.java.Reader.ExcelReader;

public class Main {
    public static void main(String[] args) {
        ExcelReader reader = new ExcelReader();
        reader.input("C:\\Users\\Demiurrg\\IdeaProjects\\Java labs\\src\\main\\java\\foreign_names.csv");
        reader.print();
    }
}