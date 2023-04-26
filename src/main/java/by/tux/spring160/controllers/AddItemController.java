package by.tux.spring160.controllers;

import by.tux.spring160.models.ItemModel;
import by.tux.spring160.models.enums.Type;
import by.tux.spring160.repos.ItemRepo;
import by.tux.spring160.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/addItem")
public class AddItemController {

    private final ItemRepo itemRepo;
    private final FirebaseImageService firebaseImageService;

    public AddItemController(ItemRepo itemRepo, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping
    public String getAddPage(){
        return "addItem";
    }
    @PostMapping
    public RedirectView setData(@RequestParam String name,
                                @RequestParam String disc,
                                @RequestParam int price,
                                @RequestParam int weight,
                                @RequestParam MultipartFile url,
                                @RequestParam String type) throws Exception {

        ItemModel itemModel = new ItemModel();
        itemModel.setName(name);
        itemModel.setDisc(disc);
        itemModel.setPrice(price);
        String fileName = firebaseImageService.save(url);
        itemModel.setUrl(fileName);
        itemModel.setWeight(weight);
        switch (type){
            case "Ёлка":
                itemModel.setType(Type.CHRISTMAS_TREES);
                break;
            case "Игрушка":
                itemModel.setType(Type.TOYS);
                break;
            case "Украшение":
                itemModel.setType(Type.DECORATIONS);
                break;
            case "Гирлянда":
                itemModel.setType(Type.LIGHTS);
                break;
            case "Подарок":
                itemModel.setType(Type.PRESENTS);
                break;
            default:
                itemModel.setType(Type.PRESENTS);
                break;
        }
        itemRepo.save(itemModel);
        return new RedirectView("/allItems", true);
    }
}
