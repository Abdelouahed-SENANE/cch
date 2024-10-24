package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.dtos.stage.CreateStageDTO;
import ma.youcode.cch.dtos.stage.StageResponseDTO;
import ma.youcode.cch.entity.Stage;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface StageService {

    StageResponseDTO createStage(CreateStageDTO stageDTO);
    StageResponseDTO updateStage(UUID stageId,CreateStageDTO stageDTO);
    StageResponseDTO deleteStage(UUID stageId);
    List<StageResponseDTO> getAllStages();
    StageResponseDTO getStage(UUID id);
    Optional<Stage> getStageWithResultOrderedByDuration(UUID id);
}
