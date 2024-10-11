package com.appl.kitchensink.repository;

import com.appl.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByEmail(String email);
}
