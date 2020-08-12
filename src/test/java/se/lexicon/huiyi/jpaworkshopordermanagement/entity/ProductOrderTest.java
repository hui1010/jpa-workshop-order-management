package se.lexicon.huiyi.jpaworkshopordermanagement.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductOrderTest {
    Product one;
    Product two;
    OrderItem orderItem1;
    OrderItem orderItem2;
    ProductOrder productOrder;
    List<OrderItem> orderItemList;


    @BeforeEach
    void setUp() {
        one = new Product("t-shirt", 100);
        two = new Product("cable", 150);
        orderItem1 = new OrderItem(5, one, null);
        orderItem2 = new OrderItem(2, two, null);
        orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        productOrder = new ProductOrder(LocalDateTime.now(), orderItemList, null);

    }

    @Test
    void addOrderItem() {
        Product three = new Product("ice cream", 40);

        OrderItem toAdd = new OrderItem(1, three, null);
        assertTrue(productOrder.addOrderItem(toAdd));
        assertEquals(3, orderItemList.size());
    }

    @Test
    void removeOrderItem() {
    }

    @Test
    void calculateOrderPrice() {
        double expected = 800;
        assertEquals(expected, productOrder.calculateOrderPrice());

    }
}