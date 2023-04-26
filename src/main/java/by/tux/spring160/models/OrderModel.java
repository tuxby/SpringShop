package by.tux.spring160.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "spring160_orders")
@Data
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "product_id")
    private long productId;
}