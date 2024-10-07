package jp.te4a.spring.boot.teamc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.te4a.spring.boot.teamc.bean.ToolBean;
import java.util.List; // Listのインポート
import org.springframework.data.jpa.repository.Query; // Queryのインポート
import org.springframework.data.repository.query.Param; // Paramのインポート

@Repository
public interface ToolRepository extends JpaRepository<ToolBean, Integer> {
  /*  @Query("SELECT t FROM ToolBean t WHERE " +
    "(:managementCode IS NULL OR t.managementcode LIKE %:managementCode%) AND " +
    "(:productName IS NULL OR t.productName LIKE %:productName%) AND " +
    "(:modelNumber IS NULL OR t.modelNumber LIKE %:modelNumber%) AND " +
    "(:maker IS NULL OR t.maker LIKE %:maker%) AND " +
    "(:purchaseDate IS NULL OR t.purchaseDate = :purchaseDate) AND " +
    "(:serviceLife IS NULL OR t.serviceLife = :serviceLife) AND " +
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
        @Param("serviceLife") String serviceLife, // ここもString型に変更
        @Param("usageProhibited") Boolean usageProhibited,
        @Param("availableForRent") Boolean availableForRent,
        @Param("installationLocation") String installationLocation,
        @Param("expirationDate") LocalDate expirationDate,
        @Param("specification") String specification
    );*/


    @Query("SELECT t FROM ToolBean t WHERE "
            + "(:managementcode IS NULL OR t.managementcode = :managementcode) AND "
            + "(:managementNo IS NULL OR t.managementNo = :managementNo) AND "
            + "(:productName IS NULL OR t.productName LIKE %:productName%) AND "
            + "(:maker IS NULL OR t.maker LIKE %:maker%)")
    List<ToolBean> findByConditions(
            @Param("managementcode") String managementcode,
            @Param("managementNo") Integer managementNo,
            @Param("productName") String productName,
            @Param("maker") String maker
    );
}