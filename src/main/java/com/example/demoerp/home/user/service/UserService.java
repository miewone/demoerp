package com.example.demoerp.home.user.service;

import com.example.demoerp.home.user.UserEntity;
import com.example.demoerp.home.user.UserAuthority;
import com.example.demoerp.home.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(userId).orElseThrow(() -> new UsernameNotFoundException(userId));
    }

    public UserEntity save(UserEntity user)
    {
        return userRepository.save(user);
    }

    public void addAuthority(Long userId,String authority)
    {
        userRepository.findById(userId).ifPresent( user -> {
            UserAuthority newRole = new UserAuthority(user.getUserId(),authority);
            if(user.getAuthorities() == null)
            {
                HashSet<UserAuthority> authorities = new HashSet<>();
                authorities.add(newRole);
                save(user);
            }else if(!user.getAuthorities().contains(newRole))
            {
                HashSet<UserAuthority> authorities = new HashSet<>();
                authorities.addAll(user.getAuthorities());
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            }

        });
    }

    public void removeAuthority(Long userId,String authority)
    {
        userRepository.findById(userId).ifPresent( user -> {
            if(user.getAuthorities() == null ) return;
            UserAuthority targetRole = new UserAuthority(user.getUserId(),authority);
            if(user.getAuthorities().contains(targetRole))
            {
                user.setAuthorities(
                        user.getAuthorities().stream().filter(auth -> !auth.equals(targetRole)).collect(Collectors.toSet())
                );
                save(user);
            }
        });
    }

    public Optional<UserEntity> findUser(String user1) {
        return userRepository.findUserByUsername(user1);
    }
}
