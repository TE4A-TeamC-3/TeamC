package jp.te4a.spring.boot.myapp13_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.te4a.spring.boot.myapp13_1.bean.BookBean;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookBean, Integer> {
    // 新しいDB操作を作成する場合
    @Query("SELECT X FROM BookBean X ORDER BY X.title") // JPQLクエリを記述
    List<BookBean> findAllOrderByTitle(); // メソッド名
}
