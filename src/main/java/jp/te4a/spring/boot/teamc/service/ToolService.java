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
        BeanUtils.copyProperties(toolForm, toolBean);//toolBeanにtoolFormをコピーする
        toolRepository.save(toolBean);
        System.out.println("ToolService_create");
        return toolForm;
    }

    //更新処理
    public ToolForm update(ToolForm toolForm){
        ToolBean toolBean = new ToolBean();
        BeanUtils.copyProperties(toolForm, toolBean);
        toolRepository.save(toolBean);
        System.out.println("ToolService_update");
        return toolForm;
    }

    public void delete(int id) {
        toolRepository.deleteById(id);
        System.out.println("ToolService_delete");
    }


    public List<ToolForm> findAll() {
        List<ToolBean> beanList = toolRepository.findAll();
        System.out.println("message_ToolService_findAll_64:" + beanList.size());

        List<ToolForm> formList = new ArrayList<>();
        for (ToolBean toolBean : beanList) {
            ToolForm toolForm = new ToolForm();
            BeanUtils.copyProperties(toolBean, toolForm);
            formList.add(toolForm);
        }
        System.out.println("message_ToolService_findAll:" + formList.size());
        return formList;
    }
        
    
    //取得処理(1件)
    public ToolForm findOne(int id) {
        Optional<ToolBean> opt = toolRepository.findById(id);
        ToolForm toolForm = new ToolForm();
        opt.ifPresent(toolBean -> BeanUtils.copyProperties(toolBean, toolForm));
        System.out.println("message_ToolService_findOne");
        return toolForm;
    }
    
    // 検索機能
    // 検索を行うメソッド
    public List<ToolBean> searchTools(
            @RequestParam(value = "managementcode", required = false) String managementcode,
            @RequestParam(value = "managementNo", required = false) Integer managementNo,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "maker", required = false) String maker) {
            return toolRepository.findByConditions(managementcode, managementNo, productName, maker);
    }

    
}