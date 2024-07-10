package jp.te4a.spring.boot.teamc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jp.te4a.spring.boot.teamc.bean.UserBean;
import jp.te4a.spring.boot.teamc.form.UserForm;
import jp.te4a.spring.boot.teamc.repository.UserRepository;

//ユーザ登録処理(画面→DB)
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserForm create(UserForm userForm) {
        //ユーザ作成時にパスワードをエンコードする{
        userForm.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));

        //画面用ユーザ情報(Form) → DB用ユーザ情報(Bean)
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userForm, userBean);

        //ユーザをDBに追加
        userRepository.save(userBean);
        return userForm;
    }
}
