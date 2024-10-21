package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.daos.interfaces.StageDao;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Result;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.StageService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StageServiceImp implements StageService {

    private final StageDao stageDao;
    private final CompetitionService competitionService;

    public StageServiceImp(StageDao stageDao, CompetitionService competitionService) {
        this.stageDao = stageDao;
        this.competitionService = competitionService;
    }

    @Override
    public Stage createStage(Stage stage) {

        Optional<Competition> optionalCompetition = competitionService.getCompetition(stage.getCompetition().getCompetitionId());

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
        } else {
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

    @Override
    public Optional<Stage> getStageWithResultOrderedByDuration(UUID id) {
        Optional<Stage> stage = stageDao.findById(id);
        if (stage.isPresent()) {
            Stage getStage = stage.get();
            List<Result> orderedResult = getStage.getResults().stream()
                    .sorted(Comparator.comparing(Result::getStageDuration))
                    .toList();

            getStage.setResults(orderedResult);
            return Optional.of(getStage);
        }

        return Optional.empty();
    }



}
