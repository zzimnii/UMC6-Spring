package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.List;

public class MissionRequestDTO {

    @Getter
    public static class CreateMissionDto{
        @NotBlank
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotNull
        String missionSpec;
    }
}
