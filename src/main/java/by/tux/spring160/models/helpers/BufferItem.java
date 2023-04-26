package by.tux.spring160.models.helpers;

import by.tux.spring160.models.ItemModel;
import by.tux.spring160.models.enums.Type;
import lombok.Data;

import javax.persistence.*;

@Data
public class BufferItem {
    private long id;
    private String name;
    private String disc;
    private int price;
    private int weight;
    private String url;
    private Type type;
    private String eurPrice;
    private String usdPrice;

    public BufferItem(ItemModel itemModel) {
        this.id = itemModel.getId();
        this.name = itemModel.getName();
        this.disc = itemModel.getDisc();
        this.price = itemModel.getPrice();
        this.weight = itemModel.getWeight();
        this.url = itemModel.getUrl();
        this.type = itemModel.getType();
    }
}
