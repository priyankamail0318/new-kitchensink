package com.appl.kitchensink.service;

import com.appl.kitchensink.model.Member;
import com.appl.kitchensink.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }
}
