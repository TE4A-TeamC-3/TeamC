package jp.te4a.spring.boot.teamc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import jp.te4a.spring.boot.teamc.bean.ToolBean;
import jp.te4a.spring.boot.teamc.bean.ToolForm;
import jp.te4a.spring.boot.teamc.bean.DeleteListBean;
import jp.te4a.spring.boot.teamc.repository.ToolRepository;
import jp.te4a.spring.boot.teamc.repository.DeleteListRepository;





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

    // 期限切れアイテムを毎日削除する処理
    @Scheduled(cron = "0 0 0 * * ?") // 毎日午前0時に実行
    public void deleteExpiredItems() {
    List<ToolBean> allItems = toolRepository.findAll();
    List<ToolBean> expiredItems = new ArrayList<>();

    System.out.println("耐用年数切れ開始");
    for (ToolBean item : allItems) {
        // ServiceLifeの後ろから2文字目を取得
        String serviceLife = item.getServiceLife();
        if (serviceLife != null && serviceLife.length() >= 2) {
            char monthChar = serviceLife.charAt(serviceLife.length() - 2);
            int monthsToAdd;

            // 2文字目を整数に変換し、12を掛ける
            monthsToAdd = Character.getNumericValue(monthChar) * 12;

            // purchaseDateに月数を足す
            LocalDate purchaseDate = item.getPurchaseDate();
            LocalDate expirationDate = purchaseDate.plusMonths(monthsToAdd);

            // 期限切れかどうかをチェック
            if (expirationDate.isBefore(LocalDate.now())) {
                expiredItems.add(item);

                // 削除するレコードを delete_list に追加
                DeleteListBean deletedItem = new DeleteListBean();
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
                deletedItem.setExpirationDate(item.getExpirationDate());
                deletedItem.setSpecification(item.getSpecification());

                deleteListRepository.save(deletedItem);
            }
        } else {
            System.out.println("無効なServiceLifeの値: " + serviceLife);
        }
    }

    // 期限切れアイテムを削除
    toolRepository.deleteAll(expiredItems);
}


    public List<ToolBean> findAllSorted(String sort, boolean ascending) {//並び替えの処理
    Sort sortOrder = ascending ? Sort.by(sort).ascending() : Sort.by(sort).descending();
    return toolRepository.findAll(sortOrder);
}

}