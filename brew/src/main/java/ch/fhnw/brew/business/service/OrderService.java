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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AlertService alertService;

    @Transactional
    public Order addOrder(Order order) {
        order.setOrderDate(LocalDate.now());

        // Load full customer details
        Integer customerId = order.getCustomer().getCustomerID();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        order.setCustomer(customer);

        // Check availability before deducting — collect all errors
        List<String> stockErrors = new ArrayList<>();

        for (OrderItem item : order.getItems()) {
            int requested = item.getAmount();
            int totalAvailable = inventoryRepository.findByInventoryCategoryName(item.getBeerName()).stream()
                    .mapToInt(Inventory::getInventoryAmount)
                    .sum();

            if (totalAvailable < requested) {
                stockErrors.add("Not enough stock for " + item.getBeerName());
            }
        }

        if (!stockErrors.isEmpty()) {
            throw new IllegalStateException(String.join("; ", stockErrors));
        }

        // Inventory deduction and processing
        List<OrderItem> processedItems = new ArrayList<>();

        for (OrderItem item : order.getItems()) {
            int remaining = item.getAmount();

            List<Inventory> available = inventoryRepository.findByInventoryCategoryName(item.getBeerName())
                    .stream()
                    .sorted(Comparator.comparing(Inventory::getExpirationDate))
                    .collect(Collectors.toList());

            for (Inventory inv : available) {
                if (remaining <= 0) break;

                int availableAmount = inv.getInventoryAmount();
                int take = Math.min(remaining, availableAmount);

                if (take > 0) {
                    inv.setInventoryAmount(availableAmount - take);
                    inventoryRepository.save(inv);

                    OrderItem splitItem = new OrderItem();
                    splitItem.setBeerName(item.getBeerName());
                    splitItem.setAmount(take);
                    splitItem.setBatchNumber(inv.getBatchNr());

                    processedItems.add(splitItem);
                    remaining -= take;
                }
            }

            // Trigger alert if remaining inventory falls below threshold
            int totalRemaining = inventoryRepository.findByInventoryCategoryName(item.getBeerName())
                    .stream()
                    .mapToInt(Inventory::getInventoryAmount)
                    .sum();

            if (totalRemaining < 72) {
                alertService.triggerLowInventoryAlert(item.getBeerName(), totalRemaining);
            }
        }

        order.setItems(processedItems);
        return orderRepository.save(order);
    }

    @Transactional
    public Order editOrder(Integer id, Order updatedOrder) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        // Restore previous inventory
        for (OrderItem item : existing.getItems()) {
            List<Inventory> matching = inventoryRepository.findByBatchNr(item.getBatchNumber());
            for (Inventory inv : matching) {
                inv.setInventoryAmount(inv.getInventoryAmount() + item.getAmount());
                inventoryRepository.save(inv);
            }
        }

        // Set metadata and validate via addOrder
        updatedOrder.setOrderID(existing.getOrderID());
        updatedOrder.setOrderDate(existing.getOrderDate());

        return addOrder(updatedOrder);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(Integer id) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        // Restore inventory before deletion
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
