import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter Numerator: ");
            int numerator = sc.nextInt();
            System.out.println("Enter Denominator: ");
            int denominator = sc.nextInt();

            int result = numerator / denominator;

            System.out.println("Result: " + result);
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
