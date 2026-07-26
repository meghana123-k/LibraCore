package com.libracore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}