package zzyzzy.springboot.semiprojectv7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.service.PdsService;

@Controller
@RequestMapping("/pds")
public class PdsController {

    @Autowired
    PdsService pdssrv;

    @GetMapping("/list")
    public String list() {
        return "pds/list";
    }

    @GetMapping("/write")
    public String write(Model m){
        m.addAttribute("pds", new Pds()); // validation을 위한 첫번때 코드
        return "pds/write";
    }

    @PostMapping("/write")
    public String writeok(Pds pds, MultipartFile attach){  //pds, attach 가져옴
        String viewPage = "error";

        int pno = pdssrv.newPds(pds);
         if (pdssrv.newPdsAttach(attach, pno))
             viewPage = "redirect:/pds/list";

        return viewPage;
    }


}
