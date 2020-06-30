package com.AZHR4EL.bruchrechner;

import java.util.ArrayList;

public class t_fraction {

////// VARIABLES ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private int nNumerator = 0; // This objects Numerator
    private int nDenominator = 0; // This objects Denominator

////// CONSTRUCTOR /////////////////////////////////////////////////////////////////////////////////////////////////////

    t_fraction () {
        this.nNumerator = 0;
        this.nDenominator = 0;
    }

    t_fraction (int nNumerator, int nDenominator) {
        this.nNumerator = nNumerator;
        this.nDenominator = nDenominator;
    }

////// METHODS /////////////////////////////////////////////////////////////////////////////////////////////////////////

////// SETTER //////////////////////////////////////////////////////
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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Will do a division of two given fraction, reduce the fraction and save it into the object
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        boolean bNegativeFraction = false;

        if (tFraction0.getnNumerator() < 0 || tFraction1.getnNumerator() < 0) { bNegativeFraction = true; }
        if (tFraction0.getnNumerator() < 0 && tFraction1.getnNumerator() < 0) { bNegativeFraction = false; }


        this.nNumerator = Math.abs(tFraction0.getnNumerator()) * Math.abs(tFraction1.getnDenominator());
        this.nDenominator = Math.abs(tFraction0.getnDenominator()) * Math.abs(tFraction1.getnNumerator());

        if (bNegativeFraction) { this.nNumerator *= -1; }
        reduceFraction();
    }

    public void fractionMultiplication(t_fraction tFraction0, t_fraction tFraction1) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Will do a multiplication of two given fraction, reduce the fraction and save it into the object
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.nNumerator = tFraction0.getnNumerator() * tFraction1.getnNumerator();
        this.nDenominator = tFraction0.getnDenominator() * tFraction1.getnDenominator();

        reduceFraction();
    }

    public void fractionAddition(t_fraction tFraction0, t_fraction tFraction1) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Will do an addition of two given fraction, reduce the fraction and save it into the object
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    }

    public void fractionSubtraction(t_fraction tFraction0, t_fraction tFraction1) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Will do a subtraction of two given fraction, reduce the fraction and save it into the object
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    }

////// ADDITIONAL METHODS /////////////////////////////////////////////////////////////////////////

    public t_fraction[] bringToSameDenominator (t_fraction tFraction0, t_fraction tFraction1) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Will bring two fractions to the same denominator and return these as Array
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int nLeastCommonMultiple = tFraction0.getnDenominator() * tFraction1.getnDenominator();


        tFraction0.setNumerator( tFraction0.getnNumerator() * ( nLeastCommonMultiple / tFraction0.getnDenominator() ));
        tFraction0.setDenominator(nLeastCommonMultiple);

        tFraction1.setNumerator( tFraction1.getnNumerator() * ( nLeastCommonMultiple / tFraction1.getnDenominator() ));
        tFraction1.setDenominator(nLeastCommonMultiple);

        return new t_fraction[]{tFraction0, tFraction1};
    }

    public void reduceFraction() {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Will reduce the fraction of this object to the smallest possible
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Will return the fraction of this object as string
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
