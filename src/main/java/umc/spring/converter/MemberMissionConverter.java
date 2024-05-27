package umc.spring.converter;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.ChallengingResultDTO toChallengingResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.ChallengingResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequestDTO.ChallengingMissionDTO request) {
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
