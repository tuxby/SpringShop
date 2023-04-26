package by.tux.spring160.controllers;

import by.tux.spring160.models.ItemModel;
import by.tux.spring160.models.enums.Type;
import by.tux.spring160.repos.ItemRepo;
import by.tux.spring160.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/edit")
public class EditItem {
    private final ItemRepo itemRepo;
    private final FirebaseImageService firebaseImageService;

    public EditItem(ItemRepo itemRepo, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping("/del/{id}")
    public RedirectView delete(@PathVariable long id){
        itemRepo.deleteById(id);
        return new RedirectView("/allItems", true);
    }
    @GetMapping("/{id}")
    public String edit(@PathVariable long id , Model model){
        ItemModel itemModel = itemRepo.findItemModelById(id);
        itemModel.setName(itemModel.getName());
        itemModel.setDisc(itemModel.getDisc());
        itemModel.setPrice(itemModel.getPrice());
        itemModel.setWeight(itemModel.getWeight());
        itemModel.setUrl(itemModel.getUrl());
        switch (itemModel.getType()){
            case CHRISTMAS_TREES:
                itemModel.setType(Type.CHRISTMAS_TREES);
                break;
            case TOYS:
                itemModel.setType(Type.TOYS);
                break;
            case DECORATIONS:
                itemModel.setType(Type.DECORATIONS);
                break;
            case LIGHTS:
                itemModel.setType(Type.LIGHTS);
                break;
            case PRESENTS:
                itemModel.setType(Type.PRESENTS);
                break;
        }
        model.addAttribute("model", itemModel);
        return "editItem";
    }

    @PostMapping("/{id}")
    public RedirectView update(
            @PathVariable long id ,
            @RequestParam String name,
            @RequestParam String disc,
            @RequestParam int price,
            @RequestParam int weight,
            @RequestParam MultipartFile url,
            @RequestParam String type) throws Exception {

        ItemModel itemModel = itemRepo.getReferenceById(id);
        itemModel.setName(name);
        itemModel.setDisc(disc);
        itemModel.setPrice(price);
        if (url.getOriginalFilename().equals("")==false){
            String fileName = firebaseImageService.save(url);
            itemModel.setUrl(fileName);
        }
        itemModel.setWeight(weight);
        switch (type){
            case "CHRISTMAS_TREES":
                itemModel.setType(Type.CHRISTMAS_TREES);
                break;
            case "TOYS":
                itemModel.setType(Type.TOYS);
                break;
            case "DECORATIONS":
                itemModel.setType(Type.DECORATIONS);
                break;
            case "LIGHTS":
                itemModel.setType(Type.LIGHTS);
                break;
            case "PRESENTS":
                itemModel.setType(Type.PRESENTS);
                break;
        }
        itemRepo.save(itemModel);
        return new RedirectView("/allItems", true);
    }
}
