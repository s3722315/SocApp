package com.sept.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

//  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
//
////  static {
////    inMemoryUserList.add(new JwtUserDetails(1L, "sept",
////        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
////  }
//
//    static {
//        inMemoryUserList.add(new JwtUserDetails(1L, "sept",
//                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e"));
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


