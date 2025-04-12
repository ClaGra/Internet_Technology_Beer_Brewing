package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private String beerName;
    private Integer amount;

    @ManyToOne
    private Customer customer;

    @ElementCollection
    private List<Integer> batchNumbers;

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

    public List<Integer> getBatchNumbers() {
        return batchNumbers;
    }

    public void setBatchNumbers(List<Integer> batchNumbers) {
        this.batchNumbers = batchNumbers;
    }
}