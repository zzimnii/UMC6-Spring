package umc.spring.service.MemberMission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MemberMissionRequestDTO;


@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandServcie {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    public MemberMission challengeMemberMission(Long memberId, Long missionId, MemberMissionRequestDTO.ChallengingMissionDTO request) {
        MemberMission memberMission = MemberMissionConverter.toMemberMission(request);
        memberMission.setMember(memberRepository.findById(memberId).get());
        memberMission.setMission(missionRepository.findById(missionId).get());
        return memberMissionRepository.save(memberMission);
    }
}
