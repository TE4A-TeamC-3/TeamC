package jp.te4a.spring.boot.myapp10;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//HTTPアクセス(URL)の対応を記述
@Controller
@RequestMapping("books")// /boosにアクセスされた時のコントローラ
public class BookController {
    @Autowired
    BookService bookService;
    
    @ModelAttribute //戻り値は、各コントローラ処理の前に自動でModelに追加される
    BookForm setUpForm(){
        return new BookForm();
    }
    
    // /booksにGET要求
    @GetMapping
    String list(Model model){
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    // /books/createにPOST要求
    @PostMapping(path="create")
    String create(BookForm form, Model model){
        bookService.create(form);
        return "redirect:/books";
    }
    

    // /books/ceditにパラメータformを含むPOST要求
    @PostMapping(path="edit", params="form")
    String editForm(@RequestParam Integer id, BookForm form){
        BookForm bookForm = bookService.findOne(id);
        BeanUtils.copyProperties(bookForm, form);
        return "books/edit";
    }

    // /books/にPOST要求
    @PostMapping(path="edit")
    String edit(@RequestParam Integer id, BookForm form){
        bookService.update(form);
        return "redirect:/books";
    }

    // /books/deleteにPOST要求
    @PostMapping(path="delete")
    String edit(@RequestParam Integer id){
        bookService.delete(id);
        return "redirect:/books";
    }

    // /books/editにパラメータgoToTopを含むPOST要求
    @PostMapping(path="edit",params="goToTop")
    String goToTop(){
        return "redirect:/books";
    }
}
