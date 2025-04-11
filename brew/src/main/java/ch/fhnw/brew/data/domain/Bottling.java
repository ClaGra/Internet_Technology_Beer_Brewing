package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bottling")
public class Bottling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bottlingID;

    @Temporal(TemporalType.DATE)
    private Date bottlingDate;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    private Integer amount;

    @ManyToOne
    private BrewingProtocol brewingProtocol;

    // Getters and Setters

    public Integer getBottlingID() {
        return bottlingID;
    }

    public void setBottlingID(Integer bottlingID) {
        this.bottlingID = bottlingID;
    }

    public Date getBottlingDate() {
        return bottlingDate;
    }

    public void setBottlingDate(Date bottlingDate) {
        this.bottlingDate = bottlingDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BrewingProtocol getBrewingProtocol() {
        return brewingProtocol;
    }

    public void setBrewingProtocol(BrewingProtocol brewingProtocol) {
        this.brewingProtocol = brewingProtocol;
    }
}
