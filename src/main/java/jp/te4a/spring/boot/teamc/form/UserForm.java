package jp.te4a.spring.boot.teamc.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//画面用ユーザクラス
public class UserForm {
    @NotNull
    private String username;

    private String password;

    private String role; //ADMIN, USER
}
