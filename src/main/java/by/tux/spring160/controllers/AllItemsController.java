package by.tux.spring160.controllers;

import by.tux.spring160.models.ItemModel;
import by.tux.spring160.models.enums.Type;
import by.tux.spring160.repos.ItemRepo;
import by.tux.spring160.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/allItems")
public class AllItemsController {

    private final ItemRepo itemRepo;
    private final FirebaseImageService firebaseImageService;

    public AllItemsController(ItemRepo itemRepo, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.firebaseImageService = firebaseImageService;
    }


    @GetMapping
    public String getPage(Model model){
        List<ItemModel> list = itemRepo.findAll();
        for (ItemModel i : list){
            i.setUrl(firebaseImageService.getPhotoUrl(i.getUrl()));
        }
        model.addAttribute("list", list);
        return "allItems";
    }

    @GetMapping("/{filter}")
    public String Filter(@PathVariable String filter,Model model) {
        Type type = Type.PRESENTS;
        switch (filter){
            case "trees":
                type=Type.CHRISTMAS_TREES;
                break;
            case "toys":
                type=Type.TOYS;
                break;
            case "decor":
                type=Type.DECORATIONS;
                break;
            case "garland":
                type = Type.LIGHTS;
                break;
            case "gift":
                type=Type.PRESENTS;
                break;
        }
        List<ItemModel> list = itemRepo.findAllByType(type);
        for (ItemModel i : list){
            i.setUrl(firebaseImageService.getPhotoUrl(i.getUrl()));
        }
        model.addAttribute("list", list);
        return "allItems";
    }
}
