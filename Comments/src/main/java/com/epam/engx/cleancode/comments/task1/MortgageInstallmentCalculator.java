package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    public static double calculateMonthlyPayment(
            int principalAmount, int termInYears, double rateOfInterest) {

        validateVariables(principalAmount, termInYears, rateOfInterest);

        rateOfInterest /= 100.0;

        double termInMonths = termInYears * 12;

        if(rateOfInterest==0)
            return  principalAmount/termInMonths;

        double rateOfInterestMonthly = rateOfInterest / 12.0;

        double monthlyPayment = (principalAmount * rateOfInterestMonthly) / (1 - Math.pow(1 + rateOfInterestMonthly, -termInMonths));

        return monthlyPayment;
    }

    private static void validateVariables(int principalAmount, int termInYears, double rateOfInterest) throws InvalidInputException {
        if (isPrincipalAmountNegative(principalAmount) || isTermInYearsNegative(termInYears) || isRateOfInterestNegative(rateOfInterest)) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }

    private static boolean isPrincipalAmountNegative(int principalAmount){
        return principalAmount < 0;
    }

    private static boolean isTermInYearsNegative(int termInYears){
        return termInYears <= 0;
    }

    private static boolean isRateOfInterestNegative(double rateOfInterest){
        return rateOfInterest < 0;
    }
}
