package jp.te4a.spring.boot.myapp13_1.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WriterValidator implements ConstraintValidator<Writer, String>{
    String ok;
    
    @Override
    public void initialize(Writer nv){
        ok = nv.ok();
    }

    @Override
    public boolean isValid(String in, ConstraintValidatorContext cxt){
        if(in == ok){
            return true;
        }
        System.out.println(in.equals(ok));
        return in.equals(ok);
    }
}
