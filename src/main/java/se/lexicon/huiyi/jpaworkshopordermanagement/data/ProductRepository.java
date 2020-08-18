package se.lexicon.huiyi.jpaworkshopordermanagement.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.huiyi.jpaworkshopordermanagement.entity.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAllByNameContainingIgnoreCase(String productName);
    List<Product> findAll();//related to the test, otherwise can't see the size

}
