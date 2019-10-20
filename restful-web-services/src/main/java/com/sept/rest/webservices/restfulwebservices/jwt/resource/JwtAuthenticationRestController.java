package com.sept.rest.webservices.restfulwebservices.jwt.resource;

import java.net.URI;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.sept.rest.webservices.restfulwebservices.jwt.StudentJpaRepository;
import com.sept.rest.webservices.restfulwebservices.jwt.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JwtAuthenticationRestController { // main class for handling user accounts

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService jwtInMemoryUserDetailsService;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private StudentJpaRepository studentJpaRepository;

  @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
  //receives JwtTokenRequest to create authentication token
      throws AuthenticationException {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

    final String token = jwtTokenUtil.generateToken(userDetails);

    logger.warn("USER_LOGGED_IN");

    return ResponseEntity.ok(new JwtTokenResponse(token));
  }

  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
	// refreshes the authentication token
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    Student user = (Student) jwtInMemoryUserDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @PutMapping("/jpa/new-account")
  public ResponseEntity<SignUpResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
	// receives SignUpRequest to add new user to the database
    logger.warn("USER_REGISTERED");
    logger.warn(signUpRequest.getUsername());
    logger.warn(signUpRequest.getPassword());
    if(studentJpaRepository.existsByUsername(signUpRequest.getUsername()) || signUpRequest.getUsername().length() >= 20) {
      logger.warn("USERNAME_ALREADY_EXISTS");
      return new ResponseEntity<SignUpResponse>(new SignUpResponse(false, "Username is already taken!"),
              HttpStatus.BAD_REQUEST);
    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Creating user's account
    long count = studentJpaRepository.count();
    Student student = new Student((count + 1), signUpRequest.getUsername(), signUpRequest.getPassword());
    student.setPassword(passwordEncoder.encode(student.getPassword()));

    Student result = studentJpaRepository.save(student);

    URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/api/users/{username}")
            .buildAndExpand(result.getUsername()).toUri();


    return ResponseEntity.created(location).body(new SignUpResponse(true, "User registered successfully"));
  }

  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) { // handle invalid credentials
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  private void authenticate(String username, String password) {  // authenticate credentials with the datastorage
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
  }
}

