package jp.te4a.spring.boot.teamc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.te4a.spring.boot.teamc.bean.ToolBean;
import jp.te4a.spring.boot.teamc.form.ToolForm;
import jp.te4a.spring.boot.teamc.repository.ToolRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.scheduling.annotation.Scheduled;



//画面寄りとしてtoolFormでデータを扱う
//Repositoryを使ったサービス(機能)を提供
@Service
public class ToolService {

    @Autowired
    ToolRepository toolRepository;

    @Autowired
    private DeleteListRepository deleteListRepository;


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
    public List<ToolBean> searchTools(String managementcode, Integer managementNo, String productName, String maker) {
        // 空の文字列をNULLに変換
        if (productName != null && productName.isEmpty()) {
            productName = null;
        }
        if (maker != null && maker.isEmpty()) {
            maker = null;
        }
        return toolRepository.findByConditions(managementcode, managementNo, productName, maker);
    }

    @Scheduled(cron = "0 0 0 * * ?") // 毎日午前0時に実行
    public void deleteExpiredItems() {
        List<ListDisplay> allItems = listDisplayRepository.findAll();
        List<ListDisplay> expiredItems = new ArrayList<>();

        for (ListDisplay item : allItems) {
            LocalDate expirationDate = item.calculateExpirationDate();
            if (expirationDate.isBefore(LocalDate.now())) {
                expiredItems.add(item);
                
                // 削除するレコードを delete_list に追加
                DeleteList deletedItem = new DeleteList();
                deletedItem.setManagementcode(item.getManagementcode());
                deletedItem.setManagementNo(item.getManagementNo());
                deletedItem.setProductName(item.getProductName());
                deletedItem.setModelNumber(item.getModelNumber());
                deletedItem.setMaker(item.getMaker());
                deletedItem.setPurchaseDate(item.getPurchaseDate());
                deletedItem.setServiceLife(item.getServiceLife());
                deletedItem.setUsageProhibited(item.getUsageProhibited());
                deletedItem.setAvailableForRent(item.getAvailableForRent());
                deletedItem.setInstallationLocation(item.getInstallationLocation());
                deletedItem.setExprationDate(item.getExprationDate());
                deletedItem.setSpecification(item.getSpecification());

                deleteListRepository.save(deletedItem);
            }
        }

        // 耐用年数を超えたアイテムを削除
        listDisplayRepository.deleteAll(expiredItems);
    }

    public List<Tool> findAllSorted(String sort) {
        Sort sortOrder = Sort.by(sort).ascending(); // 昇順で並び替え
        return toolRepository.findAll(sortOrder);
    }
}