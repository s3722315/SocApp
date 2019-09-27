package com.sept.rest.webservices.restfulwebservices.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtUserDetailsJpaRepository extends JpaRepository<JwtUserDetails, Long> {
    List<JwtUserDetails> findByUsername(String username);

}