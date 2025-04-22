package ch.fhnw.brew.data.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItem {

    private String beerName;
    private Integer amount;
    private Integer batchNumber; // one batch number per item

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }
}
