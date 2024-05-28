package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMission.MemberMissionCommandServcie;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.validation.annotation.ExistMembers;
import umc.spring.validation.annotation.ExistMissions;
import umc.spring.validation.annotation.IsChallenging;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberMissionCommandServcie memberMissionCommandServcie;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> joinMemberDTO(@RequestBody MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions")
    public ApiResponse<MemberMissionResponseDTO.ChallengingResultDTO> challengingMissionDTO(@RequestBody @IsChallenging MemberMissionRequestDTO.ChallengingMissionDTO request) {
        MemberMission memberMission = memberMissionCommandServcie.challengeMemberMission(request.getMemberId(), request.getMissionId(), request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengingResultDTO(memberMission));
    }
}
