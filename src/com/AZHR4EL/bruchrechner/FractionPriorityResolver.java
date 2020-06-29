package com.AZHR4EL.bruchrechner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FractionPriorityResolver {

    // Class variables  ////////////////////////////////////////////////////////////////////////////////////////////////
    private String strInputEquation = "";

    // Class constructors  /////////////////////////////////////////////////////////////////////////////////////////////


    public FractionPriorityResolver(String strInputEquation) {

        strInputEquation = strInputEquation + " ";
        this.strInputEquation = strInputEquation;
    }

    public void multipleFractionCalculator (ArrayList<ArrayList<Integer>> arlOperandsPriority, ArrayList<t_fraction> arlFractionArrayList) {

        ArrayList<t_fraction> arlFractionArraylistNew = new ArrayList<>();

        if (arlOperandsPriority.get(0).size() > 0) {

            for (Integer arlOperationIndex : arlOperandsPriority.get(0)) {

                t_fraction tFractionX = arlFractionArrayList.get(arlOperationIndex);

            }
        }

    }



    public ArrayList<t_fraction> multipleFractionLister() { //, ArrayList<ArrayList<Integer>> arlArraylistOfArraylist

        ArrayList<t_fraction> arlFractions = new ArrayList<t_fraction>();

        boolean bFractionBeginningState = true;
        boolean bNextFraction = false;
        String strFraction = "";
        String strNumerator = "";
        String strDenominator = "";

        int nNumeratorBeginningIndex = 0;
        int nNumeratorEndingIndex = 0;
        int nDenominatorBeginningIndex = 0;
        int nDenominatorEndingIndex = 0;

        for (int nCharacter = 0; nCharacter < this.strInputEquation.length() ; nCharacter++) {

            if (Character.isDigit(this.strInputEquation.charAt(nCharacter))) {

                nNumeratorBeginningIndex = nCharacter;

                while (this.strInputEquation.charAt(nCharacter) != '/') {

                    nCharacter++;
                }

                if (this.strInputEquation.charAt(nCharacter) == '/') {

                    nNumeratorEndingIndex = nCharacter;
                }

                while (!Character.isDigit(this.strInputEquation.charAt(nCharacter))) {

                    nCharacter++;
                }

                if (Character.isDigit(this.strInputEquation.charAt(nCharacter))) {

                    nDenominatorBeginningIndex = nCharacter;
                };

                while (Character.isDigit(this.strInputEquation.charAt(nCharacter))) {

                    nCharacter++;
                }

                if (!Character.isDigit(this.strInputEquation.charAt(nCharacter))) {

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

    public ArrayList<ArrayList<Integer>> arithmeticOperandProritizer (String strInputEquation) {

        int nArithmeticOperandsNumber = 0;

        ArrayList<Integer> MultiplyOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> DivideOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> AddOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> SubtractOperandPositions = new ArrayList<Integer>();

        int nOperandSequence = 1;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            switch (chCurrentCharacter) {

                case '+':
                    AddOperandPositions.add(nOperandSequence);
                    break;
                case '-':
                    SubtractOperandPositions.add(nOperandSequence);
                    break;
                case '*':
                    MultiplyOperandPositions.add(nOperandSequence);
                    break;
                case '/':
                    DivideOperandPositions.add(nOperandSequence);
                    break;
                default:
                    break;
            }

            nOperandSequence++;
        }

        ArrayList<ArrayList<Integer>> arlOperandsPriority = new ArrayList<>();
        arlOperandsPriority.add(MultiplyOperandPositions);
        arlOperandsPriority.add(DivideOperandPositions);
        arlOperandsPriority.add(AddOperandPositions);
        arlOperandsPriority.add(SubtractOperandPositions);

        return arlOperandsPriority;
    }


    public int arithmeticOperandsNumber (String strInputEquation) {

        int nArithmeticOperandsNumber = 0;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            switch (chCurrentCharacter) {

                case '+':
                    nArithmeticOperandsNumber++;
                    break;
                case '-':
                    nArithmeticOperandsNumber++;
                    break;
                case '*':
                    nArithmeticOperandsNumber++;
                    break;
                case '/':
                    nArithmeticOperandsNumber++;
                    break;
                default:
                    break;
            }
        }

        return nArithmeticOperandsNumber;
    }

    public boolean checkBracketExistance(String strInputEquation) {

        boolean bBracketsExist = false;
        int nBracketSum = 0;

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            if ((chCurrentCharacter == '(') || (chCurrentCharacter == ')')) {
                nBracketSum++;
            }
        }

        if (nBracketSum % 2 != 0) {

            System.out.println("Error, uneven number of brackets!");
        }
        else if (nBracketSum > 0) {

            return true;
        }

        return false;
    }

    public int[] innerBracketPositions (String strInputEquation) {

        for (int nCharacter = 0; nCharacter < strInputEquation.length(); nCharacter++) {

            char chCurrentChar = strInputEquation.charAt(nCharacter);

            int nLastOpenBracketIndex = 0;
            int nLastCloseBracketIndex = 0;

            boolean bFoundOpenBracket = false;
            boolean bFoundCloseBracket = false;

            if (chCurrentChar == '(') {

                nLastOpenBracketIndex = nCharacter;
            }
            if (chCurrentChar == ')') {

                nLastCloseBracketIndex = nCharacter;
            }

            return new int[]{nLastOpenBracketIndex, nLastCloseBracketIndex};
        }
        return new int[1]; //TODO: Dummy auswechseln
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

    public void testFunction (String strUserInput) {

        String strPlus = "+";
        String strMinus = "-";
        String strDivide = ":";
        String strMultiply = "*";

        char chArithmeticOperation = ' ';
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
        
    }

    



}
