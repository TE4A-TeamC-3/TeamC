package jp.te4a.spring.boot.teamc.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//javadoc生成時のリンク作成
@Documented
//エラーチェッククラス(Validator)指定
@Constraint(validatedBy = WriterValidator.class)
//アノテーション適用要素の指定(メソッド宣言, フィールド宣言)
@Target({ElementType.METHOD,ElementType.FIELD})
//コンパイル後にアノテーション情報を残すか
@Retention(RetentionPolicy.RUNTIME)

//エラーチェック用
public @interface Writer {
    String ok();
    String message() default "input {ok}.";
    
    //Classオブジェクトを得る(戻り値とする)メソッドgroups()
    //デフォルト値はからのクラス
    Class<?>[] groups() default {};
    
    //Payloadクラスを継承したClassオブジェクトを得る
    //(戻り値とする)メソッドpayload()、デフォルト値は空のクラス
    Class<? extends Payload>[] payload() default {};
}
