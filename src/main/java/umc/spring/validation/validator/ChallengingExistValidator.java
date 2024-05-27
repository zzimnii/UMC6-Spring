package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.IsChallenging;
import umc.spring.web.dto.MemberMissionRequestDTO;

@Component
@RequiredArgsConstructor
public class ChallengingExistValidator implements ConstraintValidator<IsChallenging, MemberMissionRequestDTO.ChallengingMissionDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(IsChallenging constraintAnnotation) {
        // 초기화 로직이 필요하면 작성합니다.
    }

    @Override
    public boolean isValid(MemberMissionRequestDTO.ChallengingMissionDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 기본적으로 null 값은 유효하다고 간주
        }

        boolean exists = memberMissionRepository.existsByMemberIdAndIdAndStatus(
                value.getMemberId(), value.getMissionId(), MissionStatus.CHALLENGING);

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전 중인 미션입니다.").addConstraintViolation();
        }

        return !exists;
    }
}
