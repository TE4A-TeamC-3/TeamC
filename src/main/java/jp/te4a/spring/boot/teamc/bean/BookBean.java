package jp.te4a.spring.boot.teamc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*; 

@Entity
@Table(name = "books") // 対応するDBのテーブルを指定
@Data
@AllArgsConstructor
@NoArgsConstructor // 引数なしコンストラクタを自動生成
public class BookBean {

    @Id // フィールドを主キーとしてマーク
    @GeneratedValue(strategy = GenerationType.IDENTITY) // データベースによって自動インクリメント
    private Integer id;

    @Column(nullable = false) // NOT NULL制約
    private String title;

    private String writer;
    private String publisher;
    private Integer price;
}
