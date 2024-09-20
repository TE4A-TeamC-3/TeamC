package jp.te4a.spring.boot.teamc.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.spring.boot.teamc.bean.UserBean;
//ユーザ登録処理(DB)
public interface UserRepository extends JpaRepository<UserBean, Integer> {
    Optional<UserBean> findByUsername(String username);
}