package com.sibirenergo.bonus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.regex.Pattern;

/**
 * User: Nikita Shutov
 * Date: 18.09.2010
 * Time: 14:48:11
 * Учётная запись карты
 */
public class Card {
    /**
     * Уникальный номер карты в формате DDDD-DDDD-DDDD
     */
    private String number;

    /**
     * Текущий баланс по карте
     */
    private double balance;

    public Card(String number) {
        setNumber(number);
    }

    public Card() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        final String regex = "\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d";
        if (Pattern.matches(regex, number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException(String.format("Card number %s doesnt matches %s", number, regex));
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return True if objects are equal; otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Card))
            return false;

        Card that = (Card) obj;

        return new EqualsBuilder()
                .append(this.getNumber(), that.getNumber())
                .isEquals();

    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value.
     */
    @Override
    public int hashCode() {
        return new Double(number).hashCode();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {").append(newLine);
        result.append(" Number: ").append(number).append(newLine);
        result.append(" Balance: ").append(balance).append(newLine);
        result.append("}");

        return result.toString();
    }
}
