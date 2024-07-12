package jp.te4a.spring.boot.myapp13_1.security;

import java.util.Collection;
//import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jp.te4a.spring.boot.myapp13_1.bean.UserBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

// パスワードや権限を含む形式でユーザ情報を保持する
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginUserDetails extends User{
    
    // 認証に使うユーザクラス
    private final UserBean user;

    // 認証ユーザ作成(コンストラクタ)
    public LoginUserDetails(UserBean userBean, 
        boolean accontNonExpried, // アカウント無効
        boolean credenttialsNonExpired, // 認証無効
        boolean accountNonLocked, // ロック状態を設定可能(今回は該当なしtrue)
        Collection<GrantedAuthority> authorities){ // 認証情報は呼び出し時にリストで設定
        super(userBean.getUsername(), userBean.getPassword(),true,true,true,true,authorities);
        this.user = userBean;
    }
}