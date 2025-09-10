package org.top.baseconverterwebapp.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class IndexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("nowTime", LocalDateTime.now().toString());
        return "index";
    }
}
