package jp.te4a.spring.boot.teamc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import jakarta.persistence.*; 

@Entity
@Table(name = "listDisplayCode") // 対応するDBのテーブルを指定
@Data
@AllArgsConstructor
@NoArgsConstructor // 引数なしコンストラクタを自動生成
public class ToolBean {
    //一覧表示DB
    
    @Column(nullable = false) // NOT NULL制約
    private char[] managementcode;  //管理コード
    @Id // フィールドを主キーとしてマーク
    @GeneratedValue(strategy = GenerationType.IDENTITY) // データベースによって自動インクリメント
    @Column(nullable = false) // NOT NULL制約
    private int managementNo; //管理番号
    @Column(nullable = false) // NOT NULL制約
    private String productName; //品名
    @Column(nullable = false) // NOT NULL制約
    private String modelNumber; //型番
    @Column(nullable = false) // NOT NULL制約
    private String maker; //メーカー
    @Column(nullable = false) // NOT NULL制約
    private Date purchaseDate; //購入日 yyyy-mm-dd
    @Column(nullable = false) // NOT NULL制約
    private int serviceLife; //耐用年数
    @Column(nullable = false) // NOT NULL制約
    private int depreciation; //減価償却
    @Column(nullable = false) // NOT NULL制約
    private boolean usageProhibited; //使用不可
    @Column(nullable = false) // NOT NULL制約
    private boolean availableForRent; //貸出可能
    @Column(nullable = false) // NOT NULL制約
    private String installationLocation; //設置場所 0=本社 1=仙台 2=埼玉 3=新潟　4=大阪
    @Column(nullable = false) // NOT NULL制約
    private Date exprationDate; //使用期限
    @Column(nullable = false) // NOT NULL制約
    private String specification; //仕様
}
