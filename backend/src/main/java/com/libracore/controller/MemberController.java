package com.libracore.controller;

import com.libracore.dto.MemberRequestDTO;
import com.libracore.dto.MemberResponseDTO;
import com.libracore.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public Page<MemberResponseDTO> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy) {

        return memberService.getAllMembers(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public MemberResponseDTO getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDTO addMember(
            @Valid @RequestBody MemberRequestDTO dto) {

        return memberService.addMember(dto);
    }

    @PutMapping("/{id}")
    public MemberResponseDTO updateMember(
            @PathVariable Long id,
            @Valid @RequestBody MemberRequestDTO dto) {

        return memberService.updateMember(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long id) {

        memberService.deleteMember(id);
    }
}