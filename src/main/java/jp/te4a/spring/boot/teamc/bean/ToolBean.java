package jp.te4a.spring.boot.teamc.bean;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@Table(name="list_display_code") // 対応するDBのテーブル名を指定
@Data
@AllArgsConstructor
@NoArgsConstructor // 引数なしコンストラクタを自動生成
public class ToolBean {

    @Id // フィールドを主キーとしてマーク
    @Column(name = "id") // NOT NULL制約
    @GeneratedValue(strategy = GenerationType.IDENTITY) // データベースによって自動インクリメント
    private int id;
    
    @Column(name = "managementcode") // NOT NULL制約
    private String managementcode;  //管理コード
    
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // データベースによって自動インクリメント
    @Column(name = "management_no") // NOT NULL制約
    private int managementNo; //管理番号
    
    @Column(name = "product_name") // NOT NULL制約
    private String productName; //品名
    
    @Column(name = "model_number") // NOT NULL制約
    private String modelNumber; //型番
    
    @Column(name = "maker") // NOT NULL制約
    private String maker; //メーカー
    
    @Column(name = "purchase_date") // NOT NULL制約
    private Date purchaseDate; //購入日 yyyy-mm-dd
    
    @Column(name = "service_life") // NOT NULL制約
    private String serviceLife; //耐用年数
    
    //@Column(name = "depreciation")
    //private String depreciation; //減価償却
    
    @Column(name = "usage_prohibited") //NOT NULL制約
    private boolean usageProhibited; //使用不可
    
    @Column(name = "available_for_rent") //NOT NULL制約
    private boolean availableForRent; //貸出状況
    
    @Column(name = "installation_location") //NOT NULL制約
    private String installationLocation; //設置場所 本社 仙台 埼玉 新潟 大阪
    
    @Column(name = "expiration_date")
    private Date expirationDate; // 貸出期限

    
    @Column(name = "specification")
    private String specification; //仕様

    public LocalDate calculateExpirationDate() {
        int serviceLifeInMonths = Integer.parseInt(serviceLife.substring(serviceLife.length() - 2, serviceLife.length() - 1)) * 12;
        LocalDate purchaseDate = LocalDate.parse(purchaseDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 購入日が String の場合
        return purchaseDate.plusMonths(serviceLifeInMonths);
    }
}