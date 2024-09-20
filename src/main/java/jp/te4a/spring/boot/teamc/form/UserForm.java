package jp.te4a.spring.boot.teamc.form;

import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;

@Date
@NoArgsConstructor
//画面用ユーザクラス
public class UserForm {
    //@NotNull
    private Integer userNo;
    private String username;

    private String password;

    private String role; //ADMIN, USER
}