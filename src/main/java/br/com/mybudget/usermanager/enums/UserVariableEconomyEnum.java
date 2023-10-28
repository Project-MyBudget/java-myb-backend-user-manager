package br.com.mybudget.usermanager.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum UserVariableEconomyEnum {

    PERCENT_30(0.30),
    PERCENT_20(0.20),
    PERCENT_15(0.15);

    private double maxEconomyVariable;

    public static double calcMaxEconomyValue(boolean hasFamily, double salary) {
        if (hasFamily) {
            return salary >= 10000 ? PERCENT_20.maxEconomyVariable : PERCENT_15.maxEconomyVariable;
        } else {
            return PERCENT_30.maxEconomyVariable;
        }
    }
}
