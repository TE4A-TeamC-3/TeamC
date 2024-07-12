package jp.te4a.spring.boot.teamc.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import jp.te4a.spring.boot.teamc.validate.Writer;

@Data
@NoArgsConstructor
public class ToolForm {


    @NotNull
    private char kanricode;  //管理コード
    @NotNull
    private int kanribangou; //管理番号
    @NotNull
    private String hinmei; //品名
    @NotNull
    private String kataban; //型番
    @NotNull
    private String maker; //メーカー
    @NotNull
    private String kounyubi; //購入日 yyyy-mm-dd
    @NotNull
    private String taiyounensuu; //耐用年数
    @NotNull
    private Integer genkasyoukyaku; //減価償却
    @NotNull
    private boolean siyouhuka; //使用不可
    @NotNull
    private boolean kasidasikanou; //貸出可能
    @NotNull
    private boolean settibasyo; //設置場所 0=本社 1=仙台 2=埼玉 3=新潟　4=大阪
    @NotNull
    private String siyoukigen; //使用期限
    @NotNull
    private String siyou; //仕様
}