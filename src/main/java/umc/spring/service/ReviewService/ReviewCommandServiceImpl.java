package umc.spring.service.ReviewService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.MemberHandler;
import umc.spring.apiPayload.exception.StoreHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;


@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Review createReview(Long storeId, Long memberId, ReviewRequestDTO.CreateReviewDto request) throws EntityNotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Review review = ReviewConverter.toReview(member, store, request);
        return reviewRepository.save(review);
    }
}
