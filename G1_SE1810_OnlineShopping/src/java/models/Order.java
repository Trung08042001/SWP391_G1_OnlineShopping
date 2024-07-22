/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author khanh cu be
 */
public class Order {
     private int orderID;
    private Date date;
    private Account account;
    private Double totalMoney;
    private int status;
    private Shipper shipper;
    private LocalDateTime confirmedDate;
    private LocalDateTime pickUpDate;
    private LocalDateTime deliveryDate;

    private Double shippingCost;
    private OrderDetail orderdetail;
    private String statusName;
    private String paymentMethod;
    private LocalDateTime payment_at;
    private int  paymentStatus;

    public Order() {
    }

    public Order(int orderID, Date date, Account account, Double totalMoney, int status, Shipper shipper, LocalDateTime confirmedDate, LocalDateTime pickUpDate, LocalDateTime deliveryDate, Double shippingCost, OrderDetail orderdetail, String statusName, String paymentMethod, LocalDateTime payment_at, int paymentStatus) {
        this.orderID = orderID;
        this.date = date;
        this.account = account;
        this.totalMoney = totalMoney;
        this.status = status;
        this.shipper = shipper;
        this.confirmedDate = confirmedDate;
        this.pickUpDate = pickUpDate;
        this.deliveryDate = deliveryDate;
        this.shippingCost = shippingCost;
        this.orderdetail = orderdetail;
        this.statusName = statusName;
        this.paymentMethod = paymentMethod;
        this.payment_at = payment_at;
        this.paymentStatus = paymentStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public LocalDateTime getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(LocalDateTime confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public OrderDetail getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(OrderDetail orderdetail) {
        this.orderdetail = orderdetail;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPayment_at() {
        return payment_at;
    }

    public void setPayment_at(LocalDateTime payment_at) {
        this.payment_at = payment_at;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", date=" + date + ", account=" + account + ", totalMoney=" + totalMoney + ", status=" + status + ", shipper=" + shipper + ", confirmedDate=" + confirmedDate + ", pickUpDate=" + pickUpDate + ", deliveryDate=" + deliveryDate + ", shippingCost=" + shippingCost + ", orderdetail=" + orderdetail + ", statusName=" + statusName + ", paymentMethod=" + paymentMethod + ", payment_at=" + payment_at + ", paymentStatus=" + paymentStatus + '}';
    }
    
    
}
