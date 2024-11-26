package com.areshok.tasks.task3;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("The sum of the digits of 100! is: " + factorial(100));
    }

    public static int factorial(int num) {
        BigInteger factorial = BigInteger.ONE;

        // Calculate the factorial of the input number
        for (int i = 2; i <= num; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        // Convert the resulting factorial to a string for digit extraction
        String factorialString = factorial.toString();

        int sum = 0;

        // Passing each character in the string
        for (int i = 0; i < factorialString.length(); i++) {
            // Convert the character to its numeric value and add it to the sum
            sum += Character.getNumericValue(factorialString.charAt(i));
        }

        return sum;
    }
}
