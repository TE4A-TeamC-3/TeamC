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
            //if (beanList == null) {
                //beanList = Collections.emptyList();
            //}

            ToolBean bean = new ToolBean();
            bean.setManagementcode("MC");
            bean.setManagementNo(6);
            bean.setProductName("説明テスト登録A01");
            java.util.Date jDate = new java.util.Date();
            java.sql.Date date = (java.sql.Date) jDate;
            bean.setExpirationDate(date);

            toolRepository.save(bean);

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
    public List<ToolForm> searchByProductName(String keyword) {
        List<ToolBean> toolBeans = toolRepository.findByProductNameContainingIgnoreCase(keyword);
        List<ToolForm> toolForms = new ArrayList<>();
        for (ToolBean toolBean : toolBeans) {
            ToolForm toolForm = new ToolForm();
            BeanUtils.copyProperties(toolBean, toolForm);
            toolForms.add(toolForm);
        }
        System.out.println("message_ToolService_searchByProductName");
        return toolForms;
    }
    
}
