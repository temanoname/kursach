package com.example.autoservice.services;

import com.example.autoservice.models.Orders;
import com.example.autoservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private final OrderRepository ordersRepository;

    @Autowired
    public OrdersService(OrderRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public Orders save(Orders order) {
        return ordersRepository.save(order);
    }

    public void delete(Orders order) {
        ordersRepository.delete(order);
    }

    public void updateOrderStatus(Long orderId, Boolean completed) {
        Optional<Orders> optionalOrder = ordersRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.setCompleted(completed);
            if (completed) {
                ordersRepository.delete(order);
            } else {
                ordersRepository.save(order);
            }
        }
    }
}
