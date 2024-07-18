package jp.te4a.spring.boot.teamc.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import jp.te4a.spring.boot.teamc.validate.Writer;

@Data
@NoArgsConstructor
public class ToolForm {


    @NotNull
    private String managementcode;  //管理コード
    @NotNull
    private int managementNo; //管理番号
    @NotNull
    private String productName; //品名
    @NotNull
    private String modelNumber; //型番
    @NotNull
    private String maker; //メーカー
    @NotNull
    private Date purchaseDate; //購入日 yyyy-mm-dd
    @NotNull
    private int serviceLife; //耐用年数
    @NotNull
    private int depreciation; //減価償却
    @NotNull
    private boolean usageProhibited; //使用不可
    @NotNull
    private boolean availableForRent; //貸出可能
    @NotNull
    private String installationLocation; //設置場所 0=本社 1=仙台 2=埼玉 3=新潟　4=大阪
    @NotNull
    private Date exprationDate; //使用期限
    @NotNull
    private String specification; //仕様
}