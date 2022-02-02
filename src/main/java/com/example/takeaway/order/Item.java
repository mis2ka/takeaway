package com.example.takeaway.order;

public class Item {

    private Order order;
    private int quantity;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(Order order, int quantity) {
        this.order = order;
        this.quantity = quantity;
    }
}
