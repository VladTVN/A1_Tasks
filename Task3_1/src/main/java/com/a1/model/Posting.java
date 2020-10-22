package com.a1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String matDoc;
    private int item;
    private Date docDate;
    private Date pstngDate;
    private String materialDescription;
    private int quantity;
    private String bun;
    private double amountLC;
    private String crcy;
    private String userName;
    private boolean authorizedDelivery;

    public Posting(String matDoc, int item, Date docDate, Date pstng, String materialDescription, int quantity, String bun, double amountLC, String crcy, String userName) {
        this.matDoc = matDoc;
        this.item = item;
        this.docDate = docDate;
        this.pstngDate = pstng;
        this.materialDescription = materialDescription;
        this.quantity = quantity;
        this.bun = bun;
        this.amountLC = amountLC;
        this.crcy = crcy;
        this.userName = userName;
    }

    public Posting() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatDoc() {
        return matDoc;
    }

    public void setMatDoc(String matDoc) {
        this.matDoc = matDoc;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Date getPstng() {
        return pstngDate;
    }

    public void setPstng(Date pstng) {
        this.pstngDate = pstng;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBun() {
        return bun;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public double getAmountLC() {
        return amountLC;
    }

    public void setAmountLC(double amountLC) {
        this.amountLC = amountLC;
    }

    public String getCrcy() {
        return crcy;
    }

    public void setCrcy(String crcy) {
        this.crcy = crcy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAuthorizedDelivery() {
        return authorizedDelivery;
    }

    public void setAuthorizedDelivery(boolean authorizedDelivery) {
        this.authorizedDelivery = authorizedDelivery;
    }
}
