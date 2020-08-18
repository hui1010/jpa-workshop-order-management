package se.lexicon.huiyi.jpaworkshopordermanagement.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime orderDateTime;

    @OneToMany(orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //ProductOrder is the parent, inside the orderItem, there is a @ManyToOne
    //if this orderItems is removed from the ProductOrder, means it is parentless
    //if the orderItem doesn't any parent, it should be removed
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private AppUser customer;

    public ProductOrder() {
    }

    public ProductOrder(LocalDateTime orderDateTime, List<OrderItem> orderItems, AppUser customer) {
        this.orderDateTime = orderDateTime;
        this.orderItems = orderItems;
        this.customer = customer;
    }

    public boolean addOrderItem(OrderItem orderItem){

        boolean added = false;
        if (orderItems == null){
            orderItems = new ArrayList<>();
        }

        if (orderItem == null){
            throw new IllegalArgumentException("Order item is null, not allowed to send in null");
        }

        if (!orderItems.contains(orderItem)){
            added = orderItems.add(orderItem);
            orderItem.setProductOrder(this);

        }
        return added;
    }

    public boolean removeOrderItem(OrderItem orderItem){
        boolean beRemoved = false;
        if (orderItems == null){
            orderItems = new ArrayList<>();
        }

        if (orderItem == null){
            throw new IllegalArgumentException("Order item is null, not allowed to send in null");
        }

        if (orderItems.contains(orderItem)){
            beRemoved = orderItems.remove(orderItem);
            orderItem.setProductOrder(null);

        }

        return beRemoved;
    }

    public double calculateOrderPrice(){
        double result = 0.0;
        for (OrderItem content: orderItems){
           result += content.calculateProducts();
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public AppUser getCustomer() {
        return customer;
    }

    public void setCustomer(AppUser customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return id == that.id &&
                Objects.equals(orderDateTime, that.orderDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDateTime);
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", orderDateTime=" + orderDateTime +
                '}';
    }

}
