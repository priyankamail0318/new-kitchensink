package com.appl.kitchensink.controller;

import com.appl.kitchensink.model.Member;
import com.appl.kitchensink.repository.MemberRepository;
import com.appl.kitchensink.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/health")
    public ResponseEntity<String> healthCheck() {
        log.info("Performing health check");
        return ResponseEntity.status(HttpStatus.OK).body("New Kitchen Sink loader service is Running ...");
    }

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newMember", new Member());
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "index";
    }

    @GetMapping("/getAllmembers")
    public ResponseEntity<List<Member>> listAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Optional<Member>> findByMemberId(@PathVariable Long id) {
        log.info("Request coming to findByMemberId Controller ...");
        Optional<Member> member = memberService.findByMemberId(id);
        log.info("Request coming to findByMemberId Controller ..."+member);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/register")
    public ResponseEntity<String>  registerMember(@ModelAttribute Member member, BindingResult result) {
        log.info("Inside controller Method ...");
        if (result.hasErrors()) {
            return null;
        }
        if (memberService.emailExists(member.getEmail())) {
            log.warn("Email already exists: {}", member.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already registered.");
        }

        memberService.registerMember(member);

        return ResponseEntity.status(HttpStatus.CREATED).body("Member Registered Successfully!");
    }
    }

