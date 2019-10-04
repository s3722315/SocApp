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

//    public ResponseEntity<Void> createUser(
//            @PathVariable String username, @PathVariable String password){
//
//        Student jwtUserDetails = new Student((long)2, username, password);
//
//        Student createdJwt = jwtUserDetailsJpaRepository.save(jwtUserDetails);
//
//        //Location
//        //Get current resource url
//        ///{id}
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(createdJwt.getId()).toUri();
//
//        return ResponseEntity.created(uri).build();
//    }

//  static List<Student> inMemoryUserList = new ArrayList<>();
//
//    static {
//        inMemoryUserList.add(new Student(1L, "sept",
//                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//    }
//
//    @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<Student> findFirst = inMemoryUserList.stream()
//        .filter(user -> user.getUsername().equals(username)).findFirst();
//
//    if (!findFirst.isPresent()) {
//      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//    }
//
//    return findFirst.get();
//  }

}


