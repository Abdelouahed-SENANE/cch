package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.StageResult;

import java.util.Set;

public interface ResultService {
    StageResult createResult(StageResult stageResult);
    StageResult updateResult(StageResult competition);
    StageResult deleteResult(StageResult competition);
    Set<StageResult> getAllResults();

}
