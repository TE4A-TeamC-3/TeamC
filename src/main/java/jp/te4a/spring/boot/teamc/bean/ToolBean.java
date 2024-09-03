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

@Entity
@Table(name = "listDisplayCode") // 対応するDBのテーブル名を指定
@Data
@AllArgsConstructor
@NoArgsConstructor // 引数なしコンストラクタを自動生成
public class ToolBean {

    @Id // フィールドを主キーとしてマーク
    @Column(name = "id") // カラム名を指定し、NOT NULL制約
    @GeneratedValue(strategy = GenerationType.IDENTITY) // データベースによって自動インクリメント
    private int id;
    
    @Column(name = "managementcode") // カラム名を指定し、NOT NULL制約
    private String managementcode;  //管理コード
    
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // データベースによって自動インクリメント
    @Column(name = "managementNo") // カラム名を指定し、NOT NULL制約
    private int managementNo; //管理番号
    
    @Column(name = "productName") // カラム名を指定し、NOT NULL制約
    private String productName; //品名
    
    @Column(name = "modelNumber") // カラム名を指定し、NOT NULL制約
    private String modelNumber; //型番
    
    @Column(name = "maker") // カラム名を指定し、NOT NULL制約
    private String maker; //メーカー
    
    @Column(name = "purchaseDate") // カラム名を指定し、NOT NULL制約
    private Date purchaseDate; //購入日 yyyy-mm-dd
    
    @Column(name = "serviceLife") // カラム名を指定し、NOT NULL制約
    private int serviceLife; //耐用年数
    
    @Column(name = "depreciation") // カラム名を指定し、NOT NULL制約
    private int depreciation; //減価償却
    
    @Column(name = "usageProhibited") // カラム名を指定し、NOT NULL制約
    private boolean usageProhibited; //使用不可
    
    @Column(name = "availableForRent") // カラム名を指定し、NOT NULL制約
    private boolean availableForRent; //貸出可能
    
    @Column(name = "installationLocation") // カラム名を指定し、NOT NULL制約
    private String installationLocation; //設置場所 0=本社 1=仙台 2=埼玉 3=新潟　4=大阪
    
    @Column(name = "expirationDate") // カラム名を指定し、NOT NULL制約
    private Date expirationDate; //使用期限
    
    @Column(name = "specification") // カラム名を指定し、NOT NULL制約
    private String specification; //仕様
}
