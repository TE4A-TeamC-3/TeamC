package jp.te4a.spring.boot.myapp13_1.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//画面用ユーザクラス
public class UserForm {
    @NotNull
    @Size(min=6, max=12)
    private String username;

    @Size(min=6, max=12)
    private String password;
}
