package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.MissionHandler;
import umc.spring.apiPayload.exception.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission createMission(Long storeId, MissionRequestDTO.CreateMissionDto request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, request);
        return missionRepository.save(mission);
    }
}
