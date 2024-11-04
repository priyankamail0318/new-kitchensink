package com.appl.kitchensink.controller;

import com.appl.kitchensink.model.*;
import com.appl.kitchensink.repository.*;
import com.appl.kitchensink.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
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

    @GetMapping("/getAllmembersListPage")
    public String listAllMembers(Model model) {
        log.info("Request coming to listAllMembers Controller ...");
        List<Member> members = memberService.getAllMembers();
        log.info("Requested Members: " + members);

        if (!members.isEmpty()) {
            model.addAttribute("members", members);
            return "allMembers";
        } else {
            model.addAttribute("errorMessage", "Error when retriving all the members Page!");
            return "error";
        }
    }


    @GetMapping("/member/{id}")
    public String findByMemberId(@PathVariable Long id, Model model) {
        log.info("Request coming to findByMemberId Controller ...");
        Optional<Member> member = memberService.findByMemberId(id);
        log.info("Requested Member: " + member);

        if (member.isPresent()) {
            model.addAttribute("member", member.get());
            return "memberDetail";
        } else {
            model.addAttribute("errorMessage", "We're sorry, but the member you are looking for does not exist.");
            return "error";
        }
    }


    @PostMapping("/register")
    public String registerMember(@ModelAttribute Member member, BindingResult result, Model model) {
        log.info("Inside registerMember controller method ...");
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Please correct the errors in the form.");
            return "error";
        }

        if (memberService.emailExists(member.getEmail())) {
            log.warn("Email already exists: {}", member.getEmail());
            model.addAttribute("errorMessage", "Email is already registered.");
            return "error";
        }
        memberService.registerMember(member);
        model.addAttribute("successMessage", "Member Registered Successfully!");

        return "registrationSuccess";
    }

}

