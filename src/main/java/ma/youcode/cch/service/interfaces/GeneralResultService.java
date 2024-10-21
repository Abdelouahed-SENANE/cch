package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.GeneralResult;

import java.util.Set;

public interface GeneralResultService {
    GeneralResult createGeneralResult(GeneralResult competition);
    GeneralResult updateGeneralResult(GeneralResult competition);
    GeneralResult deleteGeneralResult(GeneralResult competition);
    Set<GeneralResult> getAllGeneralResults();

}
