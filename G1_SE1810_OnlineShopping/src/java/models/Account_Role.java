/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nitro
 */
public class Account_Role {

    private int Id;
    private String Role;

    public Account_Role() {
    }

    public Account_Role(int Id, String Role) {
        this.Id = Id;
        this.Role = Role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "Account_Role{" + "Id=" + Id + ", Role=" + Role + '}';
    }
}
