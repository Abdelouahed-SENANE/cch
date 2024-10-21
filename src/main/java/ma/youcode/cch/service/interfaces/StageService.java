package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Stage;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface StageService {

    Stage createStage(Stage Stage);
    Stage updateStage(Stage Stage);
    Stage deleteStage(Stage Stage);
    Set<Stage> getAllStages();
    Optional<Stage> getStage(UUID id);
    Optional<Stage> getStageWithResultOrderedByDuration(UUID id);
}
