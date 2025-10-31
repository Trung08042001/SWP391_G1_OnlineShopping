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
public class Shipper {

    private int shipperID;
    private Account account;
    private String vehicleType;
    private String vehiclePlateNumber;
    private String cccd;
    private Boolean isAvailable;

    public Shipper() {
    }

    public Shipper(int shipperID, Account account, String vehicleType, String vehiclePlateNumber, String cccd, Boolean isAvailable) {
        this.shipperID = shipperID;
        this.account = account;
        this.vehicleType = vehicleType;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.cccd = cccd;
        this.isAvailable = isAvailable;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Shipper{" + "shipperID=" + shipperID + ", account=" + account + ", vehicleType=" + vehicleType + ", vehiclePlateNumber=" + vehiclePlateNumber + ", cccd=" + cccd + ", isAvailable=" + isAvailable + '}';
    }


}
