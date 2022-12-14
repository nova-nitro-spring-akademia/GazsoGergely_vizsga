package com.gergely.vizsga_0902.service;

import java.util.Calendar;

public class SimpleEstimatedValue implements EstimatedValueStrategy{
    @Override
    public int getEstimatedValue(Card card) {
        //        kártya becsült értéke = megjelenési évbeni érték * eltelt évek száma * 1,1;
        int estVal = (int) Math.round(card.getIssuePrice() * (Calendar.getInstance().get(Calendar.YEAR) - card.getIssueYear()) * 1.1);
        return estVal;
    }
}
