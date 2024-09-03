package jp.te4a.spring.boot.teamc.controller;

import java.util.List;

//import org.hibernate.mapping.List;
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
        List<ToolForm> toolForms = toolService.findAll();
        model.addAttribute("tools", toolForms);
        return "tools";
        //return "tools/list";
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
    String editForm(@RequestParam int id, ToolForm form){
        ToolForm toolForm = toolService.findOne(id);
        BeanUtils.copyProperties(toolForm, form);
        return "tools/edit";
    }

    // /tools/にPOST要求
    @PostMapping(path="edit")
    String edit(@RequestParam int id, @Validated ToolForm form, BindingResult result , Model model){
        if(result.hasErrors()){
            return editForm(id, form);
        }
        
        toolService.update(form);
        return "redirect:/tools";
    }

    // /tools/deleteにPOST要求
    @PostMapping(path="delete")
    String edit(@RequestParam int id){
        toolService.delete(id);
        return "redirect:/tools";
    }

    // /tools/editにパラメータgoToTopを含むPOST要求
    @PostMapping(path="edit",params="goToTop")
    String goToTop(){
        return "redirect:/tools";
    }

    // 検索機能の追加
    @GetMapping("search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<ToolForm> tools = toolService.searchByProductName(keyword);
        model.addAttribute("tools", tools);
        return "tools/search"; // 検索結果を表示するテンプレート
    }
}