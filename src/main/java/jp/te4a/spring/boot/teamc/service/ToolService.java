package jp.te4a.spring.boot.teamc.service;

import java.util.ArrayList;
import java.util.Collections;
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
    /*public List<ToolForm> findAll() {//受け取り側がない
        List<ToolBean> beanList = toolRepository.findAll();
        List<ToolForm> formList = new ArrayList<ToolForm>();
        for(ToolBean toolBean: beanList) {
            ToolForm toolForm = new ToolForm();
            BeanUtils.copyProperties(toolBean, toolForm);
            formList.add(toolForm);
        }
            return formList;
        }*/
        public List<ToolForm> findAll() {
            List<ToolBean> beanList = toolRepository.findAll();
            // 通常はfindAllがnullを返すことはないが、安全策としてチェックする
            if (beanList == null) {
                beanList = Collections.emptyList();
            }
        
            List<ToolForm> formList = new ArrayList<>();
            for (ToolBean toolBean : beanList) {
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
        opt.ifPresent(toolBean -> BeanUtils.copyProperties(toolBean, toolForm));
        return toolForm;
    }
    
    // 検索機能
    public List<ToolForm> searchByProductName(String keyword) {
        List<ToolBean> toolBeans = toolRepository.findByProductNameContainingIgnoreCase(keyword);
        List<ToolForm> toolForms = new ArrayList<>();
        for (ToolBean toolBean : toolBeans) {
            ToolForm toolForm = new ToolForm();
            BeanUtils.copyProperties(toolBean, toolForm);
            toolForms.add(toolForm);
        }
        return toolForms;
    }
    
}
