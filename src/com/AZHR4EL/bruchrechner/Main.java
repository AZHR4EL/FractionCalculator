package com.AZHR4EL.bruchrechner;;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*String strPlus = "+";
        String strMinus = "-";
        String strDivide = ":";
        String strMultiply = "*";*/

        ///////////////////////////////////////////////////////////////////////////////////////////
        // User Input                                                                            //
        System.out.println("Please enter a Fraction in following format:\nx/x [+-*:] x/x\"");

        //Scanner scanner = new Scanner(System.in);

        //String strUserInput = scanner.nextLine();

        String strUserInput = "1/2 + 1/4 * 1/3";

        FractionPriorityResolver tFractionPrio = new FractionPriorityResolver(strUserInput);

        ArrayList<t_fraction> testarray = tFractionPrio.multipleFractionCalculator();

        for (t_fraction fraction : testarray) {

            System.out.println(fraction.printFractionReturner());
        }

        // Testfractions:
        // 3/2 + 1/4 !!!

        //                                                                                       //
        ///////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////
        // User Input Conversion                                                                 //

        /*char chArithmeticOperation = ' ';
        if (strUserInput.contains(strPlus)) { chArithmeticOperation = '+'; }
        if (strUserInput.contains(strMinus)) { chArithmeticOperation = '-'; }
        if (strUserInput.contains(strMultiply)) { chArithmeticOperation = '*'; }
        if (strUserInput.contains(strDivide)) { chArithmeticOperation = ':'; }

        strUserInput = strUserInput.replace(" ", "");

        String strFractionDivider = "/";
        String strArithmethicChar = "[-+*:]";

        String[] arrFractions = new String[2];
        arrFractions = strUserInput.split(strArithmethicChar);

        String[] arrFractionParts0 = new String[2];
        arrFractionParts0 = arrFractions[0].split(strFractionDivider);

        String[] arrFractionParts1 = new String[2];
        arrFractionParts1 = arrFractions[1].split(strFractionDivider);

        //                                                                                       //
        ///////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////
        // Fraction Objects                                                                      //

        int nNumerator = 0;
        int nDenominator = 0;

        // Fraction 0);
        nNumerator = Integer.parseInt(arrFractionParts0[0]);
        nDenominator = Integer.parseInt(arrFractionParts0[1]);

        t_fraction tFraction0 = new t_fraction(nNumerator, nDenominator);

        // Fraction 1
        nNumerator = Integer.parseInt(arrFractionParts1[0]);
        nDenominator = Integer.parseInt(arrFractionParts1[1]);

        t_fraction tFraction1 = new t_fraction(nNumerator, nDenominator);

        //                                                                                       //
        ///////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////
        // User Input Conversion                                                                 //

        t_fraction tFractionX = new t_fraction();

        switch (chArithmeticOperation) {

            case '+':
                tFractionX.fractionAddition(tFraction0, tFraction1);
                break;
            case '-':
                tFractionX.fractionSubtraction(tFraction0, tFraction1);
                break;
            case '*':
                tFractionX.fractionMultiplication(tFraction0, tFraction1);
                break;
            case ':':
                tFractionX.fractionDivision(tFraction0, tFraction1);
                break;
            default:
                break;
        }

        System.out.println(tFractionX.printFractionReturner());*/
    }
}
