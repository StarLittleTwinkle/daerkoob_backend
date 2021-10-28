package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
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

//    @GetMapping("check/{id}")
//    public User check(@PathVariable Long id){
//        Optional<User> userOptional = userRepository.findById(id);
//        User resultUser = userOptional.get();
//        return resultUser;
//    }

    @GetMapping("signup")
    public Message signup(User user, String confirmPassword) {
        Message message;
        if (user.getUserId() == null) {
            message = new Message(false, "아이디를 입력하세요.");
            return message;
        } else if (user.getName() == null) {
            message = new Message(false, "이름을 입력하세요.");
            return message;
        } else if (user.getNickName() == null) {
            message = new Message(false, "닉네임을 입력하세요.");
            return message;
        } else if (user.getPassword() == null) {
            message = new Message(false, "비밀번호를 입력하세요.");
            return message;
        } else if (confirmPassword == null) {
            message = new Message(false, "비밀번호를 한번 더 입력하세요.");
            return message;
        } else if (user.getBirth() == null) {
            message = new Message(false, "생일을 입력하세요.");
            return message;
        } else if (user.getPassword().equals(confirmPassword)) {
            Optional<User> tempUser = userRepository.findByUserId(user.getUserId());
            User resultUser = tempUser.orElse(null);
            if(resultUser == null) {
                userRepository.save(user);
                message = new Message(true, "회원가입 성공");
                return message;
            }
            else {
                message = new Message(false, "이미 존재하는 회원입니다.");
                return message;
            }
        }
        else{
            message = new Message(false, "비밀번호를 다시 입력해주세요");
            return message;
        }
    }

    @PostMapping("login")
    public boolean login(User user){
        System.out.println(user.getUserId());
        System.out.println(user.getPassword());
        Optional<User> resultUser = userRepository.findByUserId(user.getUserId());
        User result = resultUser.orElse(null);
        System.out.println(result);
        if(result == null) {
            return false;
        }
        else{
            if(result.getPassword().equals(user.getPassword())){
                return true;
            }
            else{
                return false;
            }
        }
    }
}