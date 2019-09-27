package com.sept.rest.webservices.restfulwebservices.jwt;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static java.util.Collections.emptyList;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    private JwtUserDetailsJpaRepository jwtUserDetailsJpaRepository;

    public JwtInMemoryUserDetailsService(JwtUserDetailsJpaRepository jwtUserDetailsJpaRepository) {
        this.jwtUserDetailsJpaRepository = jwtUserDetailsJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUserDetails> jwtUserDetails = jwtUserDetailsJpaRepository.findByUsername(username);

        if (jwtUserDetails == null) {
            throw new UsernameNotFoundException(username);
        }
        return jwtUserDetails.get();
    }

//    public ResponseEntity<Void> createUser(
//            @PathVariable String username, @PathVariable String password){
//
//        JwtUserDetails jwtUserDetails = new JwtUserDetails((long)2, username, password);
//
//        JwtUserDetails createdJwt = jwtUserDetailsJpaRepository.save(jwtUserDetails);
//
//        //Location
//        //Get current resource url
//        ///{id}
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(createdJwt.getId()).toUri();
//
//        return ResponseEntity.created(uri).build();
//    }

//  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
//
//    static {
//        inMemoryUserList.add(new JwtUserDetails(1L, "sept",
//                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//    }
//
//    @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//        .filter(user -> user.getUsername().equals(username)).findFirst();
//
//    if (!findFirst.isPresent()) {
//      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//    }
//
//    return findFirst.get();
//  }

}


