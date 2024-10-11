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

    public Member registerMember(String name, String email, String phoneNumber) {
        log.info("Member Service Method Called...");
        Member newMember = new Member();
        newMember.setName(name);
        newMember.setEmail(email);
        newMember.setPhoneNumber(phoneNumber);
        return memberRepository.save(newMember);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
