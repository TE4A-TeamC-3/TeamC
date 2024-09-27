package jp.te4a.spring.boot.teamc.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usercode")
//ユーザ名、パスワードを持つDB用ユーザクラス
public class UserBean {
    //主キー
    @Id
    @Column(name = "user_no") // NOT NULL制約
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNo;
    private String username;

    //パスワード(Json出力しない)
    @JsonIgnore
    private String password;
    private String role; //ADMIN, USER
}