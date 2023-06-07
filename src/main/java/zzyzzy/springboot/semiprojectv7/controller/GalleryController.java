package zzyzzy.springboot.semiprojectv7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import zzyzzy.springboot.semiprojectv7.model.Gallery;
import zzyzzy.springboot.semiprojectv7.model.Pds;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    @GetMapping("/list")
    public String list() {
        return "gallery/list";
    }

    @GetMapping("/write")
    public String write(Model m){
        m.addAttribute("gallery", new Gallery()); // validation을 위한 첫번때 코드
        return "gallery/write";
    }

    @PostMapping("/write")
    public String writeok(Gallery gallery, List<MultipartFile> attachs){  //다중첨부파일은 리스트에 여러파일저장
        String viewPage = "error";

//        Map<String, Object> pinfo = pdssrv.newPds(pds);
//
//        if (!attach.isEmpty())  //첨부파일이 존재한다면 밑에 실행
//            pdssrv.newPdsAttach(attach, pinfo);
//
//        viewPage = "redirect:/pds/list";

        return viewPage;
    }

}
