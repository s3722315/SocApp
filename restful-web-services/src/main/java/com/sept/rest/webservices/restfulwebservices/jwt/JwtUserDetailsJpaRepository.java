package com.sept.rest.webservices.restfulwebservices.jwt;

import io.jsonwebtoken.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JwtUserDetailsJpaRepository extends JpaRepository<JwtUserDetails, Long> {
    Optional<JwtUserDetails> findByUsername(String username);

}