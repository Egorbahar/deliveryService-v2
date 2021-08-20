package com.exposit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = " category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column (name = "Name")
    private String name;
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "parent_category_id")
    private Category parent;
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Category> subCategories;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
