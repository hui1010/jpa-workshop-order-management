package se.lexicon.huiyi.jpaworkshopordermanagement.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.huiyi.jpaworkshopordermanagement.entity.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    //set up the tests
    Product testObject;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

        testObject = productRepository.save(new Product("Book", 150));
        productRepository.save(new Product("T-shirt", 199));
        productRepository.save(new Product("Car", 1_000_000));
        productRepository.save(new Product("The Book", 190));
    }

    @Test
    void successfullyCreated() {
        assertEquals(4, productRepository.findAll().size());//go back to the repository and override the findAll()
    }

    @Test
    void findAllByNameContainingIgnoreCae() {
        //Arrange
        String search = "boo";
        //Act
        List<Product> foundProductsMatchingSearch = productRepository.findAllByNameContainingIgnoreCase(search);
        //Assert
        assertNotNull(foundProductsMatchingSearch);
        assertFalse(foundProductsMatchingSearch.isEmpty());
        assertEquals(2, foundProductsMatchingSearch.size());
        assertTrue(foundProductsMatchingSearch.contains(testObject));
    }
}