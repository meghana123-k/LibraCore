package com.libracore.service;

import com.libracore.dto.MemberRequestDTO;
import com.libracore.dto.MemberResponseDTO;
import com.libracore.entity.Member;
import com.libracore.exception.BookNotFoundException;
import com.libracore.exception.DuplicateResourceException;
import com.libracore.exception.MemberNotFoundException;
import com.libracore.repository.MemberRepository;
import com.libracore.util.MemberMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Page<MemberResponseDTO> getAllMembers(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return memberRepository.findAll(pageable)
                .map(MemberMapper::toResponse);
    }

    public MemberResponseDTO getMemberById(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));

        return MemberMapper.toResponse(member);
    }

    public MemberResponseDTO addMember(MemberRequestDTO dto) {

        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (memberRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        Member savedMember = memberRepository.save(MemberMapper.toEntity(dto));

        return MemberMapper.toResponse(savedMember);
    }

    public MemberResponseDTO updateMember(Long id, MemberRequestDTO dto) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Member not found with id: " + id));

        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setAddress(dto.getAddress());

        Member updatedMember = memberRepository.save(member);

        return MemberMapper.toResponse(updatedMember);
    }

    public void deleteMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Member not found with id: " + id));

        memberRepository.delete(member);
    }
}