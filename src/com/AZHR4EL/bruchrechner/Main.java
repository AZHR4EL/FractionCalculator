package com.AZHR4EL.bruchrechner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

////////// User Input      /////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Please enter a Fraction in following format:   x/x [+-*:] (x/x [+-*:] x/x)\"");
        System.out.println("Format Example: (1/5 : 2/1) * ((1/2 + -5/1) : (-3/20 * 14/13))");
        Scanner scanner = new Scanner(System.in);
        String strUserInput = scanner.nextLine();

////////// Inputs for testing      /////////////////////////////////////////////////////////////////////////////////////
        //String strUserInput = "-1/2 - 1/4";
        //String strUserInput = "(3/4) : (-1/2)";
        //String strUserInput = "((5/8 + -4/6) : (3/250 + 1/125) * (9/13 - -4/13) * 1/2)";
                            // RESULT: (-1/24 / 1/50 * 1 * 1/2) = (-25/12 * 1/2) = ||| -25/24 |||
        //String strUserInput = "2/2 + 2/3";
                            // RESULT: 2/2 + 2/3 = 5/3
        //String strUserInput = "(1/5 - 2/1) * (1/2 + 5/1) : (3/20 * 14/13)";
                            // RESULT: -429/7
        //String strUserInput = "3/2 * 1/1 : (-1/2) * (5/7 * 3/3 - (2/2 - 2/10 - 3/3) * 1000/999 : (199/1000 + 1/1))";
                            //        3/2 * 1/1 : (-1/2) * (5/7 * 3/3 - (2/2 - 2/10 - 3/3) * 1000/999 : (199/1000 + 1/1)) =
                            // -6/2 * 		     (5/7 - (2/10 * 1000000/1197801))
                            // -6/2 *		     (5/7 - 200000/1197801)
                            // -6/2 *		     (4589005/8384607)
                            // RESULT = -4589005/2794869
                            // 3/2 * 1/1 : (-1/2) * (5/7 * 3/3 - (2/2 - 2/10 - 3/3) * 1000/999 : (199/1000 + 1/1)) =
                            // 3/2 * 1/1 : (-1/2) * (5/7 - -1/5 * 1000/999 : 1199/1000) =
                            // -3/1 * (5/7 - -200/999 : 1199/1000) =
                            // -3/1 * (5/7 - -200000/1197801) =
                            // 7389005/8384607 =
                            // RESULT = 7389005/2794869
        //String strUserInput = "3/2 * 1/1 : (-1/2) * (5/7 * 3/3 - (2/2 - 2/10 - 3/3) * 50/99)";
        //String strUserInput = "(1/2 * (1/2 + 1/3) * (1/3 : 1/5 : 8/24)) : 1/6";


////////// Starting Fraction Calculator    /////////////////////////////////////////////////////////////////////////////
        MultiFractionCalculator fractionResolver = new MultiFractionCalculator(strUserInput);
        t_fraction tResultFraction = fractionResolver.BracketFractionCalculator();


////////// Printing Result   ///////////////////////////////////////////////////////////////////////////////////////////
        System.out.println(fractionResolver.getStrEquationMonitor());
        System.out.println(tResultFraction.printFractionReturner());
    }
}
