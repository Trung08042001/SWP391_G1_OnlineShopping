/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author Nitro
 */
public class Products {

    private int productID;
    private String productName;
    private double price;
    private double discountSale;
    private String description;
    private Date create_at;
    private Date update_at;
    private int categoryID;
    private int brandID;
    private int status;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Products() {
    }

    public Products(int productID, String productName, double price, double discountSale, String image, String description, Date create_at, Date update_at, int categoryID, int brandID, int status) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.discountSale = discountSale;
        this.description = description;
        this.create_at = create_at;
        this.update_at = update_at;
        this.categoryID = categoryID;
        this.brandID = brandID;
        this.status = status;
    }

    public Products(int productID, String productName, double price, double discountSale, String description, Date create_at, Date update_at, int status) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.discountSale = discountSale;
        this.description = description;
        this.create_at = create_at;
        this.update_at = update_at;
        this.status = status;
    }

    public Products(int productID, String productName, double price, double discountSale, String description, Date create_at, Date update_at, int categoryID, int brandID, int status, Size size) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.discountSale = discountSale;
        this.description = description;
        this.create_at = create_at;
        this.update_at = update_at;
        this.categoryID = categoryID;
        this.brandID = brandID;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
//        return price * (100.0 - discountSale) / 100.0;
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountSale() {
        return discountSale;
    }

    public void setDiscountSale(double discountSale) {
        this.discountSale = discountSale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
