package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryID;

    private Integer inventoryAmount;
    private String inventoryCategoryName;

    // Getters and Setters

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
}
