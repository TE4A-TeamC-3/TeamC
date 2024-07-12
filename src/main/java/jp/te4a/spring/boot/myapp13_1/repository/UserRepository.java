package jp.te4a.spring.boot.myapp13_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.spring.boot.myapp13_1.bean.UserBean;

//ユーザ登録処理(DB)
public interface UserRepository extends JpaRepository<UserBean, String> {
    
}
