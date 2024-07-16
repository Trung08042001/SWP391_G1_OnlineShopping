/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nitro
 */
public class OrderDetail {

    private int orderDetailID;
    private int orderID;
    private int productID;
    private int oquantity;
    private double oprice;
    private int colorId;
    private int sizeId;
    private Order order;
    private Products product;
    private Shipper shipper;
    private int reviewStatus;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailID, int orderID, int productID, int oquantity, double oprice, Order order, Products product, Shipper shipper, int reviewStatus) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.oquantity = oquantity;
        this.oprice = oprice;
        this.order = order;
        this.product = product;
        this.shipper = shipper;
        this.reviewStatus = reviewStatus;
    }

    public OrderDetail(int orderDetailID, int orderID, int productID, int oquantity, double oprice, int colorId, int sizeId, Order order, Products product, Shipper shipper, int reviewStatus) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.oquantity = oquantity;
        this.oprice = oprice;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.order = order;
        this.product = product;
        this.shipper = shipper;
        this.reviewStatus = reviewStatus;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }
    
    

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOquantity() {
        return oquantity;
    }

    public void setOquantity(int oquantity) {
        this.oquantity = oquantity;
    }

    public double getOprice() {
        return oprice;
    }

    public void setOprice(double oprice) {
        this.oprice = oprice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailID=" + orderDetailID + ", orderID=" + orderID + ", productID=" + productID + ", oquantity=" + oquantity + ", oprice=" + oprice + ", order=" + order + ", product=" + product + ", shipper=" + shipper + ", reviewStatus=" + reviewStatus + '}';
    }

}
