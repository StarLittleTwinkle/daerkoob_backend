package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Member;
import com.project.daerkoob.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/member")
    public Member getTest(@RequestBody Member member){
        return memberRepository.save(member);
    }//@RequestBody는 key와 일치되는 field를 연결시켜주는 annotation

    @GetMapping("/member/{id}") //@PathVariable 은 아마 path에 명시된 것을 가져오는 듯
    public String read(@PathVariable Long id){
        Optional<Member> memberOptional = memberRepository.findById(id);
        memberOptional.ifPresent(System.out::println); //ifPresent 는 null인지 아닌지 확인하는 것이다.
        return "successfully executed";
    }

    @GetMapping("check/{id}")
    public Member check(@PathVariable Long id){
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member resultMember= memberOptional.get();
        System.out.println(resultMember.getId());
        System.out.println(resultMember.getMemberId());
        System.out.println(resultMember.getAdmin());
        System.out.println(resultMember.getBirth());
        System.out.println(resultMember.getName());
        System.out.println(resultMember.getPassword());
        System.out.println(resultMember.getFriend());
        return resultMember;
    }
}
