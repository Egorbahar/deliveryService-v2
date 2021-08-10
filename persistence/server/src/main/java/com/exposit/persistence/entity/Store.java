package com.exposit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column (name = "name")
    private String name;
    @Column (name = "address")
    private String address;
    @ManyToMany
    @JoinTable(name="product_store",
            joinColumns=@JoinColumn(name="store_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

}
