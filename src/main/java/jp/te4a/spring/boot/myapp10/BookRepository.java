package jp.te4a.spring.boot.myapp10;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookBean, Integer> {
    // 新しいDB操作を作成する場合
    @Query("SELECT X FROM BookBean X ORDER BY X.title") // JPQLクエリを記述
    List<BookBean> findAllOrderByTitle(); // メソッド名
}

/* 
//DB寄りとしてBookBeanでデータを扱う
@Repository
public class BookRepository {
    private final ConcurrentMap<Integer, BookBean>bookMap = new ConcurrentHashMap<>();
    private int BOOK_ID = 1;

    public int getBookId(){//DBを使う場合 → AUTO_INCREMENT等で代用
        return BOOK_ID++;
    }

    //作成
    public BookBean create(BookBean bookBean){
        return bookMap.put(bookBean.getId(),bookBean);
    }

    //更新
    public BookBean update(BookBean updateBookBean) {
        BookBean bookBean = bookMap.get(updateBookBean.getId());
        BeanUtils.copyProperties(updateBookBean, bookBean);
        return bookBean;
    }
    

    //削除
    public void delete(Integer bookId) {
        bookMap.remove(bookId);
    }

    //取得(全件)
    public BookBean findOne(Integer id){
        return bookMap.get(id);
    }

    //取得(1件)
    public List<BookBean> findAll() {
        return new ArrayList<>(bookMap.values());
    }

}
*/