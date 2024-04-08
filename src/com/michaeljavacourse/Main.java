package com.michaeljavacourse;

import java.awt.*;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //best practice to use constants or final variables to describe "magic variables"
        final int MAX_PRINCIPAL = 1_000_000;
        final int MIN_PRINCIPAL = 1_000;
        final byte MONTHS = 12;
        final byte PERCENT = 100;
        //get variables from user input
        Scanner scan = new Scanner(System.in);
        int principal = 0;
        while(true){
            System.out.print("Principal ($1K - $1M): ");
            principal = scan.nextInt();
            if (principal >= MIN_PRINCIPAL && principal <= MAX_PRINCIPAL){
                break;
            }else{
                System.out.println("Enter a number between 1,000 and 1,000,000.");
                continue;
            }
        }
        float annualRate = 0;
        float monthlyRate = 0;
        while(true){
            System.out.print("Annual Interest Rate: ");
            annualRate = scan.nextFloat();
            if (annualRate <= 30 && annualRate > 0){
                monthlyRate = annualRate / MONTHS / PERCENT;
                break;
            }else{
                System.out.println("Enter a value greater than 0 and less than or equal to 30");
                continue;
            }
        }
        int years = 0;
        int months = 0;
        while(true){
            System.out.print("Period (Years): ");
            years = scan.nextInt();
            if (years <= 30 && years >= 1){
                months = years * MONTHS;
                break;
            }else{
                System.out.println("Please input a value greater than or equal to 1 and less than or equal to 30");
                continue;
            }
        }
        double numerator = monthlyRate * Math.pow((1 + monthlyRate), months);
        double denominator = Math.pow((1 + monthlyRate), months) - 1;
        double rateMonthFrac = numerator / denominator;

        double mortgage = principal * rateMonthFrac;
        NumberFormat mortgageDollars = NumberFormat.getCurrencyInstance();
        System.out.print("Mortgage: " + mortgageDollars.format(mortgage));
    }
}