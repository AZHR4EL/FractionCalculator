package com.AZHR4EL.bruchrechner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // User Input      /////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Please enter a Fraction in following format:\nx/x [+-*:] x/x\"");
        //Scanner scanner = new Scanner(System.in);
        //String strUserInput = scanner.nextLine();

        //String strUserInput = "-1/2 - 1/4";
        //String strUserInput = "(3/4) : (-1/2)";
        String strUserInput = "((5/8 + -4/6) : (3/250 + 1/125) * (9/13 - -4/13) * 1/2)";
                            // RESULT: (-1/24 / 1/50 * 1 * 1/2) = (-25/12 * 1/2) = ||| -25/24 |||
        //String strUserInput = "";
        //String strUserInput = "";
        //String strUserInput = "";
        //String strUserInput = "(1/2 * (1/2 + 1/3) * (1/3 : 1/5 : 8/24)) : 1/6";
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Starting Fraction Calculator    /////////////////////////////////////////////////////////////////////////////
        MultiFractionCalculator fractionResolver = new MultiFractionCalculator(strUserInput);
        t_fraction tResultFraction = fractionResolver.BracketFractionCalculator();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Printing Result   ///////////////////////////////////////////////////////////////////////////////////////////
        System.out.println(fractionResolver.getStrEquationMonitor());
        System.out.println(tResultFraction.printFractionReturner());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
