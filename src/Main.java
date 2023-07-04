import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        Calculator calculator = new Calculator(string);
        System.out.println(calculator.toString());

    }
    public static String calc(String input){
        return input;
    };
}