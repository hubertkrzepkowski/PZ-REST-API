package com.example.photop2.Validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Użytkownik o tym adresie e-mail jest już zarejestrowany ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
