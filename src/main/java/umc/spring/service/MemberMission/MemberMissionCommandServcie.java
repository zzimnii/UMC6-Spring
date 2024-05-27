package umc.spring.service.MemberMission;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;

public interface MemberMissionCommandServcie {
    MemberMission challengeMemberMission(Long memberId, Long missionId, MemberMissionRequestDTO.ChallengingMissionDTO request);

}
