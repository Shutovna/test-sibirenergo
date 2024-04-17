package com.sibirenergo.bonus.domain;



/**
 * User: Nikita Shutov
 * Date: 18.09.2010
 * Time: 14:47:52
 * Операция, соответствующая совершённой покупке
 */
public class Purchase extends Operation {
    /**
     * Сумма покупки
     */
    private double amount;
    /**
     * Сумма начисленных бонусов
     */
    private double award;

    public Purchase() {
    }

    public Purchase(String externalId, Card card, PointOfSale pointOfSale, double amount, double award) {
        super(externalId, card, pointOfSale);
        this.amount = amount;
        this.award = award;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }
}
