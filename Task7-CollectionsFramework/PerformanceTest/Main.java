import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        // Data
        int[] data = {23, 44, -9, 14, 123, 56, 223, 445, 33, 45, 89, 1, 0, 21, 100};

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        // Add data to both
        for (int num : data) {
            arrayList.add(num);
            linkedList.add(num);
        }

        int key = 100;

        // ArrayList Search
        long startTimeArray = System.nanoTime();

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == key) {
                break;
            }
        }

        long endTimeArray = System.nanoTime();
        long timeArray = (endTimeArray - startTimeArray) / 1_000_000;

        // LinkedList Search
        long startTimeLinked = System.nanoTime();

        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i) == key) {
                break;
            }
        }

        long endTimeLinked = System.nanoTime();
        long timeLinked = (endTimeLinked - startTimeLinked) / 1_000_000;

        System.out.println("ArrayList search time: " + timeArray + " ms");
        System.out.println("LinkedList search time: " + timeLinked + " ms");
    }
}
