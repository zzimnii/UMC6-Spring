package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMission.MemberMissionCommandServcie;
import umc.spring.service.MemberMission.MemberMissionQueryService;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.validation.annotation.*;
import umc.spring.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberMissionCommandServcie memberMissionCommandServcie;
    private final ReviewQueryService reviewQueryService;
    private final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/")
    @Operation(summary = "회원가입 API",description = "회원가입하는 API입니다")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> joinMemberDTO(@RequestBody MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions")
    @Operation(summary = "미션에 도전하는 API",description = "미션에 도전하는 API입니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MemberMissionResponseDTO.ChallengingResultDTO> challengingMissionDTO(@RequestBody @IsChallenging MemberMissionRequestDTO.ChallengingMissionDTO request) {
        MemberMission memberMission = memberMissionCommandServcie.challengeMemberMission(request.getMemberId(), request.getMissionId(), request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengingResultDTO(memberMission));
    }

    @GetMapping("/{memberId}/review")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API",description = "내가 작성한 리뷰 목록 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMissionList(@ExistMembers @PathVariable(name = "memberId") Long memberId, @CheckPage  @RequestParam(name = "page") Integer page){
        Page<Review> reviews = reviewQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviews));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "도전중인 미션 목록 조회 API",description = "도전중인 미션 목록 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewListDTO> getMemberMissionList(@ExistMembers @PathVariable(name = "memberId") Long memberId, @CheckPage  @RequestParam(name = "page") Integer page){
        Page<MemberMission> memberMissions = memberMissionQueryService.getChallengingMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberMissionConverter.memberMissionPreViewListDTO(memberMissions));
    }
}
