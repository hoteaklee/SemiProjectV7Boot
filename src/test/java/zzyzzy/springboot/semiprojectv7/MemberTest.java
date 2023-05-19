package zzyzzy.springboot.semiprojectv7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zzyzzy.springboot.semiprojectv7.model.Member;
import zzyzzy.springboot.semiprojectv7.repository.MemberRepository;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("showall")
    public void findAllMember(){
        List<Member> mbs = memberRepository.findAll();
        System.out.println(mbs);
    }

    @Test
    @DisplayName("member save")
    public void saveMember(){
        Member m = new Member(null, "asd123","123456","1234567",
                "asd123","987qwe","123-456","서울시 구로구 구로동",
                "더조은 아카데미", "asd123@naver.com","010-5005-5050",
                null); //맴버 객체 생성

        memberRepository.save(m);
    }

    @Test
    @DisplayName("member update")
    public void updateMember(){
        Member m = new Member(2L, "987qwe","123456","1234567",
                "qwe987","987qwe","123-456","서울시 구로구 구로동",
                "더조은 아카데미", "asd123@naver.com","010-5005-5050",
                null); //맴버 객체 생성

        memberRepository.save(m);
    }

    @Test
    @DisplayName("member delete")
    public void deleteMember(){
        Member m = new Member();
        m.setMbno(2L); /* 타입이 Long 이여서 L를 붙임*/

        memberRepository.delete(m);
    }

    //로그인 테스트
    @Test
    @DisplayName("member login")
    public void loginMember(){
        Member m = new Member();
        m.setUserid("123");
        m.setPasswd("12");

        assertNull(memberRepository.findByUseridAndPasswd(m.getUserid(),m.getPasswd()) );

        m.setUserid("123");
        m.setPasswd("123");

        assertNotNull(memberRepository.findByUseridAndPasswd(m.getUserid(),m.getPasswd()) );
    }




}
