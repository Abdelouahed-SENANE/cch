package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.daos.interfaces.StageDao;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.service.interfaces.StageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class StageServiceImp implements StageService {

    private final StageDao stageDao;
    public StageServiceImp(StageDao stageDao){this.stageDao = stageDao;}

    @Override
    public Stage createStage(Stage Stage) {
        return stageDao.save(Stage);
    }

    @Override
    public Stage updateStage(Stage Stage) {
        return stageDao.update(Stage);
    }

    @Override
    public Stage deleteStage(Stage Stage) {
        return stageDao.delete(Stage);
    }

    @Override
    public Set<Stage> getAllStages() {
        return stageDao.findAll();
    }

    @Override
    public Optional<Stage> getStage(UUID id) {
        return stageDao.findById(id);
    }

//    @Override
//    public List<Stage> getStagesByCompetitionId(UUID id) {
//        return stageDao.findStagesByCompetitionId(id);
//    }
}
