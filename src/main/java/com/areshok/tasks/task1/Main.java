package com.areshok.tasks.task1;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Enter data from the keyboard
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a non-negative integer N: ");
        int N = scanner.nextInt();
        scanner.close();

        // Compute the Nth Catalan number
        BigInteger catalan = findCatalan(N);

        System.out.println("The number of valid parenthesis expressions for N = " + N + " is: " + catalan);
    }

    private static BigInteger findCatalan(int N) {
        // Compute factorials
        BigInteger fact2N = factorial(2 * N);
        BigInteger factN = factorial(N);
        BigInteger factNPlus1 = factorial(N + 1);

        // Catalan number formula: C_N = (2N)! / ((N+1)! * N!)
        return fact2N.divide(factNPlus1.multiply(factN));
    }

    // Function to compute factorial
    private static BigInteger factorial(int num) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
