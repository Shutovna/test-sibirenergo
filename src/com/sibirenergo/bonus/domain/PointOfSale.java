package com.sibirenergo.bonus.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * User: Nikita Shutov
 * Date: 18.09.2010
 * Time: 14:48:17
 * Место совершения операций
 */
public class PointOfSale {
    /**
     * Название места
     */
    private String name;

    public PointOfSale() {
    }

    public PointOfSale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        if (!(obj instanceof PointOfSale))
            return false;

        PointOfSale that = (PointOfSale) obj;

        return new EqualsBuilder()
                .append(this.getName(), that.getName())
                .isEquals();

    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value.
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : super.hashCode();
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
        result.append(" Name: ").append(name).append(newLine);
        result.append("}");

        return result.toString();
    }
}
