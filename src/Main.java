import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(calc(string));

    }
    public static String calc(String input){
        return new Calculator(input).toString();
    };
}