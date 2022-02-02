package com.example.takeaway.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("orderService")
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
//    private OrderDAO orderDAO;
    //@Override
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    public Iterable<Order> findAll(); {
//        return orderRepository.findAll();
//    }
    public List<Order> getOrders() {
        return  orderRepository.findAll();
    }

    public Order find(Long id) {
//        return orderRepository.findOne(id); //findDAO
//        Order orderOptional =
        return orderRepository.findOrderById(id);
    }


//    public Feedback saveFeedback(Feedback feedback) {
//        return feedbackRepository.save(feedback);
//    }


//    public void addNewOrder(Order order) {
//        Optional<Order> orderOptional = orderRepository
//                .findOrderByProduct(order.getProduct());
//        if (orderOptional.isPresent()) {
//            throw new IllegalStateException("zamowienie pobrane");
//        }
//        orderRepository.save(order);
//    }
//
//    public void deleteOrder(Long orderId) {
//        boolean exists = orderRepository.existsById(orderId);
//        if (!exists) {
//            throw new IllegalStateException("Zamowienie o id " + orderId + " nie istnieje");
//        }
//        orderRepository.deleteById(orderId);
//    }
}

