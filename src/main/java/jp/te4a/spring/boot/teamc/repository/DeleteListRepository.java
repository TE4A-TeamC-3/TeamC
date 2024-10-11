package jp.te4a.spring.boot.teamc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import jp.te4a.spring.boot.teamc.bean.ToolBean;

import java.util.Date;
import java.util.List;

public interface DeleteListRepository extends JpaRepository<DeleteListBean, Integer> {
    
}
