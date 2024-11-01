package com.appl.kitchensink.repository;

import com.appl.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long id);
}
