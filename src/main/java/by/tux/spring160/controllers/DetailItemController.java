package by.tux.spring160.controllers;

import by.tux.spring160.models.ItemModel;
import by.tux.spring160.models.OrderModel;
import by.tux.spring160.models.helpers.BufferItem;
import by.tux.spring160.repos.ItemRepo;
import by.tux.spring160.repos.OrdersRepo;
import by.tux.spring160.services.CurrencyService;
import by.tux.spring160.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/detailItem")
public class DetailItemController {
    private final ItemRepo itemRepo;
    private final OrdersRepo ordersRepo;
    private final CurrencyService currencyService;

    private final FirebaseImageService firebaseImageService;

    public DetailItemController(ItemRepo itemRepo, OrdersRepo ordersRepo, CurrencyService currencyService, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.ordersRepo = ordersRepo;
        this.currencyService = currencyService;
        this.firebaseImageService = firebaseImageService;
    }
    @GetMapping("/{id}")
    public String getDetailItem(@PathVariable long id,
                                Model model){
        ItemModel itemModel = itemRepo.findItemModelById(id);
        BufferItem bufferItem = new BufferItem(itemModel);
        bufferItem.setUrl(firebaseImageService.getPhotoUrl(bufferItem.getUrl()));
        try {
            bufferItem.setEurPrice(round(currencyService.getEurPrice(itemModel.getPrice()), 2)+" eur");
            bufferItem.setUsdPrice(round(currencyService.getUsdPrice(itemModel.getPrice()), 2)+" usd");
//            bufferItem.setEurPrice("1.0" + " eur");
//            bufferItem.setUsdPrice("1.0" + " usd");
        } catch (Exception e) {
            bufferItem.setUsdPrice("no data");
            bufferItem.setEurPrice("no data");
            e.printStackTrace();
        }
        model.addAttribute("model", bufferItem);

        return "detailItem";
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @PostMapping("/{id}")
    public RedirectView addPurchase(@PathVariable long id,
                                    @RequestParam String name,
                                    @RequestParam String contact) {

        OrderModel orderModel = new OrderModel();
        orderModel.setName(name);
        orderModel.setContact(contact);
        orderModel.setProductId(id);
        ordersRepo.save(orderModel);
        return new RedirectView("/", true);
    }
}
