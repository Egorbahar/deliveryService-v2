package com.exposit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum CategoryItem {
    FOOD_PRODUCTS("Food products", 1),
    SPORTING_GOODS("Sport products", 2),
    HOUSEHOLD_GOODS("Household products", 3),
    BUILDING_MATERIALS("Building products", 4);

    private String title;
    private Integer index;

    public static String printAll() {
        return Stream.of(CategoryItem.values())
                     .map(categoryItem -> categoryItem.index + "." + categoryItem.title)
                     .collect(Collectors.joining("\n"));
    }

    public static CategoryItem getByIndex(Integer index) {
        return Stream.of(CategoryItem.values())
                     .filter(c -> c.getIndex().equals(index))
                     .findFirst()
                     .get();
    }

}


