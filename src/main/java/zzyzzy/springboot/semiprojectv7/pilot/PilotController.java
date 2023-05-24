package zzyzzy.springboot.semiprojectv7.pilot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

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
            String fname = attach.getOriginalFilename();
            // 업로드한 파일이름 알아내기
            m.addAttribute("filename", fname);
            // 업로드한 파일종류 알아내기
            m.addAttribute("filetype", attach.getContentType());
            // 업로드한 파일크기 알아내기
            m.addAttribute("filesize", attach.getSize()/1024);

            // 겹치치 않는 파일명 작성을 위해 유니크한 값 생성 1
            // 파일이름 + uuid + 확장자
            // abc.jpg -> abc21a13e01-908c-43a5-a9d5-1b83c22a7aca.jpg
            String uuid = UUID.randomUUID().toString().replace("-",""); //uuid에서  '-'제거
            m.addAttribute("uuid", uuid);

            // 파일이름과 확장자 분리하기
            String fileName =  fname.split("[.]")[0] ;
            String fileExt = fname.split("[.]")[1];

            // 겹치치 않는 파일명 작성을 위해 유니크한 값 생성 2
            uuid = LocalDate.now() + "" + LocalTime.now();
            uuid =  uuid.replace("-","")
                        .replace(":","")
                        .replace(".","");
            m.addAttribute("uuid", uuid);


            // 업로드한 파일 저장하기
            attach.transferTo(new File("C:/Java/bootUpload/" +  fileName + uuid + "." + fileExt));
        }


        return "pilot/list";
    }

    @GetMapping("/list")
    public String list(){
        return "pilot/list";
    }
}
