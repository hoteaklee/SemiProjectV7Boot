package zzyzzy.springboot.semiprojectv7.pilot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/pilot")
public class PilotController {

    @GetMapping("/write")   // 입력폼
    public String write(){
        return "pilot/write";
    }

    @PostMapping("/write")  // 전송된 데이터 처리
    public String writeok(String title, String content,
                          MultipartFile attach, Model m) throws IOException {   //attach 네임은 write에 이름과 일치

        // 일반 폼 요소 처리
        m.addAttribute("title",title);
        m.addAttribute("content",content);

        // 멀티파트 폼 요소 처리
        if (attach.isEmpty())
            m.addAttribute("attach", "첨부파일이 없어요");
        else {
            // 업로드한 파일이름 알아내기
            m.addAttribute("filename", attach.getOriginalFilename());
            // 업로드한 파일종류 알아내기
            m.addAttribute("filetype", attach.getContentType());
            // 업로드한 파일크기 알아내기
            m.addAttribute("filesize", attach.getSize()/1024);

            // 업로드한 파일 저장하기
            attach.transferTo(new File("C:/Java/bootUpload/" + attach.getOriginalFilename()));
        }


        return "pilot/list";
    }

    @GetMapping("/list")
    public String list(){
        return "pilot/list";
    }
}
