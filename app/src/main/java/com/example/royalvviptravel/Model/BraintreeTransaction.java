package com.example.royalvviptravel.Model;

public class BraintreeTransaction {
    private boolean success;
    private Transaction transaction;

    public BraintreeTransaction() {
    }

    public BraintreeTransaction(boolean success, Transaction transaction) {
        this.success = success;
        this.transaction = transaction;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
