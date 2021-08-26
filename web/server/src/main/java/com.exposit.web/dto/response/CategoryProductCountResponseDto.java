package com.exposit.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryProductCountResponseDto {
    private String categoryName;
    private Integer countProduct;
}
