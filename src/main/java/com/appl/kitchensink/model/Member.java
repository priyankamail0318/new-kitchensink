package com.appl.kitchensink.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
@Data
public class Member {

    @Id
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public Member(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Member() {
        // Default constructor
    }
}
