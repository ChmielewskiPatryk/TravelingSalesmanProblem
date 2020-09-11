import java.util.LinkedHashSet;

public class test {

    public static void main(String[] args) {
        LinkedHashSet<Integer> hash = new LinkedHashSet(4);
        hash.add(1);
        hash.add(2);
        hash.add(3);
        hash.add(4);
        hash.add(5);
        System.out.println(hash);
    }
}
