package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDto {
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotNull
        String content;
    }
}
