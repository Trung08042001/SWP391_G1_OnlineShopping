/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author khanh cu be
 */
public class Category {
     private int categoryID;
    private String cname;
    private Date create_at;
    private Date update_at;
    private String image;
    private String imageSize;
    private Brand brandID;

    public Category() {
    }

    public Category(int categoryID, String cname, Date create_at, Date update_at, String image, String imageSize, Brand brandID) {
        this.categoryID = categoryID;
        this.cname = cname;
        this.create_at = create_at;
        this.update_at = update_at;
        this.image = image;
        this.imageSize = imageSize;
        this.brandID = brandID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public Brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryID=" + categoryID + ", cname=" + cname + ", create_at=" + create_at + ", update_at=" + update_at + ", image=" + image + ", imageSize=" + imageSize + ", brandID=" + brandID + '}';
    }
}
