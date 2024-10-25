package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.dtos.generalResult.CreateGeneralResultDTO;
import ma.youcode.cch.dtos.generalResult.GeneralResultResponseDTO;
import ma.youcode.cch.entity.GeneralResult;

import java.util.Set;
import java.util.UUID;

public interface GeneralResultService {
    GeneralResultResponseDTO createGeneralResult(CreateGeneralResultDTO generalResultDTO);
    GeneralResultResponseDTO updateGeneralResult(UUID generalResultId,CreateGeneralResultDTO generalResultDTO);
    GeneralResultResponseDTO deleteGeneralResult(UUID generalResultId);
    Set<GeneralResult> getAllGeneralResults();

}
