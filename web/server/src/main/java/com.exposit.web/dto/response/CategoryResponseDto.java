package com.exposit.web.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {
    private Long id;
    @NotNull
    private String name;
    private Long parent_category_id;
}
