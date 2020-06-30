package com.AZHR4EL.bruchrechner;

import java.util.ArrayList;

public class FractionPriorityResolver {

    // Class variables  ////////////////////////////////////////////////////////////////////////////////////////////////
    private String strInputEquation = "";
    private String strEquationMonitor = "";

    // Class constructors  /////////////////////////////////////////////////////////////////////////////////////////////


    public FractionPriorityResolver(String strInputEquation) {

        strInputEquation = strInputEquation + " ";
        this.strInputEquation = strInputEquation;
    }

    public String getStrEquationMonitor() {
        return strEquationMonitor;
    }

    public t_fraction BracketFractionCalculator() {

        String strWholeEquation = this.strInputEquation;
        String strLine = "--------------------------------------------------";
        this.strEquationMonitor = strLine + "\n" + this.strEquationMonitor + this.strInputEquation;

        while (checkBracketExistence()) {

            int nBracketOpenIndex = innerBracketPositions()[0];
            int nBracketCloseIndex = innerBracketPositions()[1];

            String strBracketEquation = strWholeEquation.substring(nBracketOpenIndex + 1, nBracketCloseIndex);
            String strReplacementEquation = strWholeEquation.substring(nBracketOpenIndex, nBracketCloseIndex + 1);

            t_fraction tFractionX = multipleFractionCalculator(strBracketEquation);
            String strResultingFraction = tFractionX.getnNumerator() + "/" + tFractionX.getnDenominator();

            strWholeEquation.replace(strBracketEquation, strReplacementEquation);
            this.strEquationMonitor = this.strEquationMonitor + "\n" + strWholeEquation;
        }

        t_fraction tResultFraction = multipleFractionCalculator(strWholeEquation);

        this.strEquationMonitor = this.strEquationMonitor + "\n" + "Result: " + tResultFraction.printFractionReturner();
        this.strEquationMonitor = this.strEquationMonitor + "\n" + strLine;

        return tResultFraction;
    }

    public t_fraction multipleFractionCalculator (String strInputEquation) {

        ArrayList<ArrayList<Integer>> arlOperandsPriority = arithmeticOperandSorter(strInputEquation);
        ArrayList<t_fraction> arlFractionArrayList = multipleFractionLister(strInputEquation);

        while (arlOperandsPriority.get(0).size() > 0) {

            t_fraction tFraction0 = arlFractionArrayList.get(arlOperandsPriority.get(0).get(0));
            t_fraction tFraction1 = arlFractionArrayList.get(arlOperandsPriority.get(0).get(0) + 1);

            t_fraction tFractionX = new t_fraction();
            tFractionX.fractionMultiplication(tFraction0, tFraction1);

            arlFractionArrayList.set(arlOperandsPriority.get(0).get(0), tFractionX);
            arlFractionArrayList.remove(arlOperandsPriority.get(0).get(0) + 1);
            arlOperandsPriority.get(0).remove(0);
        }

        while (arlOperandsPriority.get(1).size() > 0) {

            t_fraction tFraction0 = arlFractionArrayList.get(arlOperandsPriority.get(1).get(0));
            t_fraction tFraction1 = arlFractionArrayList.get(arlOperandsPriority.get(1).get(0) + 1);

            t_fraction tFractionX = new t_fraction();
            tFractionX.fractionDivision(tFraction0, tFraction1);

            arlFractionArrayList.set(arlOperandsPriority.get(1).get(0), tFractionX);
            arlFractionArrayList.remove(arlOperandsPriority.get(1).get(0) + 1);
            arlOperandsPriority.get(1).remove(0);
        }

        while (arlOperandsPriority.get(2).size() > 0) {

            t_fraction tFraction0 = arlFractionArrayList.get(arlOperandsPriority.get(2).get(0));
            t_fraction tFraction1 = arlFractionArrayList.get(arlOperandsPriority.get(2).get(0) + 1);

            t_fraction tFractionX = new t_fraction();
            tFractionX.fractionAddition(tFraction0, tFraction1);

            arlFractionArrayList.set(arlOperandsPriority.get(2).get(0), tFractionX);
            arlFractionArrayList.remove(arlOperandsPriority.get(2).get(0) + 1);
            arlOperandsPriority.get(2).remove(0);
        }

        while (arlOperandsPriority.get(3).size() > 0) {

            t_fraction tFraction0 = arlFractionArrayList.get(arlOperandsPriority.get(3).get(0));
            t_fraction tFraction1 = arlFractionArrayList.get(arlOperandsPriority.get(3).get(0) + 1);

            t_fraction tFractionX = new t_fraction();
            tFractionX.fractionAddition(tFraction0, tFraction1);

            arlFractionArrayList.set(arlOperandsPriority.get(3).get(0), tFractionX);
            arlFractionArrayList.remove(arlOperandsPriority.get(3).get(0) + 1);
            arlOperandsPriority.get(3).remove(0);
        }

        if (arlFractionArrayList.size() > 1) {

            System.out.println("Error in multipleFractionCalculator, more than one resulting fraction!");
        }

        return arlFractionArrayList.get(0);
    }

    public ArrayList<t_fraction> multipleFractionLister(String strInputEquation) { //, ArrayList<ArrayList<Integer>> arlArraylistOfArraylist

        ArrayList<t_fraction> arlFractions = new ArrayList<t_fraction>();

        String strNumerator = "";
        String strDenominator = "";

        int nNumeratorBeginningIndex = 0;
        int nNumeratorEndingIndex = 0;
        int nDenominatorBeginningIndex = 0;
        int nDenominatorEndingIndex = 0;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            if (Character.isDigit(strInputEquation.charAt(nCharacter))) {

                nNumeratorBeginningIndex = nCharacter;

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

                strDenominator = strInputEquation.substring(nDenominatorBeginningIndex, nDenominatorEndingIndex);
                int nDenominator = Integer.parseInt(strDenominator);

                t_fraction tFraction = new t_fraction(nNumerator, nDenominator);
                arlFractions.add(tFraction);
            }
        }

        return arlFractions;
    }

    public ArrayList<ArrayList<Integer>> arithmeticOperandSorter(String strInputEquation) {

        int nArithmeticOperandsNumber = 0;

        ArrayList<Integer> MultiplyOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> DivideOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> AddOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> SubtractOperandPositions = new ArrayList<Integer>();

        int nOperandSequence = 0;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            switch (chCurrentCharacter) {

                case '+':
                    AddOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                case '-':
                    SubtractOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                case '*':
                    MultiplyOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                case ':':
                    DivideOperandPositions.add(nOperandSequence);
                    nOperandSequence++;
                    break;
                default:
                    break;
            }
        }

        ArrayList<ArrayList<Integer>> arlOperandsPriority = new ArrayList<>();
        arlOperandsPriority.add(MultiplyOperandPositions);
        arlOperandsPriority.add(DivideOperandPositions);
        arlOperandsPriority.add(AddOperandPositions);
        arlOperandsPriority.add(SubtractOperandPositions);

        return arlOperandsPriority;
    }


    public boolean numberOperandsFractionsCorrect () {

        int nArithmeticOperandsNumber = 0;
        int nFractionsNumber = 0;
        boolean bFractionsOperandsNumberFits = false;

        for (int nCharacter = 0; nCharacter < this.strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = this.strInputEquation.charAt(nCharacter);

            switch (chCurrentCharacter) {

                case '+':
                case '-':
                case '*':
                case ':':
                    nArithmeticOperandsNumber++;
                    break;
                case '/':
                    nFractionsNumber++;
                    break;
                default:
                    break;
            }
        }

        if (nArithmeticOperandsNumber == (nFractionsNumber - 1)) {

            bFractionsOperandsNumberFits = true;
        }

        return bFractionsOperandsNumberFits;
    }

    public boolean checkBracketExistence() {

        boolean bBracketsExist = false;
        int nOpenBracketSum = 0;
        int nCloseBracketSum = 0;

        for (int nCharacter = 0; nCharacter < this.strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = this.strInputEquation.charAt(nCharacter);

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

    public int[] innerBracketPositions () {

        int nLastOpenBracketIndex = 0;
        int nLastCloseBracketIndex = 0;

        boolean bLastBracketWasOpening = false;

        for (int nCharacter = 0; nCharacter < this.strInputEquation.length(); nCharacter++) {

            char chCurrentChar = this.strInputEquation.charAt(nCharacter);

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

    public void bracketPriorityResolver (String strInputEquation) {

        for (int nCharacter = 0; nCharacter < strInputEquation.length(); nCharacter++) {

            char chCurrentChar = strInputEquation.charAt(nCharacter);

            int nLastOpenBracketIndex = 0;
            int nLastCloseBracketIndex = 0;

            if (chCurrentChar == '(') {
                nLastOpenBracketIndex = nCharacter;
            }
            if (chCurrentChar == ')') {
                nLastCloseBracketIndex = nCharacter;
            }

        }
    }


    



}
