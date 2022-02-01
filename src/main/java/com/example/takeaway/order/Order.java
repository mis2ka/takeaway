package com.example.takeaway.order;

import javax.persistence.*;

@Entity
@Table (name="orders")
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )

    private Long id;
    private String product;
    private String restaurant;
    private Double price;
    private Boolean chosen;

    public Order() {
    }

    public Order(Long id,
                 String product,
                 String restaurant,
                 Double price) {
        this.id = id;
        this.product = product;
        this.restaurant = restaurant;
        this.price = price;
    }

    public Order(String product, String restaurant, Double price, Boolean chosen) {
        this.product = product;
        this.restaurant = restaurant;
        this.price = price;
        this.chosen = chosen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getChosen() {
        return chosen;
    }

    public void setChosen(Boolean chosen) {
        this.chosen = chosen;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", restaurant='" + restaurant + '\'' +
                ", price=" + price +
                '}';
    }
}
