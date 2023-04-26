package by.tux.spring160.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController implements ErrorController {
    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }
    @GetMapping("/404")
    public String getErrorPage(){
        return "error";
    }
}