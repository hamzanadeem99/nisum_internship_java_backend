import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        // Create a sorted ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        list.add(901);
        list.add(902);
        list.add(903);
        list.add(904);
        list.add(905);

        // item to search
        int key = 905;

        // Searching using collections API
        int result = Collections.binarySearch(list, key);

        if (result >= 0) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found");
        }
    }
}
