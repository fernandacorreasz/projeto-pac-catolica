package ecommerce.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}