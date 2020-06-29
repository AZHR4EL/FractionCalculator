package com.AZHR4EL.bruchrechner;

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

    // GETTER //////////////////////////////////////////////////////

    public int getnNumerator() {
        return nNumerator;
    }

    public int getnDenominator() {
        return nDenominator;
    }

    // STRING METHODS //////////////////////////////////////////////

    public String getFractionString () {

        return this.nNumerator + "/" + this.nDenominator;
    }

    // ARITHMETIC METHODS //////////////////////////////////////////

    public void fractionAddition(t_fraction tFraction0, t_fraction tFraction1) {

        int nGreatestCommonDivisor = greatestCommonDivisor(tFraction0, tFraction1);

        if (nGreatestCommonDivisor <= 1) { // If there is no GCD above 1, expanding Fractions

            t_fraction arrFractionArray[] = expandFraction(tFraction0, tFraction1);

            tFraction0 = arrFractionArray[0];
            tFraction1 = arrFractionArray[1];

        } else { // If there is a GCD above 1, using it

            int nFraction0Multiplicator = tFraction0.getnDenominator() / nGreatestCommonDivisor;
            tFraction0.setNumerator(tFraction0.getnNumerator() * nFraction0Multiplicator);
            tFraction0.setDenominator(nGreatestCommonDivisor);

            int nFraction1Multiplicator = tFraction1.getnDenominator() / nGreatestCommonDivisor;
            tFraction1.setNumerator(tFraction1.getnNumerator() * nFraction0Multiplicator);
            tFraction1.setDenominator(nGreatestCommonDivisor);
        }

        //////////////////////////////////////////////////////////////////////////////////////////
        // Addition of Fractions                                                                //
        int nResultFractionNumerator = tFraction0.nNumerator + tFraction1.getnNumerator();

        // Failure check
        if (tFraction0.getnDenominator() != tFraction1.getnDenominator()) {

            System.out.println("Program error, unequal denominators after addition/subtraction!");

            t_fraction tNullFraction = new t_fraction();
            return;
        }

        int nResultFractionDenominator = tFraction0.getnDenominator();

        t_fraction tResultFraction = new t_fraction(nResultFractionNumerator, nResultFractionDenominator);

        //                                                                                      //
        //////////////////////////////////////////////////////////////////////////////////////////

        reduceFraction(tResultFraction); // Reducing the Fraction for an adequate Result

        this.setNumerator(tResultFraction.getnNumerator());
        this.setDenominator(tResultFraction.getnDenominator());
    }

    public void fractionSubtraction(t_fraction tFraction0, t_fraction tFraction1) {

        int nGreatestCommonDivisor = greatestCommonDivisor(tFraction0, tFraction1);

        if (nGreatestCommonDivisor <= 1) {

            t_fraction arrFractionArray[] = expandFraction(tFraction0, tFraction1);

            tFraction0 = arrFractionArray[0];
            tFraction1 = arrFractionArray[1];

        } else {

            int nFraction0Multiplicator = tFraction0.getnDenominator() / nGreatestCommonDivisor;
            tFraction0.setNumerator(tFraction0.getnNumerator() * nFraction0Multiplicator);
            tFraction0.setDenominator(nGreatestCommonDivisor);

            int nFraction1Multiplicator = tFraction1.getnDenominator() / nGreatestCommonDivisor;
            tFraction1.setNumerator(tFraction1.getnNumerator() * nFraction0Multiplicator);
            tFraction1.setDenominator(nGreatestCommonDivisor);
        }

        //////////////////////////////////////////////////////////////////////////////////////////
        // Addition of Fractions                                                                //
        int nResultFractionNumerator = tFraction0.nNumerator - tFraction1.getnNumerator();

        // Failure check
        if (tFraction0.getnDenominator() != tFraction1.getnDenominator()) {

            System.out.println("Program error, unequal denominators after addition/subtraction!");

            t_fraction tNullFraction = new t_fraction(0,0);
            return;
        }

        int nResultFractionDenominator = tFraction0.getnDenominator();

        t_fraction tResultFraction = new t_fraction(nResultFractionNumerator, nResultFractionDenominator);

        //                                                                                      //
        //////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////
        // Fraction reduction                                                                   //

        reduceFraction(tResultFraction);

        //                                                                                      //
        //////////////////////////////////////////////////////////////////////////////////////////

        this.setNumerator(tResultFraction.getnNumerator());
        this.setDenominator(tResultFraction.getnDenominator());
    }

    public void fractionMultiplication(t_fraction tFraction0, t_fraction tFraction1) {

        this.nNumerator = tFraction0.getnNumerator() * tFraction1.getnNumerator();
        this.nDenominator = tFraction0.getnDenominator() * tFraction1.getnDenominator();
    }

    public void fractionDivision(t_fraction tFraction0, t_fraction tFraction1) {

        this.nNumerator = tFraction0.getnNumerator() * tFraction1.getnDenominator();
        this.nDenominator = tFraction0.getnDenominator() * tFraction1.getnNumerator();
    }
    
    public int greatestCommonDivisor (t_fraction tFraction0, t_fraction tFraction1) {

        boolean greatestCommonDivisor = false;
        int nCommonDivider = 0;

        if (tFraction0.getnDenominator() < tFraction1.getnDenominator()) {

            nCommonDivider = tFraction0.getnDenominator() + 1;

        } else {

            nCommonDivider = tFraction1.getnDenominator() + 1;

        }

        while (!greatestCommonDivisor) {

            nCommonDivider--;

            if ((tFraction0.getnDenominator() % nCommonDivider == 0) && (tFraction1.getnDenominator() % nCommonDivider == 0)) {

                greatestCommonDivisor = true;
            }
        }

        return nCommonDivider;
    }

    public t_fraction[] expandFraction (t_fraction tFraction0, t_fraction tFraction1) {

        int nExpandedFraction = tFraction0.getnDenominator() * tFraction1.getnDenominator();

        tFraction0.setNumerator( tFraction0.getnNumerator() * (nExpandedFraction / tFraction0.getnDenominator()) );
        tFraction0.setDenominator(nExpandedFraction);

        tFraction1.setNumerator( tFraction1.getnNumerator() * (nExpandedFraction / tFraction1.getnDenominator()) );
        tFraction1.setDenominator(nExpandedFraction);


        return new t_fraction[] {tFraction0, tFraction1};
    }

    public t_fraction reduceFraction(t_fraction tFraction) {

        for (int nReduceFactor = 2; nReduceFactor <= tFraction.getnDenominator(); nReduceFactor++) {

            if ((tFraction.getnNumerator() % nReduceFactor == 0) &&
                    (tFraction.getnDenominator() % nReduceFactor == 0)) {

                int nNumeratorMultiplicator = tFraction.getnDenominator() / nReduceFactor;

                tFraction.setNumerator(tFraction.getnNumerator() / nNumeratorMultiplicator);
                tFraction.setDenominator(nReduceFactor);
            }
        }

        return tFraction;
    }

    public String printFractionReturner () {

        int nWholeNumberExists = 0;
        int nWholeNumber = 0;
        String strFractionPrintReturner = "";

        if (this.nNumerator >= this.nDenominator) {

            nWholeNumberExists = 1;
            nWholeNumber = this.nNumerator / this.nDenominator;
            int nNumeratorRest = this.nNumerator - (nWholeNumber * this.nDenominator);

            strFractionPrintReturner = nWholeNumber + " + " + nNumeratorRest + "/" + this.nDenominator;
        }
        else {

            strFractionPrintReturner = this.nNumerator + "/" + this.nDenominator;
        }

        return strFractionPrintReturner;
    }
}
