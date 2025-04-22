package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "brewing_protocol")
public class BrewingProtocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchNr;

    @Temporal(TemporalType.DATE)
    private Date brewingDate;

    private Float originalGravity;

    private String fermentationTankNumber;
    private Float fermentationTankTemperature;

    private String hopsType;
    private Float hopsAmount;
    private Integer hopsTime;

    private String maltType;
    private Float maltAmount;

    private String yeastType;
    private Float yeastAmount;

    private String waterTreatmentType;
    private Float waterTreatmentAmount;

    private Float temperatureMash;
    private Float temperatureMashOut;
    private Float temperatureMashIn;

    private Float waterQuantityMainCast;
    private Float waterQuantitySparge1;
    private Float waterQuantitySparge2;

    private String furtherAdditionType;
    private Float furtherAdditionAmount;
    private Integer furtherAdditionTime;
    
    @ManyToOne
    private Recipe recipe;

    // Getters and Setters

    public Integer getBatchNr() {
        return batchNr;
    }

    public void setBatchNr(Integer batchNr) {
        this.batchNr = batchNr;
    }

    public Date getBrewingDate() {
        return brewingDate;
    }

    public void setBrewingDate(Date brewingDate) {
        this.brewingDate = brewingDate;
    }

    public Float getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(Float originalGravity) {
        this.originalGravity = originalGravity;
    }


    public String getFermentationTankNumber() {
        return fermentationTankNumber;
    }

    public void setFermentationTankNumber(String fermentationTankNumber) {
        this.fermentationTankNumber = fermentationTankNumber;
    }

    public Float getFermentationTankTemperature() {
        return fermentationTankTemperature;
    }

    public void setFermentationTankTemperature(Float fermentationTankTemperature) {
        this.fermentationTankTemperature = fermentationTankTemperature;
    }

    public String getHopsType() {
        return hopsType;
    }

    public void setHopsType(String hopsType) {
        this.hopsType = hopsType;
    }

    public Float getHopsAmount() {
        return hopsAmount;
    }

    public void setHopsAmount(Float hopsAmount) {
        this.hopsAmount = hopsAmount;
    }

    public Integer getHopsTime() {
        return hopsTime;
    }

    public void setHopsTime(Integer hopsTime) {
        this.hopsTime = hopsTime;
    }

    public String getMaltType() {
        return maltType;
    }

    public void setMaltType(String maltType) {
        this.maltType = maltType;
    }

    public Float getMaltAmount() {
        return maltAmount;
    }

    public void setMaltAmount(Float maltAmount) {
        this.maltAmount = maltAmount;
    }

    public String getYeastType() {
        return yeastType;
    }

    public void setYeastType(String yeastType) {
        this.yeastType = yeastType;
    }

    public Float getYeastAmount() {
        return yeastAmount;
    }

    public void setYeastAmount(Float yeastAmount) {
        this.yeastAmount = yeastAmount;
    }

    public String getWaterTreatmentType() {
        return waterTreatmentType;
    }

    public void setWaterTreatmentType(String waterTreatmentType) {
        this.waterTreatmentType = waterTreatmentType;
    }

    public Float getWaterTreatmentAmount() {
        return waterTreatmentAmount;
    }

    public void setWaterTreatmentAmount(Float waterTreatmentAmount) {
        this.waterTreatmentAmount = waterTreatmentAmount;
    }

    public Float getTemperatureMash() {
        return temperatureMash;
    }

    public void setTemperatureMash(Float temperatureMash) {
        this.temperatureMash = temperatureMash;
    }

    public Float getTemperatureMashOut() {
        return temperatureMashOut;
    }

    public void setTemperatureMashOut(Float temperatureMashOut) {
        this.temperatureMashOut = temperatureMashOut;
    }

    public Float getTemperatureMashIn() {
        return temperatureMashIn;
    }

    public void setTemperatureMashIn(Float temperatureMashIn) {
        this.temperatureMashIn = temperatureMashIn;
    }

    public Float getWaterQuantityMainCast() {
        return waterQuantityMainCast;
    }

    public void setWaterQuantityMainCast(Float waterQuantityMainCast) {
        this.waterQuantityMainCast = waterQuantityMainCast;
    }

    public Float getWaterQuantitySparge1() {
        return waterQuantitySparge1;
    }

    public void setWaterQuantitySparge1(Float waterQuantitySparge1) {
        this.waterQuantitySparge1 = waterQuantitySparge1;
    }

    public Float getWaterQuantitySparge2() {
        return waterQuantitySparge2;
    }

    public void setWaterQuantitySparge2(Float waterQuantitySparge2) {
        this.waterQuantitySparge2 = waterQuantitySparge2;
    }

    public String getFurtherAdditionType() {
        return furtherAdditionType;
    }

    public void setFurtherAdditionType(String furtherAdditionType) {
        this.furtherAdditionType = furtherAdditionType;
    }

    public Float getFurtherAdditionAmount() {
        return furtherAdditionAmount;
    }

    public void setFurtherAdditionAmount(Float furtherAdditionAmount) {
        this.furtherAdditionAmount = furtherAdditionAmount;
    }

    public Integer getFurtherAdditionTime() {
        return furtherAdditionTime;
    }

    public void setFurtherAdditionTime(Integer furtherAdditionTime) {
        this.furtherAdditionTime = furtherAdditionTime;
    }
    public Recipe getRecipe() {
    return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
