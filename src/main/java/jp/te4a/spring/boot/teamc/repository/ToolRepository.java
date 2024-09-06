package jp.te4a.spring.boot.teamc.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.te4a.spring.boot.teamc.bean.ToolBean;

@Repository
public interface ToolRepository extends JpaRepository<ToolBean, Integer> {
        //@Query("SELECT X FROM BookBean X ORDER BY X.title") // JPQLクエリを記述
        //List<ToolBean> findAllOrderByTitle(); // メソッド名

        //一件取得 途中
        //@Query
        Optional<ToolBean> findById(int id);

        //全件取得
        //@Query("SELECT x FROM toolBean t")
        //@Override
        //List<ToolBean> findAll();

        // 条件検索
        @Query("SELECT t FROM ToolBean t WHERE " +
        "(:managementCode IS NULL OR t.managementcode LIKE %:managementCode%) AND " +
        "(:productName IS NULL OR t.productName LIKE %:productName%) AND " +
        "(:modelNumber IS NULL OR t.modelNumber LIKE %:modelNumber%) AND " +
        "(:maker IS NULL OR t.maker LIKE %:maker%) AND " +
        "(:purchaseDate IS NULL OR t.purchaseDate = :purchaseDate) AND " +
        "(:serviceLife IS NULL OR t.serviceLife = :serviceLife) AND " +
        "(:depreciation IS NULL OR t.depreciation = :depreciation) AND " +
        "(:usageProhibited IS NULL OR t.usageProhibited = :usageProhibited) AND " +
        "(:availableForRent IS NULL OR t.availableForRent = :availableForRent) AND " +
        "(:installationLocation IS NULL OR t.installationLocation LIKE %:installationLocation%) AND " +
        "(:expirationDate IS NULL OR t.expirationDate = :expirationDate) AND " +
        "(:specification IS NULL OR t.specification LIKE %:specification%)")
        List<ToolBean> findByCriteria(
        @Param("managementCode") String managementCode,
        @Param("productName") String productName,
        @Param("modelNumber") String modelNumber,
        @Param("maker") String maker,
        @Param("purchaseDate") LocalDate purchaseDate,
        @Param("serviceLife") Integer serviceLife,
        @Param("depreciation") Integer depreciation,
        @Param("usageProhibited") Boolean usageProhibited,
        @Param("availableForRent") Boolean availableForRent,
        @Param("installationLocation") String installationLocation,
        @Param("expirationDate") LocalDate expirationDate,
        @Param("specification") String specification
        );

        // 文字列検索（例: 商品名による検索）
        @Query("SELECT t FROM ToolBean t WHERE LOWER(t.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        List<ToolBean> findByProductNameContainingIgnoreCase(@Param("keyword") String keyword);
}
