package com.appl.kitchensink.controller;

import com.appl.kitchensink.model.Member;
import com.appl.kitchensink.repository.MemberRepository;
import com.appl.kitchensink.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("New Kitchen Sink loader service is Running ..."));
    }

    @Test
    void testListAllMembers() throws Exception {
        List<Member> members = Arrays.asList(new Member("John Doe", "test123#gmail.com", null), new Member("Jane Doe", null, null));
        when(memberService.getAllMembers()).thenReturn(members);

        mockMvc.perform(get("/getAllmembers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"))
                .andExpect(jsonPath("$[0].email").value("test123#gmail.com"));
    }

    @Test
    void testFindByMemberId_Success() throws Exception {
        Member member = new Member("John Doe", "johnDoe@gmail.com", "3456758754");
        when(memberService.findByMemberId(1L)).thenReturn(Optional.of(member));

        mockMvc.perform(get("/member/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testRegisterMember_Success() throws Exception {
        when(memberService.emailExists(anyString())).thenReturn(false);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Member Registered Successfully!"));

        verify(memberService).registerMember(any(Member.class));
    }

    @Test
    void testRegisterMember_EmailExists() throws Exception {
        when(memberService.emailExists("john.doe@example.com")).thenReturn(true);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com"))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email is already registered."));

        verify(memberService, never()).registerMember(any(Member.class));
    }


}
