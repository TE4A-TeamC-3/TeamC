package jp.te4a.spring.boot.myapp13_1.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import jp.te4a.spring.boot.myapp13_1.validate.Writer;

@Data
@NoArgsConstructor
public class BookForm {
    private Integer id;

    //タイトル必須 3文字以上
    @NotNull
    @Size(min = 3)
    private String title;

    //著者 3文字以上 20文字以下
    @Size(min = 3, max = 20)
    @Writer(ok = "東北タロウ")
    private String writer;

    private String publisher;
    
    //価格 0円以上
    @Min(0)
    private Integer price;
}