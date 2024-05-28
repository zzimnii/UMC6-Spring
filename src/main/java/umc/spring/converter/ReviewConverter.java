package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResponseDTO.CreateReviewDto toCreateResultDTO(Review review) {
        return ReviewResponseDTO.CreateReviewDto.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(Member member, Store store, ReviewRequestDTO.CreateReviewDto request) {
        return Review.builder()
                .member(member)
                .store(store)
                .title(request.getTitle())
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }
}
