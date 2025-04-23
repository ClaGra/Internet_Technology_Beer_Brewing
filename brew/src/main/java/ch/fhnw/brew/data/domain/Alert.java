package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alertID;

    private String alertName;
    private String alertTrigger;

    public Integer getAlertID() {
        return alertID;
    }

    public void setAlertID(Integer alertID) {
        this.alertID = alertID;
    }

    public String getAlertName() {
        return alertName;
    }

    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    public String getAlertTrigger() {
        return alertTrigger;
    }

    public void setAlertTrigger(String alertTrigger) {
        this.alertTrigger = alertTrigger;
    }
}
