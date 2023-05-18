package zzyzzy.springboot.semiprojectv7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zzyzzy.springboot.semiprojectv7.model.Member;
import zzyzzy.springboot.semiprojectv7.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired private MemberService msrv;

    // 로그인 처리
    // 입력한 아이디/비밀번호로 로그인 가능여부 확인
    // 로그인 성공시 로그인 여부를 시스템에 저장하기 위해
    // HttpSession 객체를 이용함
    // 즉, 서버가 생성한 정보를 일정기간 동안
    // WAS에 저장해두고 필요할 때마다 이것을 활용함으로써
    // 로그인한 사용자를 식별할 수 있음
    @PostMapping("/login")
    public String login(Member m, HttpSession sess) {
        String returnPage = "redirect:/loginfail";

        if (msrv.checkLogin(m, sess)) {
            returnPage = "redirect:/";
        }

        return returnPage;
    }

    @GetMapping("/logout")
    public String logout(HttpSession sess) {
        sess.invalidate();

        return "redirect:/";
    }

}
