package se.lexicon.huiyi.jpaworkshopordermanagement.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.huiyi.jpaworkshopordermanagement.data.OrderItemRepository;
import se.lexicon.huiyi.jpaworkshopordermanagement.entity.OrderItem;
import se.lexicon.huiyi.jpaworkshopordermanagement.entity.Product;
import se.lexicon.huiyi.jpaworkshopordermanagement.entity.ProductOrder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OrderItemRepositoryTest {

    OrderItem testObject;

    @Autowired
    OrderItemRepository orderItemRepository;

    @BeforeEach
    void setUp() {
        Product car = new Product("Car", 1_000_000);
        testObject = orderItemRepository.save(new OrderItem(2, car, null));
    }

    @Test
    void successfully_created() {
        assertFalse(orderItemRepository.findAll().isEmpty());
        assertTrue(orderItemRepository.findAll().contains(testObject));
    }
}