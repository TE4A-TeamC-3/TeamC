package jp.te4a.spring.boot.teamc.repository;


import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.spring.boot.teamc.bean.UserBean;

//ユーザ登録処理(DB)
public interface UserRepository extends JpaRepository<UserBean, Integer> {
    Optional<UserBean> findByUsername(String username);
}
