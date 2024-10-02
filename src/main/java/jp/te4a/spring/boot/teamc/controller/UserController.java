package jp.te4a.spring.boot.teamc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.spring.boot.teamc.form.UserForm;
import jp.te4a.spring.boot.teamc.service.UserService;

//ユーザー関係の制御
@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }
    
    @GetMapping
    String list(Model model) {
        return "users/add";
    }
    
    @PostMapping(path = "create")
    String create(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        userService.create(form);
        return "redirect:/users";
    }

    // /usersにパラメータformを含むPOST要求
    @GetMapping(path="users", params="form")
    String User(){
        System.out.println("user作成画面に戻る");
        return "users/add";
    }
    // /userにパラメータgoToTopを含むPOST要求
    @PostMapping(path="users1",params="goToTop")
    String goToTop(){
        System.out.println("アカウント作成画面からmessage_ToolController_list.htmlに戻る");
        return "redirect:/tools/list";
    }
}















/**
 *

    /*
    @GetMapping
    String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/add"; // users/add.html
    }

    @PostMapping(path = "create")
    String create(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        userService.create(form);
        return "redirect:/users";
    }

    // /users/deleteにPOST要求
    // ユーザー削除用のメソッド
    @PostMapping(path="delete")
    String delete(@RequestParam Integer userNo){
        userService.delete(userNo);
        return "redirect:/users";
    }
    }*/











/*ここから下、65号機までのUserControllerです。大幅に書き換えたいのでバックアップを残します。
１０月２日　by.やくわ。

package jp.te4a.spring.boot.teamc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.spring.boot.teamc.form.UserForm;
import jp.te4a.spring.boot.teamc.service.UserService;

//ユーザー関係の制御
@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }
    
    @GetMapping
    String list(Model model) {
        return "users/add";
    }
    
    @PostMapping(path = "create")
    String create(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        userService.create(form);
        return "redirect:/users";
    }

    // /usersにパラメータformを含むPOST要求
    @GetMapping(path="users", params="form")
    String User(){
        System.out.println("user作成画面に戻る");
        return "users/add";
    }
    // /userにパラメータgoToTopを含むPOST要求
    @PostMapping(path="users1",params="goToTop")
    String goToTop(){
        System.out.println("アカウント作成画面からmessage_ToolController_list.htmlに戻る");
        return "redirect:/tools";
    }
}
*/