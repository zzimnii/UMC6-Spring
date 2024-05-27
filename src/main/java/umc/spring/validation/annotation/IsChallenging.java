package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.ChallengingExistValidator;
import umc.spring.validation.validator.MembersExistValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = ChallengingExistValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsChallenging {
    String message() default "이미 도전중인 미션입니다..";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
