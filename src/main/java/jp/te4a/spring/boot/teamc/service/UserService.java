package jp.te4a.spring.boot.teamc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //取得処理(全件)
    public List<UserForm> findAll() {
        List<UserBean> beanList = userRepository.findAll();
        List<UserForm> formList = new ArrayList<UserForm>();
        for(UserBean userBean: beanList) {
            UserForm userForm = new UserForm();
            BeanUtils.copyProperties(userBean, userForm);
            formList.add(userForm);
        }
            return formList;
    }
    
    //取得処理(1件)
    public UserForm findOne(Integer userNo) {
        Optional<UserBean> opt = userRepository.findById(userNo);  // 修正箇所
        UserForm userForm = new UserForm();
        opt.ifPresent(user -> {
            BeanUtils.copyProperties(user, userForm);  // 修正：opt.get() を使わずに user を直接使う
        });
    return userForm;
}


    public void delete(Integer userNo) {
        userRepository.deleteById(userNo);
    } 
}
