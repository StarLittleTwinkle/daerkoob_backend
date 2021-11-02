package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

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
    @PostMapping("signup")
    public Message signUp(User user, String confirmPassword){
        if (user.getUserId() == null || user.getUserId().length() == 0) {
            return new Message(false, "아이디를 입력하세요.");
        } else if (user.getName() == null || user.getName().length() == 0) {
            return new Message(false, "이름을 입력하세요.");
        } else if (user.getNickName() == null || user.getNickName().length() == 0) {
            return new Message(false, "닉네임을 입력하세요.");
        } else if (user.getPassword() == null || user.getPassword().length() == 0) {
            return new Message(false, "비밀번호를 입력하세요.");
        } else if (confirmPassword == null || confirmPassword.length() == 0) {
            return new Message(false, "비밀번호를 한번 더 입력하세요.");
        } else if (user.getBirth() == null) {
            return new Message(false, "생일을 입력하세요.");
        } else if (user.getPassword().equals(confirmPassword)) {
            Optional<User> findUserId = userService.findByUserId(user.getUserId());
            Optional<User> findNickName = userService.findByNickName(user.getNickName());
            User findUserIdResult = findUserId.orElse(null);
            User findNickNameResult = findNickName.orElse(null);
            if(findUserIdResult == null && findNickNameResult == null) {
                userService.save(user);
                return new Message(true, "회원가입 성공");
            }
            else if(findUserIdResult != null) {
                return new Message(false, "이미 존재하는 아이디입니다.");
            }
            else{
                return new Message(false , "이미 존재하는 닉네임입니다.");
            }
        }
        else{
            return new Message(false, "비밀번호를 다시 입력해주세요");
        }
    }

    @PostMapping("login")
    public boolean login(User user){
        Optional<User> resultUser = userService.findByUserId(user.getUserId());
        User result = resultUser.orElse(null);
        if(result == null) {
            return false;
        }
        else{
            if(result.getPassword().equals(user.getPassword())){
                return true;
            }
            else {
                return false;
            }
        }
    }
}