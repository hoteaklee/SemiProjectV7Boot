package zzyzzy.springboot.semiprojectv7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zzyzzy.springboot.semiprojectv7.model.Gallery;
import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.service.GalleryService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    GalleryService galsrv;

    @GetMapping("/list")
    public ModelAndView list(Integer cpg) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("gallery/list");
        if (cpg == null || cpg == 0) cpg = 1;

        Map<String, Object> gals = galsrv.readGallery(cpg);

        mv.addObject("gallist", gals.get("gallist") );
        mv.addObject("cpg", cpg);//현재페이지
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", gals.get("cntpg") );//총페이지

        return mv;
    }

    @GetMapping("/write")
    public String write(Model m){
        m.addAttribute("gallery", new Gallery()); // validation을 위한 첫번때 코드
        return "gallery/write";
    }

    @PostMapping("/write")
    public String writeok(Gallery gallery, List<MultipartFile> attachs){  //다중첨부파일은 리스트에 여러파일저장
        String viewPage = "error";
        //System.out.println("어테치출력" + attachs);
        if (!attachs.isEmpty()) { //첨부파일이 존재한다면 밑에 실행
            Map<String, Object> ginfo = galsrv.newGallery(gallery);
            galsrv.newGalAttach(attachs, ginfo);

            viewPage = "redirect:/gallery/list";
        }
        return viewPage;
    }

}
