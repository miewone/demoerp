package com.example.demoerp.home.user;

import com.example.demoerp.config.BaseTimeEntity;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name="user_id"))
    private Set<UserAuthority> authorities;

    @Column(unique = true,nullable = false)
    private String username;
    @Column(unique = true,nullable = false)
    private String password;
    private String department;
    private String position;
    private String Bnumber;//Business Number
    private String Tel;
    private boolean enabled;


    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }





}
