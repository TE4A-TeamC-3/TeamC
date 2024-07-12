package jp.te4a.spring.boot.teamc.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.spring.boot.teamc.form.ToolForm;
import jp.te4a.spring.boot.teamc.service.ToolService;


//HTTPアクセス(URL)の対応を記述
@Controller
@RequestMapping("tools")// /boosにアクセスされた時のコントローラ
public class ToolController {
    @Autowired
    ToolService toolService;
    
    @ModelAttribute //戻り値は、各コントローラ処理の前に自動でModelに追加される
    ToolForm setUpForm(){
        return new ToolForm();
    }
    
    // /toolsにGET要求
    @GetMapping
    String list(Model model){
        model.addAttribute("tools", toolService.findAll());
        return "tools/list";
    }

    // /tools/createにPOST要求
    @PostMapping(path="create")
    String create(@Validated ToolForm form, BindingResult result , Model model){
        if(result.hasErrors()){
            return list(model);
        }
        
        toolService.create(form);
        return "redirect:/tools";
    }
    

    // /tools/ceditにパラメータformを含むPOST要求
    @PostMapping(path="edit", params="form")
    String editForm(@RequestParam int kanribangou, ToolForm form){
        ToolForm toolForm = toolService.findOne(kanribangou);
        BeanUtils.copyProperties(toolForm, form);
        return "tools/edit";
    }

    // /tools/にPOST要求
    @PostMapping(path="edit")
    String edit(@RequestParam int kanribangou, @Validated ToolForm form, BindingResult result , Model model){
        if(result.hasErrors()){
            return editForm(kanribangou, form);
        }
        
        toolService.update(form);
        return "redirect:/tools";
    }

    // /tools/deleteにPOST要求
    @PostMapping(path="delete")
    String edit(@RequestParam int kanribangou){
        toolService.delete(kanribangou);
        return "redirect:/tools";
    }

    // /tools/editにパラメータgoToTopを含むPOST要求
    @PostMapping(path="edit",params="goToTop")
    String goToTop(){
        return "redirect:/tools";
    }
}
