package ecommerce.com.ecommerce.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ecommerce.com.ecommerce.model.Order;
import ecommerce.com.ecommerce.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    }

    public Order createOrder(Order order) {
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedTime(now);

        User user = userService.getUserById(order.getUser().getId());
        order.setUser(user);

        Set<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            Product product = productRepository.findById(orderItem.getProduct().getId()).orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderItem.getProduct().getId()));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
        }

        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        Order order = getOrderById(orderId);

        order.setCreatedTime(orderDetails.getCreatedTime());
        order.setUser(orderDetails.getUser());
        order.setOrderItems(orderDetails.getOrderItems());

        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }
}
