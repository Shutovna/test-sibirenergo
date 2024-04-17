package com.sibirenergo.bonus.domain;

import com.sibirenergo.bonus.domain.Card;
import com.sibirenergo.bonus.domain.PointOfSale;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Nikita Shutov
 * Date: 18.09.2010
 * Time: 14:47:44
 * Операция по карте
 */
public abstract class Operation {
    /**
     * Уникальный идентификатор операции, назначаемый извне
     */
    private String externalId;
    /**
     * Время совершения операции
     */
    private Date timestamp;
    /**
     * Карта, по которой совершается операция
     */
    private Card card;
    /**
     * Место совершения операции
     */
    private PointOfSale pointOfSale;

    public Operation() {
        this.timestamp = Calendar.getInstance().getTime();
    }

    protected Operation(String externalId, Card card, PointOfSale pointOfSale) {
        this.externalId = externalId;
        this.card = card;
        this.pointOfSale = pointOfSale;
        this.timestamp = Calendar.getInstance().getTime();
    }   

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public PointOfSale getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
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

        if (!(obj instanceof Operation))
            return false;

        Operation that = (Operation) obj;

        return new EqualsBuilder()
                .append(this.getExternalId(), that.getExternalId())
                .isEquals();
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value.
     */
    @Override
    public int hashCode() {
        return externalId != null ? externalId.hashCode() : super.hashCode();
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
        result.append(" ExternalId: ").append(externalId).append(newLine);
        result.append(" Timestamp: ").append(timestamp).append(newLine);
        result.append("}");

        return result.toString();
    }
}
