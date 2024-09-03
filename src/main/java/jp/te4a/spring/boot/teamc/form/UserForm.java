package jp.te4a.spring.boot.teamc.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//画面用ユーザクラス
public class UserForm {
    @NotNull
    private int userNo;
    private String username;

    private String password;

    private String role; //ADMIN, USER
}
