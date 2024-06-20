import java.util.Scanner;

public class Condition {
      public static void main(String[] args) {
            System.out.println("Conditions");

            int x, y;
            Scanner input = new Scanner(System.in);
            x = input.nextInt();
            y = input.nextInt();

            if (x > y) {
                  System.out.println("x is greater than y");
            } else if (x < y) {
                  System.out.println("y is greater than x");
            } else {
                  System.out.println("x and y are equal");
            }
      }
}
