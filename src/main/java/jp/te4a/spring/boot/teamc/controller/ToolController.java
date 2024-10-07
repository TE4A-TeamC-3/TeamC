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
import jakarta.validation.Valid;



//HTTPアクセス(URL)の対応を記述
@Controller
@RequestMapping("tools")// /toolsにアクセスされた時のコントローラ
public class ToolController {

    @Autowired
    ToolService toolService;

    @ModelAttribute //toolsFormを返す
    ToolForm setUpForm(){
        System.out.println("message_ToolController_setUpForm");
        return new ToolForm();
    }
    
    // /toolsにGET要求
    @GetMapping
    String list(Model model){
        List<ToolForm> toolForms = toolService.findAll();
        model.addAttribute("tools", toolForms);
        System.out.println("message_ToolController_toolsGet要求");
        return "tools/list";
    }

    // 備品登録
     //tools/createにパラメータformを含むPOST要求
    @GetMapping(path="create")
    public String createForm(Model model) {
         // 新しい ToolForm オブジェクトをモデルに追加
        model.addAttribute("toolForm", new ToolForm());
         // create.html に遷移
         return "tools/create/create"; // toolsフォルダ内のcreateフォルダにあるcreate.htmlを返す
    }

    // POSTリクエストで新規登録を処理するメソッド
    @PostMapping(path="create")
    public String createTool(@Valid ToolForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tools/create/create"; // エラーがあれば再度入力画面を表示
        }
        // ToolServiceを使って新しいツールを追加
        toolService.create(form);
        return "redirect:/tools/create";
    }

    // 編集
    // /tools/editにパラメータformを含むPOST要求
    @PostMapping(path="edit")
    public String editForm(@RequestParam int id, ToolForm form){ //publicを追加した
        System.out.println("message_ToolController_編集 取得ID" + id);
        ToolForm toolForm = toolService.findOne(id);
        BeanUtils.copyProperties(toolForm, form);
        System.out.println("message_ToolController_編集用form取得");
        return "tools/edit/edit";
    }

    // /tools/にPOST要求
    @PostMapping(path="editForm")
    public String edit(@RequestParam int id, ToolForm form, Model model) {
        toolService.update(form);
        System.out.println("message_ToolController_編集終了後toolsに返す");
        return "redirect:/tools";
    }

    // 削除
    // /tools/deleteにPOST要求
    @PostMapping(path="delete")
    public String delete(@RequestParam int id){
        toolService.delete(id);
        System.out.println("message_ToolController_delete");
        return "redirect:/tools";
    }

    
    //備品検索画面へ遷移
    @GetMapping(path="search")
    public String serach(){
        System.out.println("一覧画面（list.html）から検索画面(search.html)へ");
        return "tools/search/search"; // toolsフォルダ内のsearchフォルダにあるsearch.htmlを返す
    }

    //検索を行う処理
    @PostMapping(path="search", params="form")
    public String searchForm(@ModelAttribute ToolForm form, Model model){

        List<ToolBean> searchResults = toolService.searchTools(
            form.getManagementcode(),
            form.getManagementNo(),
            form.getproductName(),
            form.getmaker()
        );

        model.addAttribute("searchResults", searchResults);
        System.out.println("message_ToolController_検索用form取得");
        return "tools/search/search";
    }

    // 検索機能の追加
    /*  @GetMapping("search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<ToolForm> tools = toolService.searchByProductName(keyword);
        model.addAttribute("tools", tools);
        System.out.println("message_ToolController_keyword");
        return "tools/search"; // 検索結果を表示するテンプレート
    }*/

    // /tools/createにパラメータgoToTopを含むPOST要求
    @PostMapping(path="create",params="goToTop1")
    String goToTop1(){
        System.out.println("作成画面からmessage_ToolController_list.htmlに戻る");
        return "redirect:/tools";
    }
    // /tools/editにパラメータgoToTopを含むPOST要求
    @PostMapping(path="edit",params="goToTop2")
    String goToTop2(){
        System.out.println("編集画面からmessage_ToolController_list.htmlに戻る");
        return "redirect:/tools";
    }
    // /tools/searchにパラメータgoToTopを含むPOST要求
    @PostMapping(path="search",params="goToTop3")
    String goToTop3(){
        System.out.println("編集画面からmessage_ToolController_list.htmlに戻る");
        return "redirect:/tools";
    }

    //一覧画面へ戻ってくる処理
    @GetMapping(path="gotop")
    String goToTopM(){
        System.out.println("一覧画面（list.html）に戻る");
        return "redirect:/tools";
    }
}