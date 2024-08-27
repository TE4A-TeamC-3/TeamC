package jp.te4a.spring.boot.teamc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.spring.boot.teamc.bean.ToolBean;
import jp.te4a.spring.boot.teamc.form.ToolForm;
import jp.te4a.spring.boot.teamc.repository.ToolRepository;


//画面寄りとしてtoolFormでデータを扱う
//Repositoryを使ったサービス(機能)を提供
@Service
public class ToolService {

    @Autowired
    ToolRepository toolRepository;

    //追加処理 データはtoolFormで扱う　Repositoryを使うときはtoolBeanに入れる
    public ToolForm create(ToolForm toolForm) {
        ToolBean toolBean = new ToolBean();
        BeanUtils.copyProperties(toolForm, toolBean);
        toolRepository.save(toolBean);
        return toolForm;
    }
    
    //更新処理
    public ToolForm update(ToolForm toolForm){
        ToolBean toolBean = new ToolBean();
        BeanUtils.copyProperties(toolForm, toolBean);
        toolRepository.save(toolBean);
        return toolForm;
    }

    public void delete(int id) {
        toolRepository.deleteById(id);
    }    

    //取得処理(全件)
    public List<ToolForm> findAll() {//受け取り側がない
        List<ToolBean> beanList = toolRepository.findAll();
        List<ToolForm> formList = new ArrayList<ToolForm>();
        for(ToolBean toolBean: beanList) {
            ToolForm toolForm = new ToolForm();
            BeanUtils.copyProperties(toolBean, toolForm);
            formList.add(toolForm);
        }
            return formList;
        }
    
    //取得処理(1件)
    public ToolForm findOne(int id) {
        Optional<ToolBean> opt = toolRepository.findById(id);
        ToolForm toolForm = new ToolForm();
        opt.ifPresent(tool -> {
            BeanUtils.copyProperties(opt.get(), toolForm);
        });
        return toolForm;
    }
    
    //検索条件にあったレコードを取得
    public List<ToolBean> findByKeyword(String keyword) {
        // ToolRepositoryを使ってデータベースからキーワードに一致するツールを検索する
        return toolRepository.findContainingIgnoreCase(keyword);
    }
    
}
