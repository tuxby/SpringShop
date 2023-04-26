package by.tux.spring160.controllers;

import by.tux.spring160.models.OrderModel;
import by.tux.spring160.models.OrdersHistoryModel;
import by.tux.spring160.repos.OrdersHistoryRepo;
import by.tux.spring160.repos.OrdersRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersRepo ordersRepo;
    private final OrdersHistoryRepo ordersHistoryRepo;


    public OrdersController(OrdersRepo ordersRepo, OrdersHistoryRepo ordersHistoryRepo) {
        this.ordersRepo = ordersRepo;
        this.ordersHistoryRepo = ordersHistoryRepo;
    }


    @GetMapping
    public String getPage(Model model){
        List<OrderModel> list = ordersRepo.findAll();
        model.addAttribute("list", list);
        return "orders";
    }

    @GetMapping("/del/{id}")
    public RedirectView delete(@PathVariable long id) {
        OrderModel orderModel = ordersRepo.findOrderRequestModelById(id);

        OrdersHistoryModel ordersHistoryModel =new OrdersHistoryModel();
        ordersHistoryModel.setName(orderModel.getName());
        ordersHistoryModel.setContact(orderModel.getContact());
        ordersHistoryModel.setProductId(orderModel.getProductId());
        ordersHistoryRepo.save(ordersHistoryModel);

        ordersRepo.deleteById(id);

        return new RedirectView("/orders", true);
    }
}
