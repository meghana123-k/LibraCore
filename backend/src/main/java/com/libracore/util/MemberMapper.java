package com.libracore.util;

import com.libracore.dto.MemberRequestDTO;
import com.libracore.dto.MemberResponseDTO;
import com.libracore.entity.Member;

public class MemberMapper {

    public static Member toEntity(MemberRequestDTO dto) {

        return Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();
    }

    public static MemberResponseDTO toResponse(Member member) {

        return MemberResponseDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();
    }
}