package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMission.MemberMissionQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.IsChallenging;
import umc.spring.web.dto.MemberMissionRequestDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckPageExistValidator implements ConstraintValidator<CheckPage, Integer> {
    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if (value < 1){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.INVALID_PAGE.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}