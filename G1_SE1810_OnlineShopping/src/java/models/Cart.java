/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dao.DAOSize;
import java.util.ArrayList;
import java.util.List;
import models.Item;
import models.Products;

public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuantityById(int id, int sizeId , int colorId) {
        return getItemById(id,sizeId ,colorId).getQuantity();
    }

    private Item getItemById(int id, int sizeId , int colorId) {
        for (Item i : items) {
            if (i.getProduct().getProductID() == id && i.getColor().getColorId() == colorId &&  i.getSize().getSizeId()== sizeId) {
                return i;
            }
        }
        return null;
    }

    public void addItem(Item t, int sizeId , int colorId) {
        if (getItemById(t.getProduct().getProductID(),sizeId,colorId) != null) {
            Item m = getItemById(t.getProduct().getProductID(),sizeId ,colorId);
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id, int sizeId , int colorId) {
        if (getItemById(id,sizeId ,colorId) != null) {
            items.remove(getItemById(id,sizeId ,colorId));
        }
    }

    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * (i.getProduct().getPrice() * (100.0 - i.getProduct().getDiscountSale()) / 100.0));
        }
        return t;
    }

    private Products getProductById(int pid, List<Products> list) {
        for (Products i : list) {
            if (i.getProductID() == pid) {
                return i;
            }
        }
        return null;
    }
    
    private String getImageById(int pid, int colorId) {
        return null;
    }

    public Cart(String txt, List<Products> list) {
        items = new ArrayList<>();
        DAOSize d = new DAOSize();
        try {
            if (txt != null && txt.length() != 0 && list != null) {
                String[] s = txt.split("/");
                for (String i : s) {
                    String[] n = i.split(":");
                    if (n.length >= 2) {
                        int pid = Integer.parseInt(n[0]);
                        int quantity = Integer.parseInt(n[1]);
                        int sizeId = Integer.parseInt(n[2]);
                        int colorId = Integer.parseInt(n[3]);
                        Size h = new Size();
                        h.setSizeId(sizeId);
                        h.setSize(d.getSizeNameByID(sizeId));
                        Color c = new Color();
                        c.setColorId(colorId);
                        c.setColorName(d.getColorNameByID(colorId));
                        String image = getImageById(pid, c.getColorId());
                        Products p = getProductById(pid, list);
                        Item t = new Item(p, h,c, quantity,image);
                        if (p != null) {
                            addItem(t,sizeId,colorId);
                        } else {
                            // Handle the case where no matching product is found
                        }
                    } else {
                        // Handle the case where the format of txt is incorrect
                    }
                }
            }
        } catch (NumberFormatException e) {
            // Handle the case where parsing integers from txt fails
        }
    }

}
