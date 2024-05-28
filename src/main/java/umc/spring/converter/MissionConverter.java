package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
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

    public static Mission toMission(Store store, MissionRequestDTO.CreateMissionDto request) {
        return Mission.builder()
                .store(store)
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }
}
