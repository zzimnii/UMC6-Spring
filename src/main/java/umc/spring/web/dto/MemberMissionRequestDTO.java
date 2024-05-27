package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistMembers;
import umc.spring.validation.annotation.ExistMissions;
import umc.spring.validation.annotation.IsChallenging;

public class MemberMissionRequestDTO {

    @Getter
    @IsChallenging
    public static class ChallengingMissionDTO{
        @ExistMembers
        Long memberId;
        @ExistMissions
        Long missionId;
    }
}
