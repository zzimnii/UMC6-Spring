package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public class MissionConverter {

    public static MissionResponseDTO.CreateMissionDto toCreateResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionDto.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.CreateMissionDto request) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }
}
