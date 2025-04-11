package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders") // "order" is a reserved keyword in many DBs
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private String beerName; // This should match InventoryCategoryName
    private Integer amount;

    @ManyToOne
    private Customer customer;

    // Getters and Setters

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
