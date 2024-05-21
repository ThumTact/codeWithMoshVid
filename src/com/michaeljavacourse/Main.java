package com.michaeljavacourse;

import java.awt.*;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    final static byte MONTHS = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        //best practice to use constants or final variables to describe "magic variables"
        final int MAX_PRINCIPAL = 1_000_000;
        final int MIN_PRINCIPAL = 1_000;
        //get variables from user input
        int principal = (int)readNumber("Principal: ", MIN_PRINCIPAL, MAX_PRINCIPAL);
        float annualRate = (float)readNumber("Annual Interest Rate: ", 0, 30);
        int years = (int)readNumber("Length in Years: ", 1, 30);
        printMortgagePayment(principal, annualRate, years);
        printBalance(years, principal, annualRate);
    }

    private static void printMortgagePayment(int principal, float annualRate, int years) {
        //Get monthly mortgage payment
        double mortgage = calculateMortgage(principal, annualRate, years);
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.print("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));
        System.out.println();
    }

    private static void printBalance(int years, int principal, float annualRate) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for(short month = 1; month <= years * MONTHS; month++){
            double balance = calculateBalance(years, principal, annualRate, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMortgage(int principal, double annualInterest, int numYears) {
        double numberOfPayments = numYears * MONTHS;
        double monthlyInterest = annualInterest / MONTHS / PERCENT;
        double numerator = monthlyInterest * Math.pow((1 + monthlyInterest), numberOfPayments);
        double denominator = Math.pow((1 + monthlyInterest), numberOfPayments) - 1;
        double rateMonthFrac = numerator / denominator;
        return principal * rateMonthFrac;
    }

    public static double calculateBalance(
            int numYears,
            int principal,
            double annualInterest,
            int numberOfPaymentsMade){
        double numberOfPayments = numYears * MONTHS;
        double monthlyInterest = annualInterest / MONTHS / PERCENT;
        double balance = principal *
                (Math.pow(1+monthlyInterest, numberOfPayments) - Math.pow(1+monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1+monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scan = new Scanner(System.in);
        double value;
        while(true){
            System.out.print(prompt);
            value = scan.nextFloat();
            if (value <= max && value > min)
                break;
            else{
                System.out.println("Enter a value greater than " + min + "and less than or equal to " + max);
                continue;
            }
        }
        return value;
    }

}