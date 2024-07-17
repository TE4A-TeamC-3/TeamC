package jp.te4a.spring.boot.teamc.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usercode")
//ユーザ名、パスワードを持つDB用ユーザクラス(DAO)
public class UserBean {
    //主キー
    @Id
    private String username;

    //パスワード(Json出力しない)
    @JsonIgnore
    private String password;
}
