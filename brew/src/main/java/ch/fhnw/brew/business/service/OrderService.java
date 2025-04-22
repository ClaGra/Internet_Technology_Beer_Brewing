package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Customer;
import ch.fhnw.brew.data.domain.Inventory;
import ch.fhnw.brew.data.domain.Order;
import ch.fhnw.brew.data.domain.OrderItem;
import ch.fhnw.brew.data.repository.CustomerRepository;
import ch.fhnw.brew.data.repository.InventoryRepository;
import ch.fhnw.brew.data.repository.OrderRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Order addOrder(Order order) {
        order.setOrderDate(LocalDate.now());

        // Load full customer data
        Integer customerId = order.getCustomer().getCustomerID();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        order.setCustomer(customer);

        // Pre-check stock availability for all items first
        for (OrderItem item : order.getItems()) {
            int requested = item.getAmount();

            int totalAvailable = inventoryRepository.findByInventoryCategoryName(item.getBeerName()).stream()
                    .mapToInt(Inventory::getInventoryAmount)
                    .sum();

            if (totalAvailable < requested) {
                throw new IllegalStateException("Not enough stock for " + item.getBeerName());
            }
        }

        // If all items can be fulfilled, deduct stock and split per batch
        List<OrderItem> processedItems = new ArrayList<>();

        for (OrderItem item : order.getItems()) {
            int remaining = item.getAmount();

            List<Inventory> available = inventoryRepository.findByInventoryCategoryName(item.getBeerName())
                    .stream()
                    .filter(inv -> inv.getInventoryAmount() > 0)
                    .sorted(Comparator.comparing(Inventory::getExpirationDate))
                    .collect(Collectors.toList());

            for (Inventory inv : available) {
                if (remaining <= 0) break;

                int take = Math.min(remaining, inv.getInventoryAmount());
                inv.setInventoryAmount(inv.getInventoryAmount() - take);
                inventoryRepository.save(inv);

                // Skip if we ended up taking 0
                if (take <= 0) continue;

                OrderItem splitItem = new OrderItem();
                splitItem.setBeerName(item.getBeerName());
                splitItem.setAmount(take);
                splitItem.setBatchNumber(inv.getBatchNr());

                processedItems.add(splitItem);
                remaining -= take;
            }
        }

        order.setItems(processedItems);
        return orderRepository.save(order);
    }

    public Order editOrder(Integer id, Order updatedOrder) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        // Restore previously deducted inventory
        for (OrderItem item : existing.getItems()) {
            List<Inventory> matching = inventoryRepository.findByBatchNr(item.getBatchNumber());
            for (Inventory inv : matching) {
                inv.setInventoryAmount(inv.getInventoryAmount() + item.getAmount());
                inventoryRepository.save(inv);
            }
        }

        updatedOrder.setOrderID(existing.getOrderID());
        updatedOrder.setOrderDate(existing.getOrderDate());
        return addOrder(updatedOrder);
    }

    public void deleteOrder(Integer id) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        // Restore inventory for deleted order
        for (OrderItem item : existing.getItems()) {
            List<Inventory> matching = inventoryRepository.findByBatchNr(item.getBatchNumber());
            for (Inventory inv : matching) {
                inv.setInventoryAmount(inv.getInventoryAmount() + item.getAmount());
                inventoryRepository.save(inv);
            }
        }

        orderRepository.delete(existing);
    }

    public Order getOrder(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
