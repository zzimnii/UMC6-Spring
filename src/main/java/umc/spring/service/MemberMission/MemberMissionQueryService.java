package umc.spring.service.MemberMission;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionQueryService {

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
}
