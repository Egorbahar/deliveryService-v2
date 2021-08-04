package com.exposit.core.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String quantity;
    @NotNull
    private String price;

}
