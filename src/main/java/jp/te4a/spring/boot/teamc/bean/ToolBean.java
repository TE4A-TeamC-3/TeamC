package jp.te4a.spring.boot.teamc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "list_display_code") // 対応するDBのテーブル名を指定
@Data
@AllArgsConstructor
@NoArgsConstructor // 引数なしコンストラクタを自動生成
public class ToolBean {
    
    @Column(name = "management_code", nullable = false) // カラム名を指定し、NOT NULL制約
    private String managementcode;  //管理コード
    
    @Id // フィールドを主キーとしてマーク
    @GeneratedValue(strategy = GenerationType.IDENTITY) // データベースによって自動インクリメント
    @Column(name = "management_no", nullable = false) // カラム名を指定し、NOT NULL制約
    private int managementNo; //管理番号
    
    @Column(name = "product_name", nullable = false) // カラム名を指定し、NOT NULL制約
    private String productName; //品名
    
    @Column(name = "model_number", nullable = false) // カラム名を指定し、NOT NULL制約
    private String modelNumber; //型番
    
    @Column(name = "maker", nullable = false) // カラム名を指定し、NOT NULL制約
    private String maker; //メーカー
    
    @Column(name = "purchase_date", nullable = false) // カラム名を指定し、NOT NULL制約
    private Date purchaseDate; //購入日 yyyy-mm-dd
    
    @Column(name = "service_life", nullable = false) // カラム名を指定し、NOT NULL制約
    private int serviceLife; //耐用年数
    
    @Column(name = "depreciation", nullable = false) // カラム名を指定し、NOT NULL制約
    private int depreciation; //減価償却
    
    @Column(name = "usage_prohibited", nullable = false) // カラム名を指定し、NOT NULL制約
    private boolean usageProhibited; //使用不可
    
    @Column(name = "available_for_rent", nullable = false) // カラム名を指定し、NOT NULL制約
    private boolean availableForRent; //貸出可能
    
    @Column(name = "installation_location", nullable = false) // カラム名を指定し、NOT NULL制約
    private String installationLocation; //設置場所 0=本社 1=仙台 2=埼玉 3=新潟　4=大阪
    
    @Column(name = "expiration_date", nullable = false) // カラム名を指定し、NOT NULL制約
    private Date expirationDate; //使用期限
    
    @Column(name = "specification", nullable = false) // カラム名を指定し、NOT NULL制約
    private String specification; //仕様
}
