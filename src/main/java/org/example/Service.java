package org.example;

public class Service {
    public static String calculateCollatz(int number) {
        StringBuilder i = new StringBuilder();
        i.append(number);
        while (number != 1) {
            number = (number % 2 == 0) ? number / 2 : (number * 3) + 1 ;
            i.append(" -> ").append(number);
        }
        return i.toString();
    }
}
