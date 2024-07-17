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
@RequestMapping("tools")// /toolsにアクセスされた時のコントローラ
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
    String editForm(@RequestParam int managementNo, ToolForm form){
        ToolForm toolForm = toolService.findOne(managementNo);
        BeanUtils.copyProperties(toolForm, form);
        return "tools/edit";
    }

    // /tools/にPOST要求
    @PostMapping(path="edit")
    String edit(@RequestParam int managementNo, @Validated ToolForm form, BindingResult result , Model model){
        if(result.hasErrors()){
            return editForm(managementNo, form);
        }
        
        toolService.update(form);
        return "redirect:/tools";
    }

    // /tools/deleteにPOST要求
    @PostMapping(path="delete")
    String edit(@RequestParam int managementNo){
        toolService.delete(managementNo);
        return "redirect:/tools";
    }

    // /tools/editにパラメータgoToTopを含むPOST要求
    @PostMapping(path="edit",params="goToTop")
    String goToTop(){
        return "redirect:/tools";
    }
        // 検索機能の追加
        @PostMapping(path = "search")
        String search(@RequestParam String keyword, Model model) {
            // 検索キーワードを使ってToolServiceで検索を実行し、結果を表示する
            model.addAttribute("tools", toolService.findByKeyword(keyword));
            return "tools/list"; // 検索結果を表示するテンプレート（list.html）を指定
        }
}
