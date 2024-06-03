package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;

public interface ReviewQueryService {
    Page<Review> getReviewList(Long memberId, Integer page);
}
