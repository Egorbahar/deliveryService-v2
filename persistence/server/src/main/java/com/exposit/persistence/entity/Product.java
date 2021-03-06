package com.exposit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @NotNull
    @Size(min = 3, max = 50, message = "{product.name.size}")
    private String name;
    @Column(name = "quantity")
    @NotNull(message = "product.quantity.notNull")
    @PositiveOrZero(message = "{product.quantity.positiveOrZero}")
    private Integer quantity;
    @Column(name = "price")
    @NotNull(message = "product.price.notNull")
    @Positive(message = "{product.price.positive}")
    private Double price;

    @ManyToMany
    @JoinTable(name = "product_store",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    private List<Store> stores;
    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Product(Long id) {
        this.id = id;
    }

    public String valueOfPrice() {
        return String.valueOf(price);
    }

}
