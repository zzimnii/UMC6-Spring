package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistMembers;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("stores")
public class StoreController {

    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateReviewDto> createReviewDto(@PathVariable @ExistStore Long storeId, @RequestParam @ExistMembers Long memberId, @RequestBody @Valid ReviewRequestDTO.CreateReviewDto request) {
        Review review = reviewCommandService.createReview(storeId,memberId, request);
        return ApiResponse.onSuccess(ReviewConverter.toCreateResultDTO(review));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.CreateMissionDto> createMissionDto(@PathVariable @ExistStore Long storeId, @RequestBody MissionRequestDTO.CreateMissionDto request) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toCreateResultDTO(mission));
    }
}
