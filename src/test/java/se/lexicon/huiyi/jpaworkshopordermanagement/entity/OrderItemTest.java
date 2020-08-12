package se.lexicon.huiyi.jpaworkshopordermanagement.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void calculateProducts() {

        Product product = new Product("t-shirt", 100);
        OrderItem orderItem = new OrderItem(5, product, null);
        double expected = 500;
        double actual = orderItem.calculateProducts();
        assertEquals(expected, actual);

    }
}