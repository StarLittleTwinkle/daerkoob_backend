package com.project.daerkoob;

import com.project.daerkoob.repository.UserRepository;
import com.project.daerkoob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final UserRepository userRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService(){ // @Bean을 통해서 userService와 userRepository와의 dependency를 형셩
        return new UserService(userRepository);
    }
}
