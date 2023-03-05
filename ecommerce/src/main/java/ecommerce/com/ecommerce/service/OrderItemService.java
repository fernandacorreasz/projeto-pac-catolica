package ecommerce.com.ecommerce.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import ecommerce.com.ecommerce.model.OrderItem;
import ecommerce.com.ecommerce.repository.OrderItemRepository;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
