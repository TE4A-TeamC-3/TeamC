package jp.te4a.spring.boot.teamc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.te4a.spring.boot.teamc.bean.ToolBean;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<ToolBean, Integer> {
    // 新しいDB操作を作成する場合
    @Query("SELECT X FROM BookBean X ORDER BY X.title") // JPQLクエリを記述
    List<ToolBean> findAllOrderByTitle(); // メソッド名
    List<ToolBean> findByTitleContainingIgnoreCase(String keyword);//検索用
}
