package com.sept.rest.webservices.restfulwebservices.jwt;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    private StudentJpaRepository studentJpaRepository;

    public JwtInMemoryUserDetailsService(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> jwtUserDetails = studentJpaRepository.findByUsername(username);

        if (jwtUserDetails == null) {
            throw new UsernameNotFoundException(username);
        }
        return jwtUserDetails.get();
    }

}


