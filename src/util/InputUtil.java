package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    public static Scanner sc = new Scanner(System.in);

    public static String getText(String title) {
        System.out.print(title + ": ");
        return sc.nextLine();
    }
    public static Integer getInt(String title) {
        System.out.print(title + ": ");
        while (true) {

            try {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input, Enter a number: ");
                sc.nextLine();
            }
        }


    }
}
