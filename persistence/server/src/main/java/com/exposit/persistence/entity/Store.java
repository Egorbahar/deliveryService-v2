package com.exposit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column (name = "name")
    @NotNull(message = "store.name.notNull")
    @Size(min = 3, max = 50, message = "{store.name.size}")
    private String name;
    @Column (name = "address")
    @NotNull(message = "store.address.notNull")
    private String address;
    @ManyToMany
    @JoinTable(name="product_store",
            joinColumns=@JoinColumn(name="store_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

}
