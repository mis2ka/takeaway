package com.example.takeaway.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private final OrderService orderService;
    private FeedbackController feedbackController;

    @Autowired
    public OrderController(OrderService orderService, FeedbackController feedbackController) {
        this.orderService = orderService;
        this.feedbackController = feedbackController;
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return "orders";
    }

    @GetMapping("/feedback/new")
    public String createFeedbackForm(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "feedback";
    }

    @PostMapping("/feedback/new")
    public String processFeedback(@ModelAttribute("feedback") Feedback feedback, BindingResult bindingResult) {
        feedbackController.sendFeedback(feedback, bindingResult);
        return "redirect:/orders";
    }

//    @PostMapping("/orders")
//    public String createNewOrder(@ModelAttribute("order") Order order) {
//        orderService.addNewOrder(order);
//        return "redirect:../../orders";
//    }

//    @DeleteMapping(path = "{orderId}")
//    public void deleteOrder(@PathVariable("orderId") Long orderId) {
//        orderService.deleteOrder(orderId);
//    }
}

