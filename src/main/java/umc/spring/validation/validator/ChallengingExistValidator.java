package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.service.MemberMission.MemberMissionQueryService;
import umc.spring.validation.annotation.IsChallenging;
import umc.spring.web.dto.MemberMissionRequestDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChallengingExistValidator implements ConstraintValidator<IsChallenging, MemberMissionRequestDTO.ChallengingMissionDTO> {

    private final MemberMissionQueryService memberMissionQueryService;

    @Override
    public void initialize(IsChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequestDTO.ChallengingMissionDTO value, ConstraintValidatorContext context) {
        System.out.println("validator");

        Optional<MemberMission> target = memberMissionQueryService.findByMemberIdAndMissionId(value.getMemberId(), value.getMissionId());

        if (target.isPresent() && target.get().getStatus() == MissionStatus.CHALLENGING){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_CHALLENGING_MISSION.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
