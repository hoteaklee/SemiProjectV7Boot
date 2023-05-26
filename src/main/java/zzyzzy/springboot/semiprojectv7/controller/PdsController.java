package zzyzzy.springboot.semiprojectv7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.model.PdsAttach;
import zzyzzy.springboot.semiprojectv7.model.PdsReply;
import zzyzzy.springboot.semiprojectv7.service.PdsService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pds")
public class PdsController {

    @Autowired
    PdsService pdssrv;

    @GetMapping("/list")
    public ModelAndView list(Integer cpg) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pds/list");
        if (cpg == null || cpg == 0) cpg = 1;

        // 자료실 게시글 읽어옴
        Map<String, Object> pds = pdssrv.readPds(cpg);

        // 게시글에 첨부파일 유형 읽어옴
        List<String> ftypes = pdssrv.readFtype();

        mv.addObject("pdslist", pds.get("pdslist") );
        mv.addObject("cpg", cpg);//현재페이지
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", pds.get("cntpg") );//총페이지
        mv.addObject("ftypes", ftypes );

        return mv;
    }

    @GetMapping("/write")
    public String write(Model m){
        m.addAttribute("pds", new Pds()); // validation을 위한 첫번때 코드
        return "pds/write";
    }

    @PostMapping("/write")
    public String writeok(Pds pds, MultipartFile attach){  //pds, attach 가져옴
        String viewPage = "error";

        Map<String, Object> pinfo = pdssrv.newPds(pds);

        if (!attach.isEmpty())  //첨부파일이 존재한다면 밑에 실행
            pdssrv.newPdsAttach(attach, pinfo);

        viewPage = "redirect:/pds/list";

        return viewPage;
    }

    @GetMapping("/view")
    public String view(int pno, Model m){

        m.addAttribute("pds",pdssrv.readOnePds(pno)); //본문내용
        m.addAttribute("attach",pdssrv.readOnePdsAttach(pno));  //첨부파일 내용
        m.addAttribute("rplist",pdssrv.readOnePdsReply(pno));  // 댓글 내용

        return "pds/view";
    }

    @GetMapping("/down")
    public ResponseEntity<Resource> down(int pno){

        // 업로드 파일의 uuid와 파일명 알아냄
        String uuid = pdssrv.readOnePds(pno).getUuid();
        String fname = pdssrv.readOnePdsAttach(pno).getFname();

        // 알아낸 uuid와 파일명을 이용해서 header와 리소스 객체 생성
        HttpHeaders header = pdssrv.getHeader(fname, uuid);
        UrlResource resource = pdssrv.getResource(fname, uuid);

        // 다운로드 수 증가
        pdssrv.downfile(pno);

        return ResponseEntity.ok().headers(header).body(resource); //리소스는 그파일의 내용을 바디로 보내줌
    }

    @PostMapping("/replyok")
    public String replyok(PdsReply reply ){
        String viewPage = "error";

        if (pdssrv.newReply(reply))
            viewPage = "redirect:/pds/view?pno=" + reply.getPno() ;

        return viewPage;
    }

}
