package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.DTOs.stageResult.CreateStageResultDTO;
import ma.youcode.cch.DTOs.stageResult.StageResultResponseDTO;
import ma.youcode.cch.entity.StageResult;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface StageResultService {
    StageResultResponseDTO createStageResult(CreateStageResultDTO stageResultDTO);
    StageResultResponseDTO updateStageResult(CreateStageResultDTO stageResultDTO , UUID cyclistId , UUID stageId);
    StageResultResponseDTO deleteStageResult(UUID cyclist , UUID stageId);
    List<StageResultResponseDTO> getAllStageResults();
    StageResultResponseDTO getStageResult(UUID cyclistId , UUID stageId);

}
