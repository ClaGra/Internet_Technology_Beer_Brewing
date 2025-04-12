package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Inventory;
import ch.fhnw.brew.data.domain.Order;
import ch.fhnw.brew.data.repository.InventoryRepository;
import ch.fhnw.brew.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Order addOrder(Order order) throws Exception {
        order.setOrderDate(new Date());

        int remaining = order.getAmount();
        List<Inventory> available = inventoryRepository.findByInventoryCategoryName(order.getBeerName())
            .stream()
            .sorted(Comparator.comparing(Inventory::getExpirationDate))
            .collect(Collectors.toList());

        List<Integer> usedBatches = new ArrayList<>();

        for (Inventory inv : available) {
            if (remaining <= 0) break;

            int take = Math.min(remaining, inv.getInventoryAmount());
            inv.setInventoryAmount(inv.getInventoryAmount() - take);
            remaining -= take;

            inventoryRepository.save(inv);
            usedBatches.add(inv.getBatchNr());
        }

        if (remaining > 0) {
            throw new Exception("Not enough stock for " + order.getBeerName());
        }

        order.setBatchNumbers(usedBatches);
        return orderRepository.save(order);
    }

    public Order editOrder(Integer id, Order updatedOrder) throws Exception {
        Order existing = orderRepository.findById(id)
            .orElseThrow(() -> new Exception("Order not found"));

        for (Integer batchNr : existing.getBatchNumbers()) {
            List<Inventory> matching = inventoryRepository.findByBatchNr(batchNr);
            for (Inventory inv : matching) {
                inv.setInventoryAmount(inv.getInventoryAmount() + existing.getAmount());
                inventoryRepository.save(inv);
            }
        }

        updatedOrder.setOrderID(existing.getOrderID());
        updatedOrder.setOrderDate(existing.getOrderDate());
        return addOrder(updatedOrder);
    }

    public void deleteOrder(Integer id) throws Exception {
        Order existing = orderRepository.findById(id)
            .orElseThrow(() -> new Exception("Order not found"));

        for (Integer batchNr : existing.getBatchNumbers()) {
            List<Inventory> matching = inventoryRepository.findByBatchNr(batchNr);
            for (Inventory inv : matching) {
                inv.setInventoryAmount(inv.getInventoryAmount() + existing.getAmount());
                inventoryRepository.save(inv);
            }
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