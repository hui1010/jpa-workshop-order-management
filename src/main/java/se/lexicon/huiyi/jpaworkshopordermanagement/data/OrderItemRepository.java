package se.lexicon.huiyi.jpaworkshopordermanagement.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.huiyi.jpaworkshopordermanagement.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
    List<OrderItem> findAll();
}
