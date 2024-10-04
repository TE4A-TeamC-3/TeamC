package jp.te4a.spring.boot.teamc.form;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToolForm {

    private int id;//主キー
    private String managementcode;  //管理コード
    private int managementNo; //管理番号
    private String productName; //品名
    private String modelNumber; //型番
    private String maker; //メーカー
    private Date purchaseDate; //購入日 yyyy-mm-dd
    private String serviceLife; //耐用年数
    //private int depreciation; //減価償却
    private boolean usageProhibited; //使用不可
    private boolean avaliableForRent; //貸出状況
    private String installationLocation; //設置場所 本社 仙台 埼玉 新潟 大阪
    private Date expirationDate; //貸出期限
    private String specification; //仕様
}