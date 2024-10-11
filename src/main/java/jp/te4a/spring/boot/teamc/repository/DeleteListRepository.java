package jp.te4a.spring.boot.teamc.repository;

import jp.te4a.spring.boot.teamc.bean.DeleteListBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteListRepository extends JpaRepository<DeleteListBean, Integer> {
    
}
