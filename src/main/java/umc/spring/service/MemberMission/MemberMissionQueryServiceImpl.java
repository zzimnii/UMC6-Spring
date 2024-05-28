package umc.spring.service.MemberMission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndId(memberId, missionId);
    }
}