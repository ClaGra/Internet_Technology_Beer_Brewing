package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryID;

    private Integer inventoryAmount;
    private String inventoryCategoryName;
    private Integer batchNr;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    public Integer getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(Integer inventoryID) {
        this.inventoryID = inventoryID;
    }

    public Integer getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(Integer inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    public String getInventoryCategoryName() {
        return inventoryCategoryName;
    }

    public void setInventoryCategoryName(String inventoryCategoryName) {
        this.inventoryCategoryName = inventoryCategoryName;
    }

    public Integer getBatchNr() {
        return batchNr;
    }

    public void setBatchNr(Integer batchNr) {
        this.batchNr = batchNr;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}