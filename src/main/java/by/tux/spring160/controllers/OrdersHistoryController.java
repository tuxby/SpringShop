package by.tux.spring160.controllers;

import by.tux.spring160.models.OrdersHistoryModel;
import by.tux.spring160.repos.OrdersHistoryRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/history")
public class OrdersHistoryController {

    private final OrdersHistoryRepo ordersHistoryRepo;

    public OrdersHistoryController(OrdersHistoryRepo ordersHistoryRepo) {
        this.ordersHistoryRepo = ordersHistoryRepo;
    }

    @GetMapping
    public String getPage(Model model){
        List<OrdersHistoryModel> list = ordersHistoryRepo.findAll();
        model.addAttribute("list", list);
        return "ordersHistory";
    }
}