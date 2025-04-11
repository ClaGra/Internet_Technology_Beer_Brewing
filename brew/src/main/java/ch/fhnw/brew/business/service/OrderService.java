package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Order;
import ch.fhnw.brew.data.repository.OrderRepository;
import ch.fhnw.brew.data.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    public Order addOrder(Order order) throws Exception {
        // Set the order date
        order.setOrderDate(new Date());

        // Decrease inventory before saving order
        Inventory inventory = inventoryService.updateInventoryAmount(order.getBeerName(), -order.getAmount());

        if (inventory.getInventoryAmount() < 0) {
            throw new Exception("Not enough stock for " + order.getBeerName());
        }

        return orderRepository.save(order);
    }

    public Order editOrder(Integer id, Order updatedOrder) throws Exception {
        Order existing = orderRepository.findById(id).orElseThrow(() -> new Exception("Order not found"));
        updatedOrder.setOrderID(existing.getOrderID()); // keep ID
        return orderRepository.save(updatedOrder);
    }

    public void deleteOrder(Integer id) throws Exception {
        if (!orderRepository.existsById(id)) {
            throw new Exception("Order not found");
        }
        orderRepository.deleteById(id);
    }

    public Order getOrder(Integer id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() -> new Exception("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
