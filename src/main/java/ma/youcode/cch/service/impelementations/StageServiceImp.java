package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.daos.interfaces.CompetitionDao;
import ma.youcode.cch.daos.interfaces.StageDao;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.StageService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class StageServiceImp implements StageService {

    private final StageDao stageDao;
    private final CompetitionService competitionService;
    public StageServiceImp(StageDao stageDao , CompetitionService competitionService ){this.stageDao = stageDao; this.competitionService = competitionService;}

    @Override
    public Stage createStage(Stage stage) {

        Optional<Competition> optionalCompetition = competitionService.getCompetition(UUID.fromString("a6d2a5b9-f296-4f0c-b856-01a93eb4f81b"));

        if (optionalCompetition.isPresent()) {
            Competition competition = optionalCompetition.get();
            if (competition.getStages().size() < competition.getNumberOfStage()) {
                int nextStageNumber = competition.getStages().stream()
                        .mapToInt(Stage::getStageNumber)
                        .max()
                        .orElse(0) + 1;
                stage.setStageNumber(nextStageNumber);
                stage.setCompetition(competition);
                return stageDao.save(stage);
            } else {
                System.out.println("cannot add new stage because you have reached max stage " + competition.getNumberOfStage());
            }
        }else {
            System.out.println("Competition Not Found");
        }

        return null;
    }

    @Override
    public Stage updateStage(Stage stage) {
        Optional<Stage> stageOptional = stageDao.findById(stage.getStageId());
        if (stageOptional.isPresent()) {
            Stage getStage = stageOptional.get();
            stage.setStageNumber(getStage.getStageNumber());
            return stageDao.update(stage);
        }
        return null;
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
