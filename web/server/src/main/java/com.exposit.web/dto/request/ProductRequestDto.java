package com.exposit.web.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String quantity;
    @NotNull
    private String price;

}
