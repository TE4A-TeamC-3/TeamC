package jp.te4a.spring.boot.teamc.service;

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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // ここで一度だけインスタンス化

    public UserForm create(UserForm userForm) {
        // パスワードをエンコードする
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));

        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userForm, userBean);

        userRepository.save(userBean);
        return userForm;
    }

    //取得処理(全件)
    public List<UserForm> findAll() {
        List<UserBean> beanList = userRepository.findAll();
        List<UserForm> formList = beanList.stream().map(userBean -> {
            UserForm userForm = new UserForm();
            BeanUtils.copyProperties(userBean, userForm);
            return userForm;
        }).toList();
        return formList;
    }

    
    //取得処理(1件)
    public UserForm findOne(Integer userNo) {
        Optional<UserBean> opt = userRepository.findById(userNo);  // 修正箇所
        UserForm userForm = new UserForm();
        opt.ifPresent(user -> {
            BeanUtils.copyProperties(user, userForm);  // 修正：opt.get() を使わずに user を直接使う
        });
        return opt.map(user -> {
            BeanUtils.copyProperties(user, userForm);
            return userForm;
        }).orElse(null);  // 該当するユーザがいない場合は null を返す
    }


    public void delete(Integer userNo) {
        userRepository.deleteById(userNo);
    } 
}
