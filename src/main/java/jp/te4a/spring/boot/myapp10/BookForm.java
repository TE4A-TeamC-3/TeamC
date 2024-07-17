package jp.te4a.spring.boot.myapp10;

import lombok.Data;
import lombok.NoArgsConstructor;

//画面と連携
@Data
@NoArgsConstructor
public class BookForm {
    private Integer id;
    private String title;
    private String writer;
    private String publisher;
    private Integer price;
}
