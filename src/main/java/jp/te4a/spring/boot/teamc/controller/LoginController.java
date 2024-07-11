package jp.te4a.spring.boot.teamc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    
    //ログイン処理のパス loginForm.htmlに遷移
    @GetMapping(path="loginForm")
    String loginForm(){
        return "loginForm";
    }
    
}
