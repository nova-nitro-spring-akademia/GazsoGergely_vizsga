package com.gergely.vizsga_0902.service;

import java.util.Calendar;

public class ScarcityEstimatedValue implements EstimatedValueStrategy{
    @Override
    public int getEstimatedValue(Card card) {
        int estVal = (int) Math.round(card.getIssuePrice() * (Calendar.getInstance().get(Calendar.YEAR) - card.getIssueYear()) * 1 + (1/100 * (100 - card.getPercent())));
        return estVal;
    }
}
