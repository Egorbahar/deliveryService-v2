package com.exposit.web.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @NotNull
    private List<Long> categories_id;
}
