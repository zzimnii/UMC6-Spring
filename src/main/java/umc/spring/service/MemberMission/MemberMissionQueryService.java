package umc.spring.service.MemberMission;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionQueryService {

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> getChallengingMissionList(Long memberId, Integer page);
}
