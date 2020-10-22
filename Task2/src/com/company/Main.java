package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static long factorial(long value){
        long result= 1l;
        for (long i = 1; i <= value ; i++) {
            result*=i;
        }
        return result;
    }

    public static long sumFactorial(long value){
        long result=0;
        for (long i = 1; i <= value; i++) {
            result+=factorial(i);
        }
        return result;
    }

    public static double allFunc (long value){
        return (1.0/factorial(value))*sumFactorial(value);
    }

    public static boolean getAccuracy(double value){

        String[] splitter = String.valueOf(value).split("\\.");
        int accuracy = splitter[1].length();
        return accuracy>=4;
    }

    public static String setAccuracy(double value){

        if (getAccuracy(value)){
            return String.valueOf(value);
        }else{
            return String.format("%.4f", value);
        }
    }

    public static void main(String[] args) {
        int number;
        double resultFunc;
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Enter number");
            number = in.nextInt();

            if (number>1){
                resultFunc = allFunc(number);
                System.out.println(setAccuracy(resultFunc));

            }else System.err.println("number must be greater than 0");
        }catch (InputMismatchException | NullPointerException e){
            System.err.println("invalid data");
        }
    }
}
