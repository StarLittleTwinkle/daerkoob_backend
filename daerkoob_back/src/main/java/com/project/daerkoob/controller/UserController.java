package com.project.daerkoob.controller;

import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @PostMapping("member")
//    public User getTest(@RequestBody User user){
//        return userRepository.save(user);
//    }//@RequestBody는 key와 일치되는 field를 연결시켜주는 annotation

//    @GetMapping("member/{id}") //@PathVariable 은 아마 path에 명시된 것을 가져오는 듯
//    public String read(@PathVariable Long id){
//        Optional<User> memberOptional = userRepository.findById(id);
//        memberOptional.ifPresent(System.out::println); //ifPresent 는 null인지 아닌지 확인하는 것이다.
//        return "successfully executed";
//    }

    @GetMapping("check/{id}")
    public User check(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        User resultUser = userOptional.get();
        return resultUser;
    }

    @PostMapping("signup")
    public User signup(User user){
        userRepository.save(user);
        Optional<User> resultUser = userRepository.findById(user.getId());
        return resultUser.get();
    }

    @PostMapping("login")
    public boolean login(String userId ,String password){
        System.out.println(userId);
        System.out.println(password);
        Optional<User> resultUser = userRepository.findByUserId(userId);
        System.out.println(resultUser.get().getName() );
        User result = resultUser.orElse(null);
        System.out.println(result.getPassword() + "  " + password);
        if(result == null) {
            return false;
        }
        else{
            if(result.getPassword().equals(password)){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
