package by.tux.spring160.models;

import by.tux.spring160.models.enums.Type;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "spring160_items")
@Data
public class ItemModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "disc")
    private String disc;

    @Column(name = "price")
    private int price;

    @Column(name = "weight")
    private int weight;

    @Column(name = "ph_url")
    private String url;

    @Enumerated()
    @Column(name = "item_type")
    private Type type;

}
