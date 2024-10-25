package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.DTOs.stageResult.CreateGeneralResultDTO;
import ma.youcode.cch.DTOs.stageResult.GeneralResultResponseDTO;

import java.util.List;
import java.util.UUID;

public interface GeneralResultService {
    GeneralResultResponseDTO createGeneralResult(CreateGeneralResultDTO generalResultDTO);
    GeneralResultResponseDTO updateGeneralResult(UUID cyclistId , UUID competitionId ,CreateGeneralResultDTO generalResultDTO);
    List<GeneralResultResponseDTO> getAllGeneralResults();
    GeneralResultResponseDTO deleteGeneralResult(UUID cyclistId , UUID competitionId);
    GeneralResultResponseDTO getGeneralResult(UUID cyclistId , UUID competitionId);

}
