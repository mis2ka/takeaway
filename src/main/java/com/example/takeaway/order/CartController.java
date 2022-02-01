package com.example.takeaway.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    private OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String cart() {
        return "cart";
    }

    @GetMapping("buy/{id}")
    public String buy(
            @PathVariable("id") Long id,
            Model model,
            HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Item> get_items = new ArrayList<Item>();
            get_items.add(new Item(orderService.find(id),1));
            session.setAttribute("cart", get_items);
        } else {
            List<Item> get_items = (List<Item>) session.getAttribute("cart");
            int index = exists(id, get_items);
            if (index == -1) {
                get_items.add(new Item(orderService.find(id),1));
            } else {
                int quantity = get_items.get(index).getQuantity() + 1;
                get_items.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", get_items);
        }
        return "redirect:../../cart";
    }

    private int exists(Long id, List<Item> get_items) {
        for(int i=0; i<get_items.size(); i++) {
            if(get_items.get(i).getOrder().getId() == id) {
                return i;
            }
        }
        return -1;
    }
}


