/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author acer
 */
public class ProductDetail {
    private Products product;
    private int productID;
    private int sizeID;
    private int colorID;
    private int pdetailID;
    private int quantity;

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public int getPdetailID() {
        return pdetailID;
    }

    public void setPdetailID(int pdetailID) {
        this.pdetailID = pdetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDetail(Products product, int productID, int sizeID, int colorID, int pdetailID, int quantity) {
        this.product = product;
        this.productID = productID;
        this.sizeID = sizeID;
        this.colorID = colorID;
        this.pdetailID = pdetailID;
        this.quantity = quantity;
    }
    
    
}
