package com.AZHR4EL.bruchrechner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FractionPriorityResolver {

    String strInputEquation = "";


    public ArrayList<t_fraction> multipleFractionCalculator(String strInputEquation, ArrayList<ArrayList<Integer>> arlArraylistOfArraylist) {

        ArrayList<t_fraction> arlFractions = new ArrayList<t_fraction>();

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            String strFraction = "";
            String strNumerator = "";
            String strDenominator = "";

            if (chCurrentCharacter == '/') {

                int nCharacterCounter = nCharacter - 1;

                int nNumeratorBeginningIndex = -1;
                int nNumeratorEndingIndex = nCharacter - 1;

                int nDenominatorBeginningIndex = nCharacter + 1;
                int nDenominatorEndingIndex = -1;

                while ((strInputEquation.charAt(nCharacterCounter) != ' ') && (nCharacterCounter >= 0)) {

                    nNumeratorBeginningIndex = nCharacterCounter;
                    nCharacterCounter--;
                }

                while ((strInputEquation.charAt(nCharacterCounter) != ' ') && (nCharacterCounter < strInputEquation.length())) {

                    nDenominatorEndingIndex = nCharacterCounter;
                    nCharacterCounter++;
                }

                strNumerator = strInputEquation.substring(nNumeratorBeginningIndex, nNumeratorEndingIndex + 1);
                strDenominator = strInputEquation.substring(nDenominatorBeginningIndex, nDenominatorEndingIndex + 1);

                t_fraction tFraction = new t_fraction(Integer.parseInt(strNumerator), Integer.parseInt(strDenominator));
                arlFractions.add(tFraction);
            }
        }

        return arlFractions;
    }

    public ArrayList<ArrayList<Integer>> arithmeticOperandProritizer (String strInputEquation) {

        int nArithmeticOperandsNumber = 0;

        ArrayList<Integer> pointOperandPositions = new ArrayList<Integer>();
        ArrayList<Integer> lineOperandPositions = new ArrayList<Integer>();

        for (int nCharacter = 0; nCharacter < strInputEquation.length() ; nCharacter++) {

            char chCurrentCharacter = strInputEquation.charAt(nCharacter);

            switch (chCurrentCharacter) {

                case '+':
                    lineOperandPositions.add(nCharacter + 1);
                    break;
                case '-':
                    lineOperandPositions.add(nCharacter + 1);
                    break;
                case '*':
                    pointOperandPositions.add(nCharacter + 1);
                    break;
                case '/':
                    pointOperandPositions.add(nCharacter + 1);
                    break;
                default:
                    break;
            }
        }

        ArrayList<ArrayList<Integer>> arlOperandsPriority = new ArrayList<ArrayList<Integer>>();
        arlOperandsPriority.add(pointOperandPositions);
        arlOperandsPriority.add(lineOperandPositions);

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
