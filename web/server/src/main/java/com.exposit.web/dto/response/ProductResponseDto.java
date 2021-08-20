package com.exposit.web.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String quantity;
    @NotNull
    private String price;

}
