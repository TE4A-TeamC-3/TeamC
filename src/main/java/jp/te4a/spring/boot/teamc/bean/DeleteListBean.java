import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
i
@Data
@Entity
@Table(name = "delete_list")
public class DeleteListBean {

    @Id // フィールドを主キーとしてマーク
    @Column(name = "id")
    private int id;
    
    @Column(name = "managementcode")
    private String managementcode;
    
    @Column(name = "management_no")
    private int managementNo; //管理番号
    
    @Column(name = "product_name")
    private String productName; //品名
    
    @Column(name = "model_number")
    private String modelNumber; //型番
    
    @Column(name = "maker")
    private String maker; //メーカー
    
    @Column(name = "purchase_date")
    private Date purchaseDate; //購入日 yyyy-mm-dd
    
    @Column(name = "service_life")
    private String serviceLife; //耐用年数
    
    @Column(name = "usage_prohibited")
    private boolean usageProhibited; //使用不可
    
    @Column(name = "available_for_rent")
    private boolean availableForRent; //貸出状況
    
    @Column(name = "installation_location")
    private String installationLocation; //設置場所 本社 仙台 埼玉 新潟 大阪
    
    @Column(name = "expiration_date")
    private Date expirationDate; // 貸出期限

    
    @Column(name = "specification")
    private String specification; //仕様

    @Column(name = "deleted_at", updatable = false)
    private LocalDateTime deletedAt;
}
