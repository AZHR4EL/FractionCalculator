package com.AZHR4EL.bruchrechner;

import java.util.ArrayList;

public class MultiFractionCalculator {

    // Class variables  ////////////////////////////////////////////////////////////////////////////////////////////////
    private String strInputEquation = ""; // Will held the whole given equation
    public String strEquationMonitor = ""; // Will held the equation with its calculating steps

    // Class constructors  /////////////////////////////////////////////////////////////////////////////////////////////


    public MultiFractionCalculator(String strInputEquation) {

        this.strInputEquation = strInputEquation;
    }

    public String getStrEquationMonitor() {
        return strEquationMonitor;
    }

    public t_fraction BracketFractionCalculator() {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Handles the popping out of substrings from equation string
        // (equations in brackets will be a substring before calculating and then in the
        // whole equation replaced by its result)
        // Will call functions operand replacer, checkBracketExistence, innerBracketPositions and
        // multipleFractionCalculator
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        operandReplacer();

        String strWholeEquation = this.strInputEquation;
        String strLine = "--------------------------------------------------";
        this.strEquationMonitor = strLine + "\n" + this.strEquationMonitor + this.strInputEquation;

        while (checkBracketExistence(strWholeEquation)) {

            boolean test = checkBracketExistence(strWholeEquation);

            int nBracketOpenIndex = innerBracketPositions(strWholeEquation)[0];
            int nBracketCloseIndex = innerBracketPositions(strWholeEquation)[1];

            String strBracketEquation = strWholeEquation.substring(nBracketOpenIndex + 1, nBracketCloseIndex);
            String strReplacementEquation = strWholeEquation.substring(nBracketOpenIndex, nBracketCloseIndex + 1);
            System.out.println(strBracketEquation);
            t_fraction tFractionX = multipleFractionCalculator(strBracketEquation);
            String strResultingFraction = tFractionX.getnNumerator() + "/" + tFractionX.getnDenominator();

            strWholeEquation = strWholeEquation.replace(strReplacementEquation, strResultingFraction);
            this.strEquationMonitor = this.strEquationMonitor + "\n" + strWholeEquation;
        }

        t_fraction tResultFraction = multipleFractionCalculator(strWholeEquation);

        this.strEquationMonitor = this.strEquationMonitor + "\n" + "Result: " + tResultFraction.printFractionReturner();
        this.strEquationMonitor = this.strEquationMonitor + "\n" + strLine;

        return tResultFraction;
    }

    public t_fraction multipleFractionCalculator (String strInputEquation) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Handles the fraction calculations of a String with multiple fractions
        // Will Call functions  arithmeticOperandSorter and multipleFractionLister beforehand and
        // the arithmetic functions (add, subtract, multiply, divide) as also function arithmeticOperandSorterCorrection
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<ArrayList<Integer>> arlOperandsPriority = arithmeticOperandSorter(strInputEquation);
        ArrayList<t_fraction> arlFractionArrayList = multipleFractionLister(strInputEquation);

        while (arlFractionArrayList.size() > 1) {

            while (arlOperandsPriority.get(0).size() > 0) {

                int operandIndex = arlOperandsPriority.get(0).size() - 1;
                int fractionIndex = arlOperandsPriority.get(0).get(operandIndex);

                t_fraction tFraction0 = arlFractionArrayList.get(fractionIndex);
                t_fraction tFraction1 = arlFractionArrayList.get(fractionIndex + 1);

                t_fraction tFractionX = new t_fraction();
                tFractionX.fractionDivision(tFraction0, tFraction1);

                arlFractionArrayList.set(fractionIndex, tFractionX);
                arlFractionArrayList.remove(fractionIndex + 1);
                arlOperandsPriority.get(0).remove(operandIndex);

                arlOperandsPriority = arithmeticOperandSorterCorrection(operandIndex, arlOperandsPriority);
            }

            while (arlOperandsPriority.get(1).size() > 0) {

                int operandIndex = arlOperandsPriority.get(1).size() - 1;
                int fractionIndex = arlOperandsPriority.get(1).get(operandIndex);

                t_fraction tFraction0 = arlFractionArrayList.get(fractionIndex);
                t_fraction tFraction1 = arlFractionArrayList.get(fractionIndex + 1);

                t_fraction tFractionX = new t_fraction();
                tFractionX.fractionMultiplication(tFraction0, tFraction1);

                arlFractionArrayList.set(fractionIndex, tFractionX);
                arlFractionArrayList.remove(fractionIndex + 1);
                arlOperandsPriority.get(1).remove(operandIndex);

                arlOperandsPriority = arithmeticOperandSorterCorrection(operandIndex, arlOperandsPriority);
            }

            while (arlOperandsPriority.get(2).size() > 0) {

                int operandIndex = arlOperandsPriority.get(2).size() - 1;
                int fractionIndex = arlOperandsPriority.get(2).get(operandIndex);

                t_fraction tFraction0 = arlFractionArrayList.get(fractionIndex);
                t_fraction tFraction1 = arlFractionArrayList.get(fractionIndex + 1);

                t_fraction tFractionX = new t_fraction();
                tFractionX.fractionAddition(tFraction0, tFraction1);

                arlFractionArrayList.set(fractionIndex, tFractionX);
                arlFractionArrayList.remove(fractionIndex + 1);
                arlOperandsPriority.get(2).remove(operandIndex);

                arlOperandsPriority = arithmeticOperandSorterCorrection(operandIndex, arlOperandsPriority);
            }

            while (arlOperandsPriority.get(3).size() > 0) {

                int operandIndex = arlOperandsPriority.get(3).size() - 1;
                int fractionIndex = arlOperandsPriority.get(3).get(operandIndex);

                t_fraction tFraction0 = arlFractionArrayList.get(fractionIndex);
                t_fraction tFraction1 = arlFractionArrayList.get(fractionIndex + 1);

                t_fraction tFractionX = new t_fraction();
                tFractionX.fractionSubtraction(tFraction0, tFraction1);

                arlFractionArrayList.set(fractionIndex, tFractionX);
                arlFractionArrayList.remove(fractionIndex + 1);
                arlOperandsPriority.get(3).remove(operandIndex);

                arlOperandsPriority = arithmeticOperandSorterCorrection(operandIndex, arlOperandsPriority);
            }
        }
        return arlFractionArrayList.get(0);
    }

    public ArrayList<ArrayList<Integer>> arithmeticOperandSorterCorrection(int nLastFractionIndex, ArrayList<ArrayList<Integer>> arlOperandsPriority) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Corrects Operand ArrayList<ArrayList<Integer>> from this classes function arithmeticOperandSorter
        // after popping an operand-index-integer from the contained <ArrayList<Integer>s
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (ArrayList<Integer> arlIntegerArraylist : arlOperandsPriority) {

            for (int elements = 0; elements < arlIntegerArraylist.size(); elements++) {

                if (arlIntegerArraylist.get(elements) > nLastFractionIndex) {

                    arlIntegerArraylist.set(elements, (arlIntegerArraylist.get(elements) - 1));
                }
            }
        }
        return arlOperandsPriority;
    }


    public ArrayList<t_fraction> multipleFractionLister(String strInputEquation) { //, ArrayList<ArrayList<Integer>> arlArraylistOfArraylist

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Converts an equation with multiple fractions inclusive negative-signs to class-type t_fraction
        // and saves them to an ArrayList<t_fraction>
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        strInputEquation = strInputEquation + " ";

        ArrayList<t_fraction> arlFractions = new ArrayList<t_fraction>();

        String strNumerator = "";
        String strDenominator = "";

        int nNumeratorBeginningIndex = 0;
        int nNumeratorEndingIndex = 0;
        int nDenominatorBeginningIndex = 0;
        int nDenominatorEndingIndex = 0;
        boolean bNegativeFraction = false;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            if (Character.isDigit(strInputEquation.charAt(nCharacter))) {

                nNumeratorBeginningIndex = nCharacter;

                if ((nCharacter > 0) && ((strInputEquation.charAt(nCharacter - 1)) == '-')) {

                    bNegativeFraction = true;
                }

                while (strInputEquation.charAt(nCharacter) != '/') {

                    nCharacter++;
                }

                if (strInputEquation.charAt(nCharacter) == '/') {

                    nNumeratorEndingIndex = nCharacter;
                }

                while (!Character.isDigit(strInputEquation.charAt(nCharacter))) {

                    nCharacter++;
                }

                if (Character.isDigit(strInputEquation.charAt(nCharacter))) {

                    nDenominatorBeginningIndex = nCharacter;
                };

                while (Character.isDigit(strInputEquation.charAt(nCharacter))) {

                    nCharacter++;

                }

                if (!Character.isDigit(strInputEquation.charAt(nCharacter))) {

                    nDenominatorEndingIndex = nCharacter;
                };

                strNumerator = strInputEquation.substring(nNumeratorBeginningIndex, nNumeratorEndingIndex);
                int nNumerator = Integer.parseInt(strNumerator);

                if (bNegativeFraction) {
                    nNumerator *= -1;
                    bNegativeFraction = false;
                }

                strDenominator = strInputEquation.substring(nDenominatorBeginningIndex, nDenominatorEndingIndex);
                int nDenominator = Integer.parseInt(strDenominator);

                t_fraction tFraction = new t_fraction(nNumerator, nDenominator);
                arlFractions.add(tFraction);
            }
        }

        return arlFractions;
    }

    public ArrayList<ArrayList<Integer>> arithmeticOperandSorter(String strInputEquation) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Gives each Operand an index and saves it to an ArrayList<ArrayList<Integer>>
        // {Divide-indices<Integer>, Multiply-indices<Integer>, Add-indices<Integer>, Subtract-indices<Integer>}
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int nArithmeticOperandsNumber = 0;

        ArrayList<Integer> DivideOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> MultiplyOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> AddOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> SubtractOperandPositions = new ArrayList<Integer>();

        int nOperandSequence = 0;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            switch (chCurrentCharacter) {
                case 'd':
                    DivideOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                case 'u':
                    MultiplyOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                case 'p':
                    AddOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                case 'm':
                    SubtractOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                default:
                    break;
            }
        }

        ArrayList<ArrayList<Integer>> arlOperandsPriority = new ArrayList<>();
        arlOperandsPriority.add(DivideOperandPositions);
        arlOperandsPriority.add(MultiplyOperandPositions);
        arlOperandsPriority.add(AddOperandPositions);
        arlOperandsPriority.add(SubtractOperandPositions);

        return arlOperandsPriority;
    }

    public void operandReplacer() {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Replaces operands by specific characters for an easier parse into class-type t_fraction
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.strInputEquation = this.strInputEquation.replace(" + ", " p ");
        this.strInputEquation = this.strInputEquation.replace(" - ", " m ");
        this.strInputEquation = this.strInputEquation.replace(" * ", " u ");
        this.strInputEquation = this.strInputEquation.replace(" : ", " d ");
    }

    public boolean checkBracketExistence(String strInputEquation) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Checks the existence of brackets inside an fraction equation and checks for wrong number of
        // open and close brackets (uneven throws an error), gives back boolean, if brackets exist
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        boolean bBracketsExist = false;
        int nOpenBracketSum = 0;
        int nCloseBracketSum = 0;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            if (chCurrentCharacter == '(') {
                nOpenBracketSum++;
                bBracketsExist = true;
            }
            else if (chCurrentCharacter == ')') {
                nCloseBracketSum++;
                bBracketsExist = true;
            }
        }

        if (nOpenBracketSum != nCloseBracketSum) {

            System.out.println("Error, uneven number of brackets!");
        }

        return bBracketsExist;
    }

    public int[] innerBracketPositions (String strInputEquation) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Delivers the indices of the first inner closed Brackets (open bracket & closed bracket) as integer Array
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int nLastOpenBracketIndex = 0;
        int nLastCloseBracketIndex = 0;

        boolean bLastBracketWasOpening = false;

        for (int nCharacter = 0; nCharacter < strInputEquation.length(); nCharacter++) {

            char chCurrentChar = strInputEquation.charAt(nCharacter);

            if (chCurrentChar == '(') {

                nLastOpenBracketIndex = nCharacter;
                bLastBracketWasOpening = true;
            }
            if (chCurrentChar == ')' && bLastBracketWasOpening) {

                nLastCloseBracketIndex = nCharacter;
                break;
            }
        }

        return new int[] {nLastOpenBracketIndex, nLastCloseBracketIndex};
    }
}
