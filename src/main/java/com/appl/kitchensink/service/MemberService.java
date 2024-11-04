package com.appl.kitchensink.service;

import com.appl.kitchensink.model.*;
import com.appl.kitchensink.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final SequenceGeneratorService sequenceGenerator;

    @Autowired
    public MemberService(MemberRepository memberRepository, SequenceGeneratorService sequenceGenerator) {
        this.memberRepository = memberRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll(Sort.by("name"));
    }

    public Optional<Member> findByMemberId(Long id) {
        return memberRepository.findById(id);
    }

    public boolean emailExists(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public Member registerMember(Member member) {
        Long id = sequenceGenerator.generateSequence("members_sequence");
        member.setId(id);
        return memberRepository.save(member);
    }
}
