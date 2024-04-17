package com.sibirenergo.bonus.domain;



/**
 * User: Nikita Shutov
 * Date: 18.09.2010
 * Time: 14:48:00
 * ��������, ��������������� ������ ��������
 */
public class UseBonus extends Operation {
    /**
     * ����� ��������� � ����� ������� 
     */
    private double amount;

    public UseBonus() {
    }

    public UseBonus(String externalId, Card card, PointOfSale pointOfSale, double amount) {
        super(externalId, card, pointOfSale);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    
}
