package zzyzzy.springboot.semiprojectv7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zzyzzy.springboot.semiprojectv7.model.Board;
import zzyzzy.springboot.semiprojectv7.model.Checkme;
import zzyzzy.springboot.semiprojectv7.model.Member;
import zzyzzy.springboot.semiprojectv7.service.JoinService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/join")
public class JoinController {

    @Autowired private JoinService jnsrv;

    @GetMapping("/agree")
    public String agree() {
         return "join/agree";
     }

    @GetMapping("/checkme")
    public String checkme(Model m) {
        m.addAttribute("checkme", new Checkme());
        return "join/checkme";
    }
    @PostMapping("/checkme")
    public String checkmeok(
            @Valid Checkme checkme, BindingResult br, HttpSession sess) {
        // checkme에서 작성한 이름,주민번호를 joinme에 보내는 방법 1
        //"redirect:/join/joinme?name=asd123&jumin1=123456&jumin2=1234567"   //보안상 문제~

        //checkme에서 작성한 이름,주민번호를 joinme에 보내는 방법 2 - session
        String viewPage = "redirect:/join/joinme";

       if (br.hasErrors())
           viewPage ="join/checkme";
       else
           sess.setAttribute("ckm", checkme);

        return viewPage;
    }

    @GetMapping("/joinme")
    public String joinme(Model m) {

        m.addAttribute("member", new Member());

        return "join/joinme";
    }
    @PostMapping("/joinme")
    public String joinmeok(
            @Valid Member member, BindingResult br, HttpSession sess) {
        String viewPage = "redirect:/join/joinok";

        if (br.hasErrors())
            viewPage = "join/joinme";
        else {
            jnsrv.newMember(member);
            sess.invalidate();  //세션제거
        }

        return viewPage;
    }

    @GetMapping("/joinok")
    public String joinok() {

        return "join/joinok";
    }

    // 우편번호 검색
    // /join/zipcode?dong=동이름
    // 검색결과는 뷰페이지 없이 바로 응답으로 출력 : RESTful 방식
    // 서블릿에서 제공하는 HttpServletResponse 객체를 이용하면
    // 스프링의 뷰리졸버 없이 바로 응답으로 출력할 수 있음
    // 단, 응답유형은 JSON 형식으로 함
    @ResponseBody
    @GetMapping("/zipcode")
    public void zipcode(String dong, HttpServletResponse res) {
        try {
            // 응답유형은 JSON으로 설정
            res.setContentType("application/json; charset=utf-8");
            // 검색된 우편번호 결과를 뷰없이 바로 응답으로 출력
            res.getWriter().print( jnsrv.findZipcode(dong) );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 아이디 사용가능여부 검사
    // /join/checkuid?uid=아이디
    // 사용가능   : 0
    // 사용불가능 : 1
    @ResponseBody
    @GetMapping("/checkuid")
    public void checkuid(String uid, HttpServletResponse res) {
        try {
            // 아이디 사용여부를 뷰없이 바로 응답으로 출력
            res.getWriter().print( jnsrv.checkUserid(uid) );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
