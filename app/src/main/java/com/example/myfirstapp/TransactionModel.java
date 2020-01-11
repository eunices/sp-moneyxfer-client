package com.example.myfirstapp;

public class TransactionModel {
    private String fromID;
    private String toID;
    private Float amount;
    private String transactionDate;

    public TransactionModel(){}

    public TransactionModel(String fromID, String toID, Float amount, String transactionDate) {
        this.setFromID(fromID);
        this.setToID(toID);
        this.setAmount(amount);
        this.setTransactionDate(transactionDate);
    }


    public String getFromID() {
        return fromID;
    }

    public void setFromID(String fromID) {
        this.fromID = fromID;
    }

    public String getToID() {
        return toID;
    }

    public void setToID(String toID) {
        this.toID = toID;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
