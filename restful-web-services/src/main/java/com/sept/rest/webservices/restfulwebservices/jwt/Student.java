package com.sept.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.sept.rest.webservices.restfulwebservices.course.Course;
import com.sept.rest.webservices.restfulwebservices.course.Enrolment;
import com.sept.rest.webservices.restfulwebservices.todo.Todo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Student implements UserDetails {

  private static final long serialVersionUID = 5155720064139820502L;


  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  private String username;
  private String password;

  @OneToMany(mappedBy = "student")
  Set<Enrolment> enrolments;

//  @OneToMany(mappedBy = "jwt_user_details")
//  private List<Todo> todos;

//  @ManyToMany
//  @JoinTable(
//          name = "student_course",
//          joinColumns = @JoinColumn(name = "jwt_user_details_id"),
//          inverseJoinColumns = @JoinColumn(name = "course_id"))
//  Set<Course> enrolledCourses;

  @Transient
  private final Collection<? extends GrantedAuthority> authorities;

  public Student(Long id, String username, String password, String role) {
    this.id = id;
    this.username = username;
    this.password = password;

    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(role));

    this.authorities = authorities;
  }

  public Student(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = null;
  }

  protected Student(){
    this.authorities = null;
  }

  @JsonIgnore
  public Long getId() {
    return id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) { this.username = username; }

  //public Set<Course> getCourses() { return enrolledCourses; }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) { this.password = password; }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}

