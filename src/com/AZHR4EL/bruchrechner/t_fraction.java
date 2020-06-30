package com.AZHR4EL.bruchrechner;

import java.util.ArrayList;

public class t_fraction {

    // VARIABLES ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private int nNumerator = 0;
    private int nDenominator = 0;

    // KONSTRUKTOR /////////////////////////////////////////////////////////////////////////////////////////////////////

    t_fraction () {
        this.nNumerator = 0;
        this.nDenominator = 0;
    }

    t_fraction (int nNumerator, int nDenominator) {
        this.nNumerator = nNumerator;
        this.nDenominator = nDenominator;
    }

    // METHODS /////////////////////////////////////////////////////////////////////////////////////////////////////////

    // SETTER //////////////////////////////////////////////////////
    public void setNumerator(int nNumerator) {
        this.nNumerator = nNumerator;
    }
    public void setDenominator(int nDenominator) {
        this.nDenominator = nDenominator;
    }

////// GETTER //////////////////////////////////////////////////////

    public int getnNumerator() {
        return nNumerator;
    }

    public int getnDenominator() {
        return nDenominator;
    }

////// STRING METHODS //////////////////////////////////////////////

    public String getFractionString () {

        return this.nNumerator + "/" + this.nDenominator;
    }

////// ARITHMETIC METHODS //////////////////////////////////////////

    public void fractionDivision(t_fraction tFraction0, t_fraction tFraction1) {

        boolean bNegativeFraction = false;

        if (tFraction0.getnNumerator() < 0 || tFraction1.getnNumerator() < 0) { bNegativeFraction = true; }
        if (tFraction0.getnNumerator() < 0 && tFraction1.getnNumerator() < 0) { bNegativeFraction = false; }


        this.nNumerator = Math.abs(tFraction0.getnNumerator()) * Math.abs(tFraction1.getnDenominator());
        this.nDenominator = Math.abs(tFraction0.getnDenominator()) * Math.abs(tFraction1.getnNumerator());

        if (bNegativeFraction) { this.nNumerator *= -1; }
        reduceFraction();
        System.out.println(tFraction0.getnNumerator() + "/" + tFraction0.getnDenominator() + " : " + tFraction1.getnNumerator() + "/"
                + tFraction1.getnDenominator() + " = " + this.getnNumerator() + "/" + this.getnDenominator());
    }

    public void fractionMultiplication(t_fraction tFraction0, t_fraction tFraction1) {

        this.nNumerator = tFraction0.getnNumerator() * tFraction1.getnNumerator();
        this.nDenominator = tFraction0.getnDenominator() * tFraction1.getnDenominator();

        reduceFraction();
        System.out.println(tFraction0.getnNumerator() + "/" + tFraction0.getnDenominator() + " * " + tFraction1.getnNumerator() + "/"
                + tFraction1.getnDenominator() + " = " + this.getnNumerator() + "/" + this.getnDenominator());
    }

    public void fractionAddition(t_fraction tFraction0, t_fraction tFraction1) {

        t_fraction[] arrFractionArray = bringToSameDenominator(tFraction0, tFraction1);

        tFraction0 = arrFractionArray[0];
        tFraction1 = arrFractionArray[1];

        // Addition of Fractions   //////////////////////////////////////////////////////////////
        int nResultFractionNumerator = tFraction0.getnNumerator() + tFraction1.getnNumerator();
        int nResultFractionDenominator = tFraction0.getnDenominator();

        t_fraction tResultFraction = new t_fraction(nResultFractionNumerator, nResultFractionDenominator);

        // Setting this Objects Numerator / Denominator //////////////////////////////////////////
        this.setNumerator(tResultFraction.getnNumerator());
        this.setDenominator(tResultFraction.getnDenominator());

        // Reducing the Fraction for an adequate Result //////////////////////////////////////////
        reduceFraction();
        System.out.println(tFraction0.getnNumerator() + "/" + tFraction0.getnDenominator() + " + " + tFraction1.getnNumerator() + "/"
                + tFraction1.getnDenominator() + " = " + this.getnNumerator() + "/" + this.getnDenominator());
    }

    public void fractionSubtraction(t_fraction tFraction0, t_fraction tFraction1) {

        t_fraction[] arrFractionArray = bringToSameDenominator(tFraction0, tFraction1);

        tFraction0 = arrFractionArray[0];
        tFraction1 = arrFractionArray[1];

        // Subtraction of Fractions   ////////////////////////////////////////////////////////////
        int nResultFractionNumerator = tFraction0.getnNumerator() - tFraction1.getnNumerator();
        int nResultFractionDenominator = tFraction0.getnDenominator();

        t_fraction tResultFraction = new t_fraction(nResultFractionNumerator, nResultFractionDenominator);

        // Setting this Objects Numerator / Denominator //////////////////////////////////////////
        this.setNumerator(tResultFraction.getnNumerator());
        this.setDenominator(tResultFraction.getnDenominator());

        // Reducing the Fraction for an adequate Result //////////////////////////////////////////
        reduceFraction();
        System.out.println(tFraction0.getnNumerator() + "/" + tFraction0.getnDenominator() + " - " + tFraction1.getnNumerator() + "/"
                + tFraction1.getnDenominator() + " = " + this.getnNumerator() + "/" + this.getnDenominator());
    }

////// ADDITIONAL METHODS /////////////////////////////////////////////////////////////////////////

    public t_fraction[] bringToSameDenominator (t_fraction tFraction0, t_fraction tFraction1) {

        //int nLeastCommonMultiple = leastCommonMultiple(tFraction0, tFraction1);  //TODO: Korrektur, funktioniert nicht korrekt

        int nLeastCommonMultiple = tFraction0.getnDenominator() * tFraction1.getnDenominator();


        tFraction0.setNumerator( tFraction0.getnNumerator() * ( nLeastCommonMultiple / tFraction0.getnDenominator() ));
        tFraction0.setDenominator(nLeastCommonMultiple);

        tFraction1.setNumerator( tFraction1.getnNumerator() * ( nLeastCommonMultiple / tFraction1.getnDenominator() ));
        tFraction1.setDenominator(nLeastCommonMultiple);

        return new t_fraction[]{tFraction0, tFraction1};
    }

    public int leastCommonMultiple(t_fraction tFraction0, t_fraction tFraction1) {

        int nLeastCommonMultiple = tFraction0.getnDenominator();

        if (tFraction1.getnDenominator() > tFraction0.getnDenominator()) {

            nLeastCommonMultiple = tFraction1.getnDenominator();
        }

        while (!((nLeastCommonMultiple % tFraction0.getnNumerator() != 0)
                || (nLeastCommonMultiple % tFraction1.getnNumerator() != 0))) {

            nLeastCommonMultiple++;
        }

        return nLeastCommonMultiple;
    }

//    public int greatestCommonDivisor (t_fraction tFraction0, t_fraction tFraction1) {
//
//        boolean greatestCommonDivisor = false;
//        int nCommonDivider = 0;
//
//        if (tFraction0.getnDenominator() < tFraction1.getnDenominator()) {
//
//            nCommonDivider = tFraction0.getnDenominator() + 1;
//
//        } else {
//
//            nCommonDivider = tFraction1.getnDenominator() + 1;
//
//        }
//
//        while (!greatestCommonDivisor) {
//
//            nCommonDivider--;
//
//            if ((tFraction0.getnDenominator() % nCommonDivider == 0) && (tFraction1.getnDenominator() % nCommonDivider == 0)) {
//
//                greatestCommonDivisor = true;
//            }
//        }
//        return nCommonDivider;
//    }
//
//    public t_fraction[] expandFractionByGCD(t_fraction tFraction0, t_fraction tFraction1, int nGreatestCommonDivisor) {
//
//        if ((tFraction0.getnNumerator() % nGreatestCommonDivisor == 0)
//                && (tFraction1.getnNumerator() % nGreatestCommonDivisor == 0)) {
//
//            tFraction0.setNumerator(tFraction0.getnNumerator() * (tFraction0.getnDenominator() / nGreatestCommonDivisor));
//            tFraction0.setDenominator(nGreatestCommonDivisor);
//
//            tFraction1.setNumerator(tFraction1.getnNumerator() * (tFraction1.getnDenominator() / nGreatestCommonDivisor));
//            tFraction1.setDenominator(nGreatestCommonDivisor);
//        }
//
//        return new t_fraction[]{tFraction0, tFraction1};
//    }
//
//    public t_fraction[] expandFraction (t_fraction tFraction0, t_fraction tFraction1) {
//
//        int nExpandedFraction = tFraction0.getnDenominator() * tFraction1.getnDenominator();
//
//        tFraction0.setNumerator( tFraction0.getnNumerator() * (nExpandedFraction / tFraction0.getnDenominator()) );
//        tFraction0.setDenominator(nExpandedFraction);
//
//        tFraction1.setNumerator( tFraction1.getnNumerator() * (nExpandedFraction / tFraction1.getnDenominator()) );
//        tFraction1.setDenominator(nExpandedFraction);
//
//        return new t_fraction[] {tFraction0, tFraction1};
//    }

    public void reduceFraction() {

        int nReduceFactor = 1;

        while (true) {


            for (int nFactorSearch = 1; nFactorSearch <= this.nDenominator; nFactorSearch++) {

                if ((this.nNumerator % (nFactorSearch) == 0)
                        && (this.nDenominator % (nFactorSearch) == 0)) {

                    nReduceFactor = nFactorSearch;
                    //System.out.println("Test: " + this.nNumerator + " | " + this.nDenominator + " | " + nReduceFactor);
                }
            }
            //System.out.println("Reducer: " + this.nNumerator + " | " + this.nDenominator + " | " + nReduceFactor);
            if (nReduceFactor <= 1) { break; }

            this.nNumerator = this.nNumerator / nReduceFactor;
            this.nDenominator = this.nDenominator / nReduceFactor;
        }

    }

    public String printFractionReturner () {

        int nWholeNumberExists = 0;
        int nWholeNumber = 0;

        String strFractionPrintReturner = "";
        String strWholeNegativeSign = "";
        String strFractionNegativeSign = "";

        if (this.nNumerator < 0) {

            strWholeNegativeSign = "-";
            strFractionNegativeSign = "-";
        }

        if (this.nNumerator >= this.nDenominator) {

            nWholeNumberExists = 1;
            nWholeNumber = this.nNumerator / this.nDenominator;
            int nNumeratorRest = this.nNumerator - (nWholeNumber * this.nDenominator);

            strFractionPrintReturner = strWholeNegativeSign + nWholeNumber + " + "
                    + strFractionNegativeSign + nNumeratorRest + "/" + this.nDenominator;
        }
        else {

            strFractionPrintReturner = this.nNumerator + "/" + this.nDenominator;
        }

        return strFractionPrintReturner;
    }
}
