package com.example.demoerp.home.user;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_authority")
@IdClass(UserAuthority.class)
public class UserAuthority implements GrantedAuthority {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(length = 20)
    private String authority;
}
