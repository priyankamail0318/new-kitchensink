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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/health")
    public ResponseEntity<String> healthCheck() {
        log.info("Performing health check");
        return ResponseEntity.status(HttpStatus.OK).body("New Kitchen Sink loader service is Up and Running ...");
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

    @PostMapping("/register")
    public String registerMember(@ModelAttribute Member member, BindingResult result) {
        log.info("Inside controller Method ...");
        if (result.hasErrors()) {
            return null;
        }

        memberService.registerMember(member);

        return "Member Registered Successfully!";
    }

}
