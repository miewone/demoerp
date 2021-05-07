package com.example.demoerp.config;

import com.example.demoerp.home.user.UserEntity;
import com.example.demoerp.home.user.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements InitializingBean {
    @Autowired
    private UserService userService;

    private static String PREFIXROLE = "ROLE_";

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!userService.findUser("admin").isPresent())
        {
            UserEntity user = userService.save(UserEntity.builder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("1234"))
                    .enabled(true)
                    .position("")
                    .Tel("010-1234-1234")
                    .build());
            userService.addAuthority(user.getUserId(),PREFIXROLE+"ADMIN");
        }
    }
}
