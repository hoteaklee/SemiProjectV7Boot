package zzyzzy.springboot.semiprojectv7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pds")
public class PdsController {

    @GetMapping("/list")
    public String list() {
        return "pds/list";
    }

}
