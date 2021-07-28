package com.ex.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Long id;
    private String name;
    private String address;
}
