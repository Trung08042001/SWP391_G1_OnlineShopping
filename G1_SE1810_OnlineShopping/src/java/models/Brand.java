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
public class Brand {

    private int brandID;
    private String brandName;
    private Date create_at;
    private Date update_at;
    private Category categoryID;

    public Brand() {
    }

    public Brand(int brandID, String brandName, Date create_at, Date update_at, Category categoryID) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.create_at = create_at;
        this.update_at = update_at;
        this.categoryID = categoryID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "Brand{" + "brandID=" + brandID + ", brandName=" + brandName + ", create_at=" + create_at + ", update_at=" + update_at + ", categoryID=" + categoryID + '}';
    }

    
}
