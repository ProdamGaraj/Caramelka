package com.example.Caramelca.models.Admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
@RequiredArgsConstructor
@Setter
@Getter
//public class Staff implements UserDetails {
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "staff_role", joinColumns = @JoinColumn(name = "staff_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Staff(String phone,
                 String password,
                 String name,
                 String surname,
                 String patronymic,
                 Set<Role> roles) {
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.roles = roles;
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
}
