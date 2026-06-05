public class Main {
    public static void main(String[] args) {
        String content = "Java is an Object Oriented Programming Language";
        int count = 10000;  // We will loop many times to see the speed difference

        // Testing StringBuffer
        StringBuffer stringBuffer = new StringBuffer(content);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < count;  i++) {
            stringBuffer.append("!");  // Simulating work/traversal
        }

        long endTime = System.currentTimeMillis();
        System.out.println("StringBuffer Time: " + (endTime - startTime) + "ms");

        // Testing StringBuilder
        StringBuilder stringBuilder = new StringBuilder(content);
        startTime = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            stringBuilder.append("!"); // Simulating work/traversal
        }

        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder Time:  " + (endTime -  startTime) + "ms");
    }
}
