package com.sibirenergo.bonus.service;

/**
 * Created by IntelliJ IDEA.
 * User: Nikita
 * Date: 21.09.2010
 * Time: 22:03:22
 * ������������ � ������ ���� �� ����� ������������ ������� ��� ��������
 */
public class InsufficientFundsException extends Exception {
    private double cardBalance;

    public InsufficientFundsException(String message, double cardBalance) {
        super(message);
        this.cardBalance = cardBalance;
    }

    public double getCardBalance() {
        return cardBalance;
    }
}
