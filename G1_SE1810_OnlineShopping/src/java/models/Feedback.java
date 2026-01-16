/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;

/**
 *
 * @author Nitro
 */
public class Feedback {

    private int feedbackID;
    private Account account;
    private Products products;
    private int rate;
    private String content;
    private LocalDateTime feedback_at;

    public Feedback() {
    }

    public Feedback(int feedbackID, Account account, Products products, int rate, String content, LocalDateTime feedback_at) {
        this.feedbackID = feedbackID;
        this.account = account;
        this.products = products;
        this.rate = rate;
        this.content = content;
        this.feedback_at = feedback_at;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getFeedback_at() {
        return feedback_at;
    }

    public void setFeedback_at(LocalDateTime feedback_at) {
        this.feedback_at = feedback_at;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackID=" + feedbackID + ", account=" + account + ", products=" + products + ", rate=" + rate + ", content=" + content + ", feedback_at=" + feedback_at + '}';
    }

}
