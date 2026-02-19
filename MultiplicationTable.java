public class MultiplicationTable {
    public static void main(String[] args) {
        int x, y;

        for (x = 1; x <= 10; x++) {
            for (y = 1; y<= 10; y++) {
                System.out.print((x * y) + "\t");


            }
            System.out.println();
        }
    }
}