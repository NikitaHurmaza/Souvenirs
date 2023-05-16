package org.example.for_enter_data;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ForInput {
    private Scanner scanner;
    public ForInput() {
        scanner=new Scanner(System.in);
    }

    public boolean inputValidate(String input) {

        if (input.contains("\n") || input.contains("\t") || input.isEmpty()) {

            return false;
        }

        return true;
    }
    public <T> T enterData(String n1, Types type) {
        if (type == Types.INTEGER) {
            Integer data = 0;
            Callable<Integer> dataInput = () -> {

                System.out.println(n1);

                int data1;

                try {

                    data1 = scanner.nextInt();
                    scanner.nextLine();

                } catch (InputMismatchException err) {

                    System.out.println("Входное значение не корректно");
                    scanner.next();
                    return -1;
                }

                return data1;
            };

            try {

                do {

                    data = dataInput.call();

                } while (data == -1);
            } catch (Exception err) {

                err.printStackTrace();
            }
            return (T) data;

        } else if (type == Types.DOUBLE) {
            Double data = 0.0;
            Callable<Double> dataInput = () -> {

                System.out.println(n1);
                double data1;

                try {

                    data1 = scanner.nextDouble();
                    scanner.nextLine();

                } catch (InputMismatchException err) {

                    System.out.println("Входное значение не корректно");
                    scanner.next();

                    return -1.0;
                }

                return data1;
            };

            try {

                do {

                    data = dataInput.call();

                } while (data == -1.0);

            } catch (Exception err) {

                err.printStackTrace();
            }
            return (T) data;
        } else if (type == Types.STRING) {
            String data = null;
            Callable<String> dataInput = () -> {

                System.out.println(n1);
                String data1 = scanner.nextLine();

                if (!inputValidate(data1)) {

                    System.out.println("Входное значение не корректно");
                    return null;
                }

                return data1;
            };

            try {

                do {

                    data = dataInput.call();

                } while (data == null);

            } catch (Exception err) {

                err.printStackTrace();
            }
            return (T) data;
        } else if (type == Types.LOCALDATE) {
            LocalDate data = null;
            Callable<LocalDate> dataInput = () -> {
                System.out.println(n1);
                LocalDate data1 = null;

                try {
                    String input = scanner.nextLine();
                    data1 = LocalDate.parse(input);
                } catch (DateTimeParseException err) {
                    System.out.println("Входное значение не корректно");
                }

                return data1;
            };

            try {
                do {
                    data = dataInput.call();
                } while (data == null);
            } catch (Exception err) {
                err.printStackTrace();
            }

            return (T) data;
        }
        return null;
    }
}
