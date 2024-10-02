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

    public void addTool(ToolForm toolForm) {
        // ToolFormのデータをエンティティに変換し、リポジトリを使って保存
        // 必要に応じてToolEntityを作成し、toolFormのデータを設定する
        ToolEntity toolEntity = new ToolEntity();
        toolEntity.setManagementCode(toolForm.getManagementcode());
        toolEntity.setManagementNo(toolForm.getManagementNo());
        toolEntity.setProductName(toolForm.getProductName());
        toolEntity.setModelNumber(toolForm.getModelNumber());
        toolEntity.setMaker(toolForm.getMaker());
        toolEntity.setPurchaseDate(toolForm.getPurchaseDate());
        toolEntity.setServiceLife(toolForm.getServiceLife());
        toolEntity.setUsageProhibited(toolForm.getUsageProhibited());
        toolEntity.setAvailableForRent(toolForm.getAvailableForRent());
        toolEntity.setInstallationLocation(toolForm.getInstallationLocation());
        toolEntity.setExprationDate(toolForm.getExprationDate());
        toolEntity.setSpecification(toolForm.getSpecification());

        toolRepository.save(toolEntity); // データベースに保存
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
   /*  public List<ToolForm> searchByProductName(String keyword) {
        List<ToolBean> toolBeans = toolRepository.findByProductNameContainingIgnoreCase(keyword);
        List<ToolForm> toolForms = new ArrayList<>();
        for (ToolBean toolBean : toolBeans) {
            ToolForm toolForm = new ToolForm();
            BeanUtils.copyProperties(toolBean, toolForm);
            toolForms.add(toolForm);
        }
        System.out.println("message_ToolService_searchByProductName");
        return toolForms;
    }*/
    
}