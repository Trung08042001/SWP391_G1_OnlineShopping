/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Account {

    private int id;
    private String fullname;
    private Boolean gender;
    private String address;
    private String email;
    private String password;
    private String phone_number;
    private int roleID;
    private int status;
    private String profile_picture;
    private Date create_at;
    private Date update_at;
    private String role;
    private Shipper shipper;

    public Account() {
    }

    public Account(int id, String fullname, Boolean gender, String address, String email, String password, String phone_number, int roleID, int status, String profile_picture, Date create_at, Date update_at) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.roleID = roleID;
        this.status = status;
        this.profile_picture = profile_picture;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public Account(int id, String fullname, Boolean gender, String address, String email, String password, String phone_number, int roleID, int status, String profile_picture, Date create_at, Date update_at, String role, Shipper shipper) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.roleID = roleID;
        this.status = status;
        this.profile_picture = profile_picture;
        this.create_at = create_at;
        this.update_at = update_at;
        this.role = role;
        this.shipper = shipper;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", fullname=" + fullname + ", gender=" + gender + ", address=" + address + ", email=" + email + ", password=" + password + ", phone_number=" + phone_number + ", roleID=" + roleID + ", status=" + status + ", profile_picture=" + profile_picture + ", create_at=" + create_at + ", update_at=" + update_at + ", role=" + role + ", shipper=" + shipper + '}';
    }

   

}
