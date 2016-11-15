package com.mrkbrz.pojo;

/**
 * Created by Mirko Manuel Burzzi on 11/14/16.
 * This Java Bean wraps all the property that are in the input file
 */
public class Item {

    private Number units;

    private Number price;

    private String description;

    private Boolean imported;

    private Boolean hasExtraTax;

    public Number getUnits() {
        return units;
    }

    public void setUnits(Number units) {
        this.units = units;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getImported() {
        return imported;
    }

    public Boolean isImported(){
        return getImported();
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    public Boolean getHasExtraTax() {
        return hasExtraTax;
    }

    public void setHasExtraTax(Boolean hasExtraTax) {
        this.hasExtraTax = hasExtraTax;
    }

    @Override
    public String toString() {
        final StringBuilder text = new StringBuilder();
        text.append(units).append(" ");
        if(imported) text.append("imported ");
        text.append(description).append(" : ").append(price);
        return text.toString();
    }
}
