package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.DTOs.stage.CreateStageDTO;
import ma.youcode.cch.DTOs.stage.StageResponseDTO;
import ma.youcode.cch.entity.Stage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StageService {

    StageResponseDTO createStage(CreateStageDTO stageDTO);
    StageResponseDTO updateStage(UUID stageId,CreateStageDTO stageDTO);
    StageResponseDTO deleteStage(UUID stageId);
    List<StageResponseDTO> getAllStages();
    StageResponseDTO getStage(UUID stageId);
    Optional<Stage> getStageEntity(UUID stageId);
    Optional<Stage> getStageWithResultOrderedByDuration(UUID stageId);
    StageResponseDTO patchCompleted(CreateStageDTO createStageDTO , UUID stageId);
}
