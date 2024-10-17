package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Stage;

import java.util.Set;
import java.util.UUID;

public interface StageService {

    Stage saveStage(Stage Stage);
    Stage updateStage(Stage Stage);
    Stage deleteStage(Stage Stage);
    Set<Stage> findAllStages();
    Stage findStage(UUID id);
}
